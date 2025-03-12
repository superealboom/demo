package cn.afuo.webtool.validation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ValidatedServiceTest {

    @Autowired
    private ValidatedService validatedService;

    @Test
    public void interceptSize() {
        validatedService.interceptSize(5);
    }

}