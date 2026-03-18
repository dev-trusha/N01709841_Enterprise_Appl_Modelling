package com.va.week9.microservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

// This service calls other microservices using RestTemplate (like an HTTP client)
@Service
public class OrderInterfaceService {

    @Autowired
    private RestTemplate restTemplate;

    // These URLs come from application.properties
    @Value("${acct.service.url}")
    private String acctServiceUrl;

    @Value("${fee.service.url}")
    private String feeServiceUrl;

    @Value("${market.service.url}")
    private String marketServiceUrl;

    // ============================================================
    // SEND order data to AcctMgmt (POST request to port 8081)
    // AcctMgmt will store this as an account transaction
    // ============================================================
    public void sendToAcctService(Order order) {
        try {
            Map<String, Object> body = new HashMap<>();
            body.put("orderId",   order.getOrderId());
            body.put("date",      order.getDate());
            body.put("price",     order.getPrice());
            body.put("quantity",  order.getQuantity());
            body.put("accountId", order.getAccountId());

            restTemplate.postForObject(acctServiceUrl + "/acct/transactions", body, String.class);
            System.out.println("AcctMgmt notified for order: " + order.getOrderId());
        } catch (Exception e) {
            System.out.println("WARNING: Could not reach AcctMgmt - " + e.getMessage());
        }
    }

    // ============================================================
    // SEND order data to FeeMgmt (POST request to port 8082)
    // FeeMgmt will calculate and record the fee
    // ============================================================
    public void sendToFeeService(Order order) {
        try {
            Map<String, Object> body = new HashMap<>();
            body.put("orderId",  order.getOrderId());
            body.put("date",     order.getDate());
            body.put("price",    order.getPrice());
            body.put("quantity", order.getQuantity());

            restTemplate.postForObject(feeServiceUrl + "/fees", body, String.class);
            System.out.println("FeeMgmt notified for order: " + order.getOrderId());
        } catch (Exception e) {
            System.out.println("WARNING: Could not reach FeeMgmt - " + e.getMessage());
        }
    }

    // ============================================================
    // SEND order data to MarketMgmt (POST request to port 8083)
    // MarketMgmt will place the order on the stock exchange
    // ============================================================
    public void sendToMarketService(Order order) {
        try {
            Map<String, Object> body = new HashMap<>();
            body.put("orderId",    order.getOrderId());
            body.put("stockQuote", order.getStockQuote());
            body.put("price",      order.getPrice());
            body.put("quantity",   order.getQuantity());
            body.put("date",       order.getDate());

            restTemplate.postForObject(marketServiceUrl + "/market/transactions", body, String.class);
            System.out.println("MarketMgmt notified for order: " + order.getOrderId());
        } catch (Exception e) {
            System.out.println("WARNING: Could not reach MarketMgmt - " + e.getMessage());
        }
    }

    // ============================================================
    // GET fees for a specific order from FeeMgmt
    // Returns JSON string from port 8082
    // ============================================================
    public String getFeesForOrder(Long orderId) {
        try {
            return restTemplate.getForObject(feeServiceUrl + "/fees/order/" + orderId, String.class);
        } catch (Exception e) {
            return "FeeMgmt service not available (make sure port 8082 is running)";
        }
    }

    // ============================================================
    // GET account transactions for a specific order from AcctMgmt
    // Returns JSON string from port 8081
    // ============================================================
    public String getAcctTransactionsForOrder(Long orderId) {
        try {
            return restTemplate.getForObject(acctServiceUrl + "/acct/transactions/order/" + orderId, String.class);
        } catch (Exception e) {
            return "AcctMgmt service not available (make sure port 8081 is running)";
        }
    }

    // ============================================================
    // GET market transactions for a specific order from MarketMgmt
    // Returns JSON string from port 8083
    // ============================================================
    public String getMarketTransactionsForOrder(Long orderId) {
        try {
            return restTemplate.getForObject(marketServiceUrl + "/market/transactions/order/" + orderId, String.class);
        } catch (Exception e) {
            return "MarketMgmt service not available (make sure port 8083 is running)";
        }
    }

}
