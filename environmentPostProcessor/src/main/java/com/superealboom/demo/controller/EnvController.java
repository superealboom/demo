package com.superealboom.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tianci
 * @description 测试env
 * @date 2022/4/19 9:29
 */
@RestController
public class EnvController {


    private final Environment environment;

    public EnvController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping(value = "/testEnv")
    public Object testEnv() {
        return environment.getProperty("spring.datasource.url");
    }

}
