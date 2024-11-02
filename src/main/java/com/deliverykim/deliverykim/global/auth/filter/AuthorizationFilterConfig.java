package com.deliverykim.deliverykim.global.auth.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;

@Configuration
public class AuthorizationFilterConfig {

    @Bean
    public FilterRegistrationBean getAuthFilterRegistrationBean(AuthorizationFilter authorizationFilter) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(authorizationFilter);
        filterRegistrationBean.setOrder(Integer.MIN_VALUE);

        // 회원탈퇴
        filterRegistrationBean.addUrlPatterns("/api/v1/auth/withdrawal");

        return filterRegistrationBean;
    }

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

}
