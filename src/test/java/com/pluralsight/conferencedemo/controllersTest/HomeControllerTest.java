package com.pluralsight.conferencedemo.controllersTest;

import com.pluralsight.conferencedemo.controllers.HomeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class HomeControllerTest {

    @Autowired
    private HomeController homeController;
    @Test
    void CheckGetStatus() {
        // given
        String appVersion = "1.0.0";

        // when
        Map excepted = homeController.getStatus();

        // then
        assert(appVersion.equals(excepted.get("app-version")));
    }
}
