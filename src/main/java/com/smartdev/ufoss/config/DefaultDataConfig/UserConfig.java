package com.smartdev.ufoss.config.DefaultDataConfig;

import com.smartdev.ufoss.entity.UserEntity;
import com.smartdev.ufoss.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            UserEntity bao = new UserEntity(
                    "Quoc",
                    "Bao",
                    "quocbao@gmail.com",
                    "0888866668",
                    null,
                    "baobao",
                    "baobao"
            );
            UserEntity quyet = new UserEntity(
                    "Quyet",
                    "Bao",
                    "maitocode@gmail.com",
                    "0888866668",
                    null,
                    "quyet",
                    "quyet"
            );
            UserEntity hai = new UserEntity(
                    "hai",
                    "kis",
                    "taka@gmail.com",
                    "0888866668",
                    null,
                    "hai",
                    "hai"
            );
            UserEntity hoang = new UserEntity(
                    "nhat",
                    "hoang",
                    "hoang@gmail.com",
                    "0888866668",
                    null,
                    "hoang",
                    "hoang"
            );
            UserEntity thiet = new UserEntity(
                    "thiet",
                    "truong",
                    "thiet@gmail.com",
                    "0888866668",
                    null,
                    "thiet",
                    "thiet"
            );

            userRepository.saveAll(
                    List.of(quyet, bao, hai, thiet, hoang)
            );
        };
    }
}
