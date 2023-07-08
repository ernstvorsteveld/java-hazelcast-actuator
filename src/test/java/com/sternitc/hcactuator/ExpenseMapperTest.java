package com.sternitc.hcactuator;

import com.sternitc.hcactuator.expenses.Expense;
import com.sternitc.hcactuator.expenses.ExpenseMapper;
import com.sternitc.hcactuator.expenses.ExpensePayload;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseMapperTest {

    @Test
    public void should_map_to_domain() {
        var id = UUID.randomUUID().toString();
        var description = "Test description";
        var amount = 10.1;
        ExpensePayload payload = new ExpensePayload(id, description, amount);

        Expense expense = ExpenseMapper.INSTANCE.toDomain(payload);
        assertEquals(expense.getId(), id);
        assertEquals(expense.getDescription(), description);
        assertEquals(expense.getAmount(), amount);
    }

    @Test
    public void should_map_to_payload() {
        var id = UUID.randomUUID().toString();
        var description = "Test description";
        var amount = 10.1;
        var expense = Expense.builder().id(id).description(description).amount(amount).build();

        var payload = ExpenseMapper.INSTANCE.toResponse(expense);
        assertEquals(payload.id(), id);
        assertEquals(payload.description(), id);
        assertEquals(payload.amount(), id);
    }

}