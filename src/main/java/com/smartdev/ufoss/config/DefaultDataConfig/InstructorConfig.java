package com.smartdev.ufoss.config.DefaultDataConfig;

import com.smartdev.ufoss.entity.InstructorEntity;
import com.smartdev.ufoss.repository.InstructorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class InstructorConfig{
    @Bean
    CommandLineRunner instructorCommandLineRunner(InstructorRepository instructorRepository) {
        return args -> {
            InstructorEntity instruc1 = new InstructorEntity(
                    "Yasuo",
                    "Damaged",
                    "yasuo@gmail.com",
                    "088668866",
                    "Teacher at Univ, have 3 experiment years of teaching at school"
            );
            InstructorEntity instruc2 = new InstructorEntity(
                    "Darius",
                    "Aram",
                    "darius@gmail.com",
                    "09968866",
                    "Teacher at Univ, have 3 experiment years of teaching at school"
            );
            InstructorEntity instruc3 = new InstructorEntity(
                    "Diana",
                    "Darkin",
                    "diana@gmail.com",
                    "088668866",
                    "Teacher at Univ, have 3 experiment years of teaching at school"
            );
            InstructorEntity instruc4 = new InstructorEntity(
                    "Cho'gath",
                    "Boss",
                    "chogath@gmail.com",
                    "088668866",
                    "Teacher at Univ, have 3 experiment years of teaching at school"
            );
            InstructorEntity instruc5 = new InstructorEntity(
                    "Kai'sa",
                    "Flight",
                    "kaisa@gmail.com",
                    "088668866",
                    "Teacher at Univ, have 3 experiment years of teaching at school"
            );
            InstructorEntity instruc6 = new InstructorEntity(
                    "Jhin",
                    "Cowboy",
                    "jhin@gmail.com",
                    "088668866",
                    "Teacher at Univ, have 3 experiment years of teaching at school"
            );
            InstructorEntity instruc7 = new InstructorEntity(
                    "Master",
                    "Yi",
                    "yi@gmail.com",
                    "088668866",
                    "Teacher at Univ, have 3 experiment years of teaching at school"
            );
            InstructorEntity instruc8 = new InstructorEntity(
                    "LeeSin",
                    "Sugar",
                    "lessin@gmail.com",
                    "088668866",
                    "Teacher at Univ, have 3 experiment years of teaching at school"
            );
            InstructorEntity instruc9 = new InstructorEntity(
                    "Rakan",
                    "SSG",
                    "rakan@gmail.com",
                    "088668866",
                    "Teacher at Univ, have 3 experiment years of teaching at school"
            );
            InstructorEntity instruc10 = new InstructorEntity(
                    "Zed",
                    "Championship",
                    "zed@gmail.com",
                    "088668866",
                    "Teacher at Univ, have 3 experiment years of teaching at school"
            );
            InstructorEntity instruc11 = new InstructorEntity(
                    "SKT-T1",
                    "Zed",
                    "zed@gmail.com",
                    "088668866",
                    "Teacher at Univ, have 3 experiment years of teaching at school"
            );
            InstructorEntity instruc12 = new InstructorEntity(
                    "FPX",
                    "Vayne",
                    "vayne@gmail.com",
                    "088668866",
                    "Teacher at Univ, have 3 experiment years of teaching at school"
            );
            InstructorEntity instruc13 = new InstructorEntity(
                    "Archlight",
                    "Varus",
                    "varus@gmail.com",
                    "088668866",
                    "Teacher at Univ, have 3 experiment years of teaching at school"
            );
            InstructorEntity instruc14 = new InstructorEntity(
                    "Tryndamere",
                    "Blood Moon",
                    "tryndamere@gmail.com",
                    "088668866",
                    "Teacher at Univ, have 3 experiment years of teaching at school"
            );
            InstructorEntity instruc15 = new InstructorEntity(
                    "High Noon",
                    "Thress",
                    "thress@gmail.com",
                    "088668866",
                    "Teacher at Univ, have 3 experiment years of teaching at school"
            );
            InstructorEntity instruc16 = new InstructorEntity(
                    "Shen",
                    "Samurai",
                    "jodie@gmail.com",
                    "088668866",
                    "Teacher at Univ, have 3 experiment years of teaching at school"
            );
            InstructorEntity instruc17 = new InstructorEntity(
                    "Frozen",
                    "Lissandra",
                    "frozen@gmail.com",
                    "088668866",
                    "Teacher at Univ, have 3 experiment years of teaching at school"
            );
            InstructorEntity instruc18 = new InstructorEntity(
                    "Kakarot",
                    "Sayan",
                    "kakarot@gmail.com",
                    "088668866",
                    "Teacher at Univ, have 3 experiment years of teaching at school"
            );
            InstructorEntity instruc19 = new InstructorEntity(
                    "Thor",
                    "Diesel",
                    "thor@gmail.com",
                    "088668866",
                    "Teacher at Univ, have 3 experiment years of teaching at school"
            );
            InstructorEntity instruc20 = new InstructorEntity(
                    "Vegeta",
                    "Bulma",
                    "vegeta@gmail.com",
                    "088668866",
                    "Teacher at Univ, have 3 experiment years of teaching at school"
            );

            instructorRepository.saveAll(
                    List.of(
                        instruc1, instruc2, instruc3, instruc4,
                        instruc5, instruc6, instruc7, instruc8,
                        instruc9, instruc10, instruc11, instruc12,
                        instruc13, instruc14, instruc15, instruc16,
                        instruc17, instruc18, instruc19, instruc20
                    )
            );
        };
    }
}
