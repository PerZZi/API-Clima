package com.example.ApiClima.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.atomic.AtomicInteger;

@Configuration
@EnableScheduling
public class RateLimitConfig {

    @Bean
    @Scope("singleton")
    public AtomicInteger requestCounter() {
        return new AtomicInteger(0);
    }

    @Scheduled(fixedRate = 3600000)
    public void resetCounter() {
        requestCounter().set(0);
    }
}
