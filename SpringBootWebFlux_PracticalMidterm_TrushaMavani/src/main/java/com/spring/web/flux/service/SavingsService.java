//N01709841
package com.spring.web.flux.service;

import com.spring.web.flux.model.Savings;
import com.spring.web.flux.repository.SavingsRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service

public class SavingsService {

    private final SavingsRepository repo;

    public SavingsService(SavingsRepository repo) {
        this.repo = repo;
    }

    // CRUD
    public Mono<Savings> create(Savings savings) {
        return repo.save(savings);
    }

    public Flux<Savings> findAll() {
        return repo.findAll();
    }

    public Mono<Savings> findById(String id) {
        return repo.findById(id);
    }

    public Mono<Savings> update(String id, Savings updated) {
        return repo.findById(id)
                .flatMap(existing -> {
                    existing.setAccountHolder(updated.getAccountHolder());
                    existing.setBalance(updated.getBalance());
                    existing.setInterestRate(updated.getInterestRate());
                    return repo.save(existing);
                });
    }

    public Mono<Void> delete(String id) {
        return repo.deleteById(id);
    }

    // “Account operations”
    public Mono<Savings> deposit(String id, double amount) {
        return repo.findById(id)
                .map(acc -> { acc.deposit(amount); return acc; })
                .flatMap(repo::save);
    }

    public Mono<Savings> withdraw(String id, double amount) {
        return repo.findById(id)
                .map(acc -> { acc.withdrawal(amount); return acc; })
                .flatMap(repo::save);
    }

    public Mono<Savings> depositMonthlyInterest(String id) {
        return repo.findById(id)
                .map(acc -> { acc.depositMonthlyInterest(); return acc; })
                .flatMap(repo::save);
    }
}