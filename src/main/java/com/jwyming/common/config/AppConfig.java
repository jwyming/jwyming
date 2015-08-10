package com.jwyming.common.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;

import com.jwyming.common.web.ViewScope;

@Configuration
@ComponentScan(basePackages = "com.jwyming", excludeFilters = @ComponentScan.Filter(value = {Configuration.class, Controller.class}, type = FilterType.ANNOTATION))
@Import({ MessageConfig.class, TXConfig.class})

public class AppConfig {

    @Bean
    public static CustomScopeConfigurer customScopeConfigurer() {
        CustomScopeConfigurer customScopeConfigurer = new CustomScopeConfigurer();
        Map<String, Object> scopes = new HashMap<>();
        scopes.put("view", new ViewScope());
        customScopeConfigurer.setScopes(scopes);
        return customScopeConfigurer;
    }
}
