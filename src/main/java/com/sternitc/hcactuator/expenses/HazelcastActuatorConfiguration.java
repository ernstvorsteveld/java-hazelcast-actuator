package com.sternitc.hcactuator.expenses;

import com.sternitc.hcactuator.expenses.dao.ExpenseDao;
import com.sternitc.hcactuator.expenses.dao.ExpenseDaoMap;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@Configuration
@EnableCaching
public class HazelcastActuatorConfiguration {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("expenses");
    }

    @Bean
    public ExpenseService expenseService(ExpenseDao expenseDao) {
        return new ExpenseServiceImpl(expenseDao);
    }

    @Bean
    public ExpenseDao expenseDao() {
        return new ExpenseDaoMap();
    }

    @Bean
    public InvokerCounter invokerCounter() {
        return new InvokerCounter();
    }

    @Bean
    public InvokeCounterAspect invokeCounterAspect(InvokerCounter counter) {
        return new InvokeCounterAspect(counter);
    }
}
