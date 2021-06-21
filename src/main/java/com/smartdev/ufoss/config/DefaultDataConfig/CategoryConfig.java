package com.smartdev.ufoss.config.DefaultDataConfig;

import com.smartdev.ufoss.entity.CategoryEntity;
import com.smartdev.ufoss.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CategoryConfig {

    @Bean
    CommandLineRunner categoryCommandLineRunner(CategoryRepository categoryRepository) {
        return args -> {
            CategoryEntity cate1 = new CategoryEntity(
                    "music"
            );
            CategoryEntity cate2 = new CategoryEntity(
                    "IT-Programing"
            );
            CategoryEntity cate3 = new CategoryEntity(
                    "Design"
            );
            CategoryEntity cate4 = new CategoryEntity(
                    "Office Productivity"
            );

            categoryRepository.saveAll(
                List.of(
                    cate1, cate2, cate3, cate4
                )
            );
        };
    }
}
