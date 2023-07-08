package com.sternitc.hcactuator.expenses;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    ExpenseMapper INSTANCE = Mappers.getMapper(ExpenseMapper.class);

    Expense toDomain(ExpensePayload payload);

    ExpenseResponse toResponse(Expense expense);
}
