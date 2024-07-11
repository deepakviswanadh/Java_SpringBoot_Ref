package com.example.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    //return a new instance, whenever this bean is invoked
    //this is how we make it non singleton in nature
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}