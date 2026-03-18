package com.va.week9.microservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @Controller works with Thymeleaf HTML templates
// Returns HTML pages (not JSON)
@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderInterfaceService orderInterfaceService;

    // ============================================================
    // Operation 1: SHOW ALL ORDERS (SELECT query)
    // URL: http://localhost:8080/orders
    // ============================================================
    @GetMapping
    public String showAllOrders(Model model) {
        List<Order> orders = orderRepository.findAll();  // SELECT * FROM orders
        model.addAttribute("orders", orders);            // send to Thymeleaf
        return "index";                                  // loads templates/index.html
    }

    // ============================================================
    // Operation 2: SHOW NEW ORDER FORM
    // URL: http://localhost:8080/orders/new
    // ============================================================
    @GetMapping("/new")
    public String showOrderForm(Model model) {
        model.addAttribute("order", new Order());  // empty Order object for the form
        return "order-form";                       // loads templates/order-form.html
    }

    // ============================================================
    // Operation 3: SAVE ORDER + NOTIFY OTHER MICROSERVICES (INSERT)
    // Called when user submits the form
    // ============================================================
    @PostMapping("/save")
    public String saveOrder(@ModelAttribute Order order) {
        // Step 1: Save order to OUR database (INSERT into orders)
        Order savedOrder = orderRepository.save(order);

        // Step 2: Tell AcctMgmt to record this transaction
        orderInterfaceService.sendToAcctService(savedOrder);

        // Step 3: Tell FeeMgmt to calculate and record the fee
        orderInterfaceService.sendToFeeService(savedOrder);

        // Step 4: Tell MarketMgmt to place the order on the exchange
        orderInterfaceService.sendToMarketService(savedOrder);

        return "redirect:/orders";  // redirect back to the orders list
    }

    // ============================================================
    // Operation 4: VIEW ORDER DETAIL WITH DATA FROM OTHER SERVICES
    // URL: http://localhost:8080/orders/{id}/detail
    // Shows fees, account transactions, market transactions
    // ============================================================
    @GetMapping("/{id}/detail")
    public String showOrderDetail(@PathVariable Long id, Model model) {
        // Get order from OUR database
        Order order = orderRepository.findById(id).orElse(null);
        model.addAttribute("order", order);

        // Get fee info from FeeMgmt (port 8082)
        String fees = orderInterfaceService.getFeesForOrder(id);
        model.addAttribute("fees", fees);

        // Get account transactions from AcctMgmt (port 8081)
        String acctTxns = orderInterfaceService.getAcctTransactionsForOrder(id);
        model.addAttribute("acctTxns", acctTxns);

        // Get market transactions from MarketMgmt (port 8083)
        String marketTxns = orderInterfaceService.getMarketTransactionsForOrder(id);
        model.addAttribute("marketTxns", marketTxns);

        return "order-detail";  // loads templates/order-detail.html
    }

}
