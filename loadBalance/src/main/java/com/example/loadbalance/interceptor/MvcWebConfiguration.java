package com.example.loadbalance.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description: spring mvc 过滤拦截器
 * @author: tianci
 * @date: 2022/8/17 15:20
 */
@Configuration
public class MvcWebConfiguration implements WebMvcConfigurer {

    private final WebInterceptor webInterceptor;

    public MvcWebConfiguration(WebInterceptor webInterceptor) {
        this.webInterceptor = webInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns是拦截器需要拦截的请求路径
        // excludePathPatterns是不进行拦截的请求路径
        registry.addInterceptor(webInterceptor).excludePathPatterns("/css/**","/js/**","/pic/**");
    }
}
