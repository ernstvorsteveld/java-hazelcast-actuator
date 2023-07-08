package com.sternitc.hcactuator.expenses.dao;

import com.sternitc.hcactuator.expenses.Expense;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ExpenseDaoMap implements ExpenseDao {

    private final Map<String, Expense> store = new HashMap<>();

    @Override
    public Expense save(Expense expense) {
        this.store.put(expense.getId(), expense);
        return expense;
    }

    @Override
    public Expense get(String id) {
        return this.store.get(id);
    }
}
