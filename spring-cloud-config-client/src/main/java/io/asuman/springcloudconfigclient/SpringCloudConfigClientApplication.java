package io.asuman.springcloudconfigclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringCloudConfigClientApplication {
    @Value("${db.connection: n/a}")
    private String dbConnection;

    @Value("${my.greeting: n/a}")
    private String greetingMessage;

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigClientApplication.class, args);
    }

    @GetMapping("/")
    public String getConfigString() {
        return "Config Server says: " + greetingMessage + "\nDB config: " + dbConnection;
    }
}
