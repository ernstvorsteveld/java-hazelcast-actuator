package com.sternitc.hcactuator.expenses;

public record ExpenseResponse(
        String id,
        String description,

        double amount
) {
}
