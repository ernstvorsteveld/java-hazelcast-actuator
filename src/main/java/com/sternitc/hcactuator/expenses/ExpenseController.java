package com.sternitc.hcactuator.expenses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/{id}")
    @ResponseBody
    public Mono<ExpenseResponse> getExpenseById(@PathVariable("id") String id) {
        return Mono.just(ExpenseMapper.INSTANCE.toResponse(expenseService.getById(id)));
    }

    @PostMapping("/")
    public Mono<ExpenseResponse> save(@RequestBody ExpensePayload expensePayload) {
        return Mono.just(ExpenseMapper.INSTANCE.toResponse(expenseService.save(ExpenseMapper.INSTANCE.toDomain(expensePayload))));
    }
}

