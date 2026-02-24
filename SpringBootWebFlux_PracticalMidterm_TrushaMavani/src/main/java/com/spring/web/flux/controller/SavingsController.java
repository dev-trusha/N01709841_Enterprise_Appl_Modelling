//N01709841
package com.spring.web.flux.controller;

import com.spring.web.flux.model.Savings;
import com.spring.web.flux.service.SavingsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/savings")
public class SavingsController {

    private final SavingsService service;
    public SavingsController(SavingsService service) {
        this.service = service;
    }
    // ===== CRUD =====

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Savings> create(@RequestBody Savings savings) {
        return service.create(savings);
    }

    @GetMapping
    public Flux<Savings> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Savings> findById(@PathVariable String id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public Mono<Savings> update(@PathVariable String id, @RequestBody Savings updated) {
        return service.update(id, updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String id) {
        return service.delete(id);
    }

    // ===== Operations =====

    @PatchMapping("/{id}/deposit")
    public Mono<Savings> deposit(@PathVariable String id, @RequestParam double amount) {
        return service.deposit(id, amount);
    }

    @PatchMapping("/{id}/withdraw")
    public Mono<Savings> withdraw(@PathVariable String id, @RequestParam double amount) {
        return service.withdraw(id, amount);
    }

    @PatchMapping("/{id}/monthly-interest")
    public Mono<Savings> monthlyInterest(@PathVariable String id) {
        return service.depositMonthlyInterest(id);
    }
}