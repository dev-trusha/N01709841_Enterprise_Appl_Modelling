package com.va.week9.microservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// @RestController returns JSON automatically
// This service CONSUMES data from OrderMgmt and stores it
@RestController
@RequestMapping("/acct/transactions")
public class AcctController {

    @Autowired
    private AcctTransactionRepository acctTransactionRepository;

    // ============================================================
    // Operation 1: INSERT - Store account transaction
    // Called by OrderMgmt when a new order is placed
    // URL: POST http://localhost:8081/acct/transactions
    // Body: { "orderId":1, "date":"2026-03-16", "price":150.0, "quantity":100, "accountId":"ABC" }
    // ============================================================
    @PostMapping
    public String saveTransaction(@RequestBody Map<String, Object> request) {
        AcctTransaction txn = new AcctTransaction();
        txn.setOrderId(Long.valueOf(request.get("orderId").toString()));
        txn.setDate(request.get("date").toString());
        txn.setPrice(Double.valueOf(request.get("price").toString()));
        txn.setQuantity(Integer.valueOf(request.get("quantity").toString()));
        txn.setAccountId(request.get("accountId").toString());

        acctTransactionRepository.save(txn);  // INSERT into acct_transactions
        return "Account transaction saved! Order ID: " + txn.getOrderId() + ", Account: " + txn.getAccountId();
    }

    // ============================================================
    // Operation 2: SELECT - Get ALL account transactions
    // URL: GET http://localhost:8081/acct/transactions
    // ============================================================
    @GetMapping
    public List<AcctTransaction> getAllTransactions() {
        return acctTransactionRepository.findAll();  // SELECT * FROM acct_transactions
    }

    // ============================================================
    // Operation 3: SELECT - Get transactions for a specific order
    // URL: GET http://localhost:8081/acct/transactions/order/{orderId}
    // Called by OrderMgmt's detail page
    // ============================================================
    @GetMapping("/order/{orderId}")
    public List<AcctTransaction> getByOrderId(@PathVariable Long orderId) {
        return acctTransactionRepository.findByOrderId(orderId);
    }

}
