package com.sternitc.hcactuator.expenses;

public interface ExpenseService {
    Expense getById(String id);

    Expense save(Expense expense);
}
