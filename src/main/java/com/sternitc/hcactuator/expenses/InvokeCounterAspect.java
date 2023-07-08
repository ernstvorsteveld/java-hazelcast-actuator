package com.sternitc.hcactuator.expenses;

import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@AllArgsConstructor
@Aspect
public class InvokeCounterAspect {

    private InvokerCounter counter;

    @Pointcut("target(com.sternitc.hcactuator.expenses.dao.ExpenseDao)")
    public void daoClassMethod() {};

    @Before("daoClassMethod()")
    public void addCounter(JoinPoint jp) {
        counter.add();
    }
}
