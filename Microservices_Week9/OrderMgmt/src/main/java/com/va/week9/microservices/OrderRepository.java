package com.va.week9.microservices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// JpaRepository gives us free CRUD methods:
//   save(), findAll(), findById(), deleteById(), count(), etc.
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // No extra methods needed - JpaRepository handles everything
}
