package com.va.week9.microservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// @RestController returns JSON
// This service CONSUMES data from OrderMgmt, calculates fee, and records it
@RestController
@RequestMapping("/fees")
public class FeeController {

    @Autowired
    private FeeRepository feeRepository;

    // ============================================================
    // Operation 1: INSERT - Record fee for an order
    // Called by OrderMgmt when a new order is placed
    // URL: POST http://localhost:8082/fees
    // Body: { "orderId":1, "date":"2026-03-16", "price":150.0, "quantity":100 }
    //
    // Business Rule: Fee = 1% of total trade value (price x quantity x 0.01)
    // ============================================================
    @PostMapping
    public String saveFee(@RequestBody Map<String, Object> request) {
        double price    = Double.valueOf(request.get("price").toString());
        int quantity    = Integer.valueOf(request.get("quantity").toString());

        // Business Rule: 1% fee on total trade value
        double tradeValue = price * quantity;
        double fee        = tradeValue * 0.01;

        FeeRecord feeRecord = new FeeRecord();
        feeRecord.setOrderId(Long.valueOf(request.get("orderId").toString()));
        feeRecord.setDate(request.get("date").toString());
        feeRecord.setQuantity(quantity);
        feeRecord.setFees(fee);
        feeRecord.setTotalFees(fee);  // simplified: just this transaction's fee

        feeRepository.save(feeRecord);  // INSERT into fee_records
        return "Fee recorded: $" + String.format("%.2f", fee) + " for Order ID: " + feeRecord.getOrderId();
    }

    // ============================================================
    // Operation 2: SELECT - Get ALL fee records
    // URL: GET http://localhost:8082/fees
    // ============================================================
    @GetMapping
    public List<FeeRecord> getAllFees() {
        return feeRepository.findAll();  // SELECT * FROM fee_records
    }

    // ============================================================
    // Operation 3: SELECT - Get fees for a specific order
    // URL: GET http://localhost:8082/fees/order/{orderId}
    // Called by OrderMgmt's detail page
    // ============================================================
    @GetMapping("/order/{orderId}")
    public List<FeeRecord> getFeesByOrderId(@PathVariable Long orderId) {
        return feeRepository.findByOrderId(orderId);
    }

}
