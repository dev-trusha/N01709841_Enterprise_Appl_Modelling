package com.va.week9.microservices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarketTransactionRepository extends JpaRepository<MarketTransaction, Long> {

    // SELECT * FROM market_transactions WHERE order_id = ?
    List<MarketTransaction> findByOrderId(Long orderId);

}
