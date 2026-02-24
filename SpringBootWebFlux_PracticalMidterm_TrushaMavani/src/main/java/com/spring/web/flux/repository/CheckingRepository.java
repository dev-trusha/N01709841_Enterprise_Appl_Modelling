package com.spring.web.flux.repository;

import com.spring.web.flux.model.Checking;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingRepository extends ReactiveMongoRepository<Checking, String> {}