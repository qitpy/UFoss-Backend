package com.smartdev.ufoss.config.DefaultDataConfig;

import com.smartdev.ufoss.entity.CategoryEntity;
import com.smartdev.ufoss.entity.CourseEntity;
import com.smartdev.ufoss.entity.InstructorEntity;
import com.smartdev.ufoss.entity.UserEntity;
import com.smartdev.ufoss.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DefaultConfigData {
    @Bean
    CommandLineRunner defaultDataConfig(
            CategoryRepository categoryRepository,
            CourseRepository courseRepository,
            InstructorRepository instructorRepository,
            LessonRepository lessonRepository,
            PaymentRepository paymentRepository,
            RateRepository rateRepository,
            UserRepository userRepository
    ) {
        return args -> {
            // Create User
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

            // Create Instructor
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
                    "Soraka",
                    "soraka@gmail.com",
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

            // Create Category
            CategoryEntity cateMusic = new CategoryEntity(
                    "music"
            );
            CategoryEntity cateIT = new CategoryEntity(
                    "IT-Programing"
            );
            CategoryEntity cateDesign = new CategoryEntity(
                    "Design"
            );
            CategoryEntity cateOffice = new CategoryEntity(
                    "Office Productivity"
            );
            CategoryEntity cateHealthyAndFitness = new CategoryEntity(
                    "Healthy and Fitness"
            );
            categoryRepository.saveAll(
                    List.of(
                            cateMusic, cateIT, cateDesign, cateOffice, cateHealthyAndFitness
                    )
            );

            // Create Course
            // IT category
            CourseEntity c1 = new CourseEntity(
                    "C++ Full Course",
                    "learning all about C++ in one course",
                    99.0,
                    "https://khaind.github.io/img/cpp_icon.png",
                    instructorRepository.findByEmail(instruc1.getEmail()).get(),
                    categoryRepository.findByName(cateIT.getName()).get()
            );
            CourseEntity c2 = new CourseEntity(
                    "Java Basic",
                    "learning Basic of Java",
                    99.0,
                    "https://lh3.googleusercontent.com/proxy/e4Jc1V4Okc_Xvs2S_rOjg2FPjwEd9mRjy85THmdpyJMcPcMTGIHCRqyH5XhSp9b_mPDmyAr5TvQVkGIJI9yaOUmzwH2-vIqO31FTx3aIBha6",
                    instructorRepository.findByEmail(instruc2.getEmail()).get(),
                    categoryRepository.findByName(cateIT.getName()).get()
            );
            CourseEntity c3 = new CourseEntity(
                    "Python BeautifulSoup",
                    "Crawling Data from any Website",
                    22.99,
                    "https://i.morioh.com/0eae3e7fa4.png",
                    instructorRepository.findByEmail(instruc3.getEmail()).get(),
                    categoryRepository.findByName(cateIT.getName()).get()
            );

            //Design Category
            CourseEntity c4 = new CourseEntity(
                    "The Ultimate Drawing Course - Beginner to Advanced",
                    "Learn the #1 most important building block of all art",
                    11.99,
                    "https://i.ytimg.com/vi/ljyPcX2EuQA/maxresdefault.jpg",
                    instructorRepository.findByEmail(instruc4.getEmail()).get(),
                    categoryRepository.findByName(cateDesign.getName()).get()
            );
            CourseEntity c5 = new CourseEntity(
                    "Complete Blender Creator: Learn 3D Modelling for Beginners",
                    "Use Blender to Create Beautiful 3D models for Video Games, 3D Printing & More. Beginners Level Course",
                    22.99,
                    "https://process.fs.teachablecdn.com/ADNupMnWyR7kCWRvm76Laz/resize=width:705/https://www.filepicker.io/api/file/G7ChKXpFScOhWP2HHZ6L",
                    instructorRepository.findByEmail(instruc5.getEmail()).get(),
                    categoryRepository.findByName(cateDesign.getName()).get()
            );
            CourseEntity c6 = new CourseEntity(
                    "Illustrator 2021 MasterClass",
                    "Master Adobe Illustrator with this in-depth training for all levels.",
                    13.99,
                    "https://coursemarks.com/wp-content/uploads/2020/11/3580811_a2df.jpg",
                    instructorRepository.findByEmail(instruc6.getEmail()).get(),
                    categoryRepository.findByName(cateDesign.getName()).get()
            );

            // Music Category
            CourseEntity c7 = new CourseEntity(
                    "Pianoforall - Incredible New Way To Learn Piano & Keyboard",
                    "Learn Piano in WEEKS not years. Play-By-Ear & learn to Read Music. Pop, Blues, Jazz, Ballads, Improvisation, Classical",
                    11.99,
                    "https://i.pinimg.com/originals/6c/4c/90/6c4c901210dff9d6faf47be022092a42.jpg",
                    instructorRepository.findByEmail(instruc7.getEmail()).get(),
                    categoryRepository.findByName(cateMusic.getName()).get()
            );
            CourseEntity c8 = new CourseEntity(
                    "Complete Guitar Lessons System - Beginner to Advanced",
                    "All-in-one Guitar Course, Fingerstyle Guitar, Blues Guitar, Acoustic Guitar, Electric Guitar & Fingerpicking Guitarra",
                    12.99,
                    "https://coursemarks.com/wp-content/uploads/2020/11/667186_6d70_5.jpg",
                    instructorRepository.findByEmail(instruc8.getEmail()).get(),
                    categoryRepository.findByName(cateMusic.getName()).get()
            );
            CourseEntity c9 = new CourseEntity(
                    "The Professional Guitar Masterclass",
                    "Learn The Tools Used By The World's Top Professional Guitar Players.",
                    16.99,
                    "https://cdnp3.stackassets.com/4f8d1831ae91827213409280ba0d283c7822ff7f/store/opt/596/298/397bb6dde3fc5ed87faa569eaf651d2672ad7ceca6f15555bb4a4952ab2c/product_39814_product_shot_wide.jpg",
                    instructorRepository.findByEmail(instruc9.getEmail()).get(),
                    categoryRepository.findByName(cateMusic.getName()).get()
            );

            // Office Productivity
            CourseEntity c10 = new CourseEntity(
                    "Microsoft Excel - Total Course",
                    "Excel with this A-Z Microsoft Excel Course. Microsoft Excel 2010, 2013, 2016, Excel 2019 and Office 365",
                    11.99,
                    "https://static.wixstatic.com/media/8ff445_dbec5fe234434d8ba1ef9a2d5a676213~mv2.jpg/v1/fill/w_1000,h_500,al_c,q_85/8ff445_dbec5fe234434d8ba1ef9a2d5a676213~mv2.jpg",
                    instructorRepository.findByEmail(instruc10.getEmail()).get(),
                    categoryRepository.findByName(cateOffice.getName()).get()
            );
            CourseEntity c11 = new CourseEntity(
                    "Microsoft Excel - Advanced Excel Formulas & Functions",
                    "Master 75+ MS Excel formulas and learn data analysis with a top Microsoft Excel & business intelligence instructor",
                    11.99,
                    "https://1.bp.blogspot.com/-6Vc_lfemBmQ/X2LoFwtHBhI/AAAAAAAAI8w/HbaUA36e6cwAlN5hbO5U7RIWTw_G4pOLQCLcBGAsYHQ/w1200-h630-p-k-no-nu/Microsoft-Excel-Advanced-Excel-Formulas-Functions.jpg",
                    instructorRepository.findByEmail(instruc11.getEmail()).get(),
                    categoryRepository.findByName(cateOffice.getName()).get()
            );

            CourseEntity c12 = new CourseEntity(
                    "Visually Effective Excel Dashboards",
                    "Actionable Excel Tips (Templates Included) You Can Use Right Now to Create Eye-Catching Microsoft Excel Dashboards",
                    11.99,
                    "https://1.bp.blogspot.com/-SmNiYjTNbss/YL5rFSGSx4I/AAAAAAAAI0E/fERrKh3PkGkYgHV5EFAZ6fpoRVWjJ2MXgCNcBGAsYHQ/s750/photo_2021-06-08_00-22-54.jpg",
                    instructorRepository.findByEmail(instruc12.getEmail()).get(),
                    categoryRepository.findByName(cateOffice.getName()).get()
            );

            // Healthy and Fitness Category
            CourseEntity c13 = new CourseEntity(
                    "Cognitive Behavioural Therapy (CBT) Practitioner Certificate",
                    "Become certified in time-proven Cognitive Behavioural Therapy to help people increase their mental health and wellbeing",
                    15.99,
                    "https://blog.vilmatech.com/wp-content/uploads/2020/03/cbt.jpg",
                    instructorRepository.findByEmail(instruc13.getEmail()).get(),
                    categoryRepository.findByName(cateHealthyAndFitness.getName()).get()
            );
            CourseEntity c14 = new CourseEntity(
                    "Herbalism :: Introduction & Medicine Making Certificate",
                    "Take charge of your health with herbal medicine. Using herbs and natural holistic medicine is easy, effective and safe.",
                    20.99,
                    "https://downloadfreecourse.com/uploads/images/2020/webp/image_750x_5eca56e4ed8d9.webp",
                    instructorRepository.findByEmail(instruc14.getEmail()).get(),
                    categoryRepository.findByName(cateHealthyAndFitness.getName()).get()
            );
            CourseEntity c15 = new CourseEntity(
                    "Become a SuperHuman: Naturally & Safely Boost Testosterone",
                    "Hack diet, exercise, & habits to safely boost your testosterone; the motivation, fitness, health, & happiness super drug",
                    18.99,
                    "https://downloadfreecourse.com/uploads/images/2020/webp/image_750x_5eca04dd86b03.webp",
                    instructorRepository.findByEmail(instruc15.getEmail()).get(),
                    categoryRepository.findByName(cateHealthyAndFitness.getName()).get()
            );
            CourseEntity c16 = new CourseEntity(
                    "Learn Military Close Combat Training | Captain Chris Pizzo",
                    "Military Hand-To-Hand Self Defense System Lets You Humiliate Younger, Tougher, BIGGER and More Experienced Attackers.",
                    11.99,
                    "https://coursemarks.com/wp-content/uploads/2020/11/225172_977f_4.jpg",
                    instructorRepository.findByEmail(instruc16.getEmail()).get(),
                    categoryRepository.findByName(cateHealthyAndFitness.getName()).get()
            );
            CourseEntity c17 = new CourseEntity(
                    "CBT for Depression, Anxiety, Phobias and Panic Attacks",
                    "Cognitive Behavioural Therapy for Depression, Anxiety, Phobias and Panic Attacks",
                    21.99,
                    "https://d3f1iyfxxz8i1e.cloudfront.net/courses/course_image/4f96e56a8caf.jpg",
                    instructorRepository.findByEmail(instruc17.getEmail()).get(),
                    categoryRepository.findByName(cateHealthyAndFitness.getName()).get()
            );

            // Music Category
            CourseEntity c18 = new CourseEntity(
                    "Read Music FAST!",
                    "Learn to read music using my unique method: just see a note on a piano score and play it on the keyboard straight away",
                    11.99,
                    "https://i.ytimg.com/vi/xzh0YzL0oqg/maxresdefault.jpg",
                    instructorRepository.findByEmail(instruc18.getEmail()).get(),
                    categoryRepository.findByName(cateMusic.getName()).get()
            );
            CourseEntity c19 = new CourseEntity(
                    "Music Theory for Electronic Producers - The Complete Course!",
                    "Join Successful students in Music Theory for Electronic Producers for Creating, Arranging, and Analysing Music Theory",
                    12.99,
                    "https://i.ytimg.com/vi/lZAazL2pYBI/maxresdefault.jpg",
                    instructorRepository.findByEmail(instruc19.getEmail()).get(),
                    categoryRepository.findByName(cateMusic.getName()).get()
            );
            CourseEntity c20 = new CourseEntity(
                    "Play Modern Blues Now",
                    "Take your playing to the next level using proven effective methods simplified so you can play amazing modern blues ASAP.",
                    12.99,
                    "https://coursemarks.com/wp-content/uploads/2020/11/561718_4ed9.jpg",
                    instructorRepository.findByEmail(instruc20.getEmail()).get(),
                    categoryRepository.findByName(cateMusic.getName()).get()
            );
            courseRepository.saveAll(
                    List.of(
                            c1, c2, c3, c4, c5,
                            c6, c7, c8, c9, c10,
                            c11, c12, c13, c14, c15,
                            c16, c17, c18, c19, c20
                    )
            );


        };
    }
}
