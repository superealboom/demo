package cn.afuo.webtool.env;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("env")
@RestController
public class EnvController {


    private final Environment environment;

    public EnvController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping(value = "/author")
    public Object author() {
        String property = environment.getProperty("author.name");
        return environment.getProperty("author.name");
    }

}
