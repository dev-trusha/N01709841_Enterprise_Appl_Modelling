package com.va.week9.microservices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeeRepository extends JpaRepository<FeeRecord, Long> {

    // SELECT * FROM fee_records WHERE order_id = ?
    List<FeeRecord> findByOrderId(Long orderId);

}
