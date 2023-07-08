package com.sternitc.hcactuator.expenses;

import com.sternitc.hcactuator.expenses.dao.ExpenseDao;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;

@AllArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseDao expenseDao;

    @Cacheable(value = "expenses")
    @Override
    public Expense getById(String id) {
        return expenseDao.get(id);
    }

    @Override
    public Expense save(Expense expense) {
        expenseDao.save(expense);
        return expense;
    }
}
