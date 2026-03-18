package com.va.week9.microservices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcctTransactionRepository extends JpaRepository<AcctTransaction, Long> {

    // Custom query: SELECT * FROM acct_transactions WHERE order_id = ?
    // Spring Data JPA generates this automatically from the method name!
    List<AcctTransaction> findByOrderId(Long orderId);

}
