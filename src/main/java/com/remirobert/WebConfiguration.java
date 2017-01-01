package com.remirobert;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by remirobert on 31/12/2016.
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    RequestHandlerInterceptor getAuthenticationInterceptor() {
        return new RequestHandlerInterceptor();
    }

    @Override
    public void addInterceptors (InterceptorRegistry registry) {
        System.out.println("configuration web register interceptor");
        registry.addInterceptor(getAuthenticationInterceptor());
    }
}
