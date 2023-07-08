package com.sternitc.hcactuator.expenses;

public record ExpensePayload(
        String id,
        String description,
        double amount
) {
}
