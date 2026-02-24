//N01709841
package com.spring.web.flux.service;

import com.spring.web.flux.model.Checking;
import com.spring.web.flux.repository.CheckingRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CheckingService {

    private final CheckingRepository repo;

    public CheckingService(CheckingRepository repo) {
        this.repo = repo;
    }

    // CRUD
    public Mono<Checking> create(Checking checking) {
        return repo.save(checking);
    }

    public Flux<Checking> findAll() {
        return repo.findAll();
    }

    public Mono<Checking> findById(String id) {
        return repo.findById(id);
    }

    public Mono<Checking> update(String id, Checking updated) {
        return repo.findById(id)
                .flatMap(existing -> {
                    existing.setAccountHolder(updated.getAccountHolder());
                    existing.setBalance(updated.getBalance());
                    existing.setInsufficientFundFee(updated.getInsufficientFundFee());
                    return repo.save(existing);
                });
    }

    public Mono<Void> delete(String id) {
        return repo.deleteById(id);
    }

    public Mono<Checking> deposit(String id, double amount) {
        return repo.findById(id)
                .map(acc -> {
                    acc.deposit(amount);
                    return acc;
                })
                .flatMap(repo::save);
    }

    public Mono<Checking> withdraw(String id, double amount) {
        return repo.findById(id)
                .map(acc -> {
                    acc.withdrawal(amount);
                    return acc;
                })
                .flatMap(repo::save);
    }

    public Mono<Checking> processCheck(String id, double checkAmount) {
        return repo.findById(id)
                .map(acc -> {
                    acc.processingCheck(checkAmount);
                    return acc;
                })
                .flatMap(repo::save);
    }
}