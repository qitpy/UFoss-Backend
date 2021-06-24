/*
package com.smartdev.ufoss.config.DefaultDataConfig;

import com.smartdev.ufoss.entity.PaymentEntity;
import com.smartdev.ufoss.repository.PaymentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;
import java.util.List;

@Configuration
public class PaymentConfig {

    @Bean
    CommandLineRunner PaymentCommandLineRunner(PaymentRepository paymentRepository) {
        return args -> {
            PaymentEntity p1 = new PaymentEntity(
                    Calendar.getInstance().getTime().toString()
            );
            PaymentEntity p2 = new PaymentEntity(
                    Calendar.getInstance().getTime().toString()
            );
            PaymentEntity p3 = new PaymentEntity(
                    Calendar.getInstance().getTime().toString()
            );

            paymentRepository.saveAll(List.of(p1, p2, p3));
        };
    }
}
*/
