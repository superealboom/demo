package cn.afuo.webtool;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class WebToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebToolApplication.class, args);
    }
}
