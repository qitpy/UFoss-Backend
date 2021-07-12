package com.smartdev.ufoss;

import com.smartdev.ufoss.config.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class UFossApplication {

    public static void main(String[] args) {
        SpringApplication.run(UFossApplication.class, args);
    }
}
