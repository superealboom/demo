package com.superealboom.demo.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.stereotype.Component;

import java.util.Properties;


/**
 * @description: 手动添加到yml
 * @author: tianci
 * @date: 2022/4/19 9:28
 */
@Component
public class AppEnvPostProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        MutablePropertySources propertySources = environment.getPropertySources();
        Properties properties = new Properties();
        properties.setProperty("spring.datasource.driver-class-name", "org.postgresql.Driver");
        properties.setProperty("spring.datasource.url", "jdbc:postgresql://192.168.177.132:5432/postgres");
        properties.setProperty("spring.datasource.username", "root");
        properties.setProperty("spring.datasource.password", "123456");
        propertySources.addLast(new PropertiesPropertySource("init",properties));
    }
}