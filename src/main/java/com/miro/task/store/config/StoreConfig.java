package com.miro.task.store.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.miro.task.store.IWidgetStore;

@Configuration
public class StoreConfig {

    @Autowired
    private ApplicationContext context;

    @Bean
    public IWidgetStore WidgetStore(@Value("${app.store.current}") String qualifier) {
        return (IWidgetStore) context.getBean(qualifier);
    }
}