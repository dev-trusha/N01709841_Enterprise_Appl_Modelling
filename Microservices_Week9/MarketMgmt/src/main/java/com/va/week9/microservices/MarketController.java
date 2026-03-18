package com.va.week9.microservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// @RestController returns JSON
// This service CONSUMES order data and places it on the stock exchange
@RestController
@RequestMapping("/market/transactions")
public class MarketController {

    @Autowired
    private MarketTransactionRepository marketTransactionRepository;

    // ============================================================
    // Operation 1: INSERT - Place order to stock market
    // Called by OrderMgmt when a new order is placed
    // URL: POST http://localhost:8083/market/transactions
    // Body: { "orderId":1, "stockQuote":"AAPL", "price":150.0, "quantity":100, "date":"2026-03-16" }
    // ============================================================
    @PostMapping
    public String placeOrderToMarket(@RequestBody Map<String, Object> request) {
        MarketTransaction txn = new MarketTransaction();
        txn.setOrderId(Long.valueOf(request.get("orderId").toString()));
        txn.setStockQuote(request.get("stockQuote").toString());
        txn.setPrice(Double.valueOf(request.get("price").toString()));
        txn.setQuantity(Integer.valueOf(request.get("quantity").toString()));
        txn.setDate(request.get("date").toString());
        txn.setStatus("PLACED");  // order has been placed on the exchange

        marketTransactionRepository.save(txn);  // INSERT into market_transactions
        return "Order placed on stock exchange! Stock: " + txn.getStockQuote()
                + " | Qty: " + txn.getQuantity() + " | Status: " + txn.getStatus();
    }

    // ============================================================
    // Operation 2: SELECT - Get ALL market transactions
    // URL: GET http://localhost:8083/market/transactions
    // ============================================================
    @GetMapping
    public List<MarketTransaction> getAllMarketTransactions() {
        return marketTransactionRepository.findAll();  // SELECT * FROM market_transactions
    }

    // ============================================================
    // Operation 3: SELECT - Get market transactions for a specific order
    // URL: GET http://localhost:8083/market/transactions/order/{orderId}
    // Called by OrderMgmt's detail page
    // ============================================================
    @GetMapping("/order/{orderId}")
    public List<MarketTransaction> getByOrderId(@PathVariable Long orderId) {
        return marketTransactionRepository.findByOrderId(orderId);
    }

}
