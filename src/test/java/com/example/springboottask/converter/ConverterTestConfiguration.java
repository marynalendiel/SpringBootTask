package com.example.springboottask.converter;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ConverterTestConfiguration {
    @Bean
    public OrderDtoConverter orderDtoConverter() {
        return new OrderDtoConverter();
    }

    @Bean
    public UserDtoConverter userDtoConverter() {
        return new UserDtoConverter();
    }

    @Bean
    public OrderEntityConverter orderEntityConverter() {
        return new OrderEntityConverter();
    }

    @Bean
    public UserEntityConverter userEntityConverter() {
        return new UserEntityConverter();
    }
}
