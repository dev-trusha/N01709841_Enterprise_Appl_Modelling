//N01709841
package com.spring.web.flux.controller;

import com.spring.web.flux.model.Checking;
import com.spring.web.flux.service.CheckingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/checking")
public class CheckingController {

    private final CheckingService service;

    public CheckingController(CheckingService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Checking> create(@RequestBody Checking checking) {
        return service.create(checking);
    }

    @GetMapping
    public Flux<Checking> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Checking> findById(@PathVariable String id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public Mono<Checking> update(@PathVariable String id, @RequestBody Checking updated) {
        return service.update(id, updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String id) {
        return service.delete(id);
    }

    @PatchMapping("/{id}/deposit")
    public Mono<Checking> deposit(@PathVariable String id, @RequestParam double amount) {
        return service.deposit(id, amount);
    }

    @PatchMapping("/{id}/withdraw")
    public Mono<Checking> withdraw(@PathVariable String id, @RequestParam double amount) {
        return service.withdraw(id, amount);
    }

    @PatchMapping("/{id}/process-check")
    public Mono<Checking> processCheck(@PathVariable String id, @RequestParam double amount) {
        return service.processCheck(id, amount);
    }
}