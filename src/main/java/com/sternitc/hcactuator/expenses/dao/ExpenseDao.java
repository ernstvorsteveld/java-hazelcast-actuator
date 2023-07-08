package com.sternitc.hcactuator.expenses.dao;

import com.sternitc.hcactuator.expenses.Expense;

import java.util.UUID;

public interface ExpenseDao {
    Expense save(Expense expense);

    Expense get(String id);
}
