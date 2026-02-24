//N01709841
package com.spring.web.flux.repository;

import com.spring.web.flux.model.Savings;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsRepository extends ReactiveMongoRepository<Savings, String> { }