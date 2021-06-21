package com.smartdev.ufoss.config.DefaultDataConfig;

import com.smartdev.ufoss.entity.RateEntity;
import com.smartdev.ufoss.repository.RateRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RateConfig {

    @Bean
    CommandLineRunner rateCommandLineRunner(RateRepository rateRepository) {
        return args -> {
            RateEntity r1 = new RateEntity(
                    4
            );
            RateEntity r2 = new RateEntity(
                    5
            );
            RateEntity r3 = new RateEntity(
                    3
            );
            RateEntity r4 = new RateEntity(
                    5
            );

            rateRepository.saveAll(List.of(r1, r2, r3, r4));
        };
    }
}
