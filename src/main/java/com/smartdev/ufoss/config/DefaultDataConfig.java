package com.smartdev.ufoss.config;

import com.smartdev.ufoss.dto.CategoryDTO;
import com.smartdev.ufoss.entity.*;

import com.smartdev.ufoss.repository.*;
import com.smartdev.ufoss.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Configuration
public class DefaultDataConfig {
    @Autowired
    CategoryService categoryService;

    @Bean
    CommandLineRunner dataInitial(
            CategoryRepository categoryRepository,
            CoursesRepository coursesRepository,
            InstructorRepository instructorRepository,
            LessonRepository lessonRepository,
            PaymentRepository paymentRepository,
            RateRepository rateRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            RoleRepository roleRepository,
            PermissionRepository permissionRepository
    ) {
        return args -> {

            // Create PERMISSION
            PermissionEntity pCourseRead = new PermissionEntity("course:read");
            PermissionEntity pCourseWrite = new PermissionEntity("course:write");
            /*PermissionEntity pCourseCreate = new PermissionEntity("course:create");
            PermissionEntity pCourseUpdate = new PermissionEntity("course:update");
            PermissionEntity pCourseDelete = new PermissionEntity("course:delete");*/
            PermissionEntity pUserRead = new PermissionEntity("user:read");
            PermissionEntity pUserWrite = new PermissionEntity("user:write");
            /*PermissionEntity pUserCreate = new PermissionEntity("user:create");
            PermissionEntity pUserUpdate = new PermissionEntity("user:update");
            PermissionEntity pUserDelete = new PermissionEntity("user:delete");*/
            PermissionEntity pLessonRead = new PermissionEntity("lesson:read");
            PermissionEntity pLessonWrite = new PermissionEntity("lesson:write");
            PermissionEntity pInstructorRead = new PermissionEntity("instructor:read");
            PermissionEntity pInstructorWrite = new PermissionEntity("instructor:write");
            PermissionEntity pRateRead = new PermissionEntity("rate:read");
            PermissionEntity pRateWrite = new PermissionEntity("rate:write");

            permissionRepository.saveAll(List.of(
                    pCourseRead, pCourseWrite, pUserRead,
                    pUserWrite, pLessonRead, pLessonWrite,
                    pInstructorRead, pInstructorWrite, pRateRead,
                    pRateWrite
            ));

            // Create ROLE
            RoleEntity rUser = new RoleEntity("USER");
            RoleEntity rAdmin = new RoleEntity("ADMIN");
            RoleEntity rINSTRUCTOR = new RoleEntity("INSTRUCTOR");

            rUser.addPermission(permissionRepository.findByName(pUserRead.getName()).get());
            rUser.addPermission(permissionRepository.findByName(pUserWrite.getName()).get());
            rUser.addPermission(permissionRepository.findByName(pCourseRead.getName()).get());
            rUser.addPermission(permissionRepository.findByName(pLessonRead.getName()).get());
            rUser.addPermission(permissionRepository.findByName(pInstructorRead.getName()).get());
            rUser.addPermission(permissionRepository.findByName(pRateRead.getName()).get());
            rUser.addPermission(permissionRepository.findByName(pRateWrite.getName()).get());

            rAdmin.addPermission(permissionRepository.findByName(pCourseRead.getName()).get());
            rAdmin.addPermission(permissionRepository.findByName(pCourseWrite.getName()).get());
            rAdmin.addPermission(permissionRepository.findByName(pUserRead.getName()).get());
            rAdmin.addPermission(permissionRepository.findByName(pUserWrite.getName()).get());
            rAdmin.addPermission(permissionRepository.findByName(pLessonRead.getName()).get());
            rAdmin.addPermission(permissionRepository.findByName(pLessonWrite.getName()).get());
            rAdmin.addPermission(permissionRepository.findByName(pInstructorRead.getName()).get());
            rAdmin.addPermission(permissionRepository.findByName(pInstructorWrite.getName()).get());
            rAdmin.addPermission(permissionRepository.findByName(pRateRead.getName()).get());
            rAdmin.addPermission(permissionRepository.findByName(pRateWrite.getName()).get());

            rINSTRUCTOR.addPermission(permissionRepository.findByName(pCourseRead.getName()).get());
            rINSTRUCTOR.addPermission(permissionRepository.findByName(pCourseWrite.getName()).get());
            rINSTRUCTOR.addPermission(permissionRepository.findByName(pLessonRead.getName()).get());
            rINSTRUCTOR.addPermission(permissionRepository.findByName(pLessonWrite.getName()).get());
            rINSTRUCTOR.addPermission(permissionRepository.findByName(pInstructorRead.getName()).get());
            rINSTRUCTOR.addPermission(permissionRepository.findByName(pInstructorWrite.getName()).get());
            rINSTRUCTOR.addPermission(permissionRepository.findByName(pRateRead.getName()).get());

            roleRepository.saveAll(List.of(rUser, rAdmin, rINSTRUCTOR));

            // Create User
            UserEntity bao = new UserEntity(
                    "Quoc",
                    "Bao",
                    "quocbao@gmail.com",
                    "0888866668",
                    "bao",
                    passwordEncoder.encode("bao"),
                    true
            );
            UserEntity quyet = new UserEntity(
                    "Quyet",
                    "Maito",
                    "maitocode@gmail.com",
                    "0888866668",
                    "quyet",
                    passwordEncoder.encode("quyet"),
                    true
            );
            UserEntity hai = new UserEntity(

                    "Tamaumi",
                    "KIS",
                    "ngochai20101998@gmail.com",
                    "0888866668",
                    "hai",
                    passwordEncoder.encode("hai"),
                    true
            );
            UserEntity hoang = new UserEntity(
                    "nhat",
                    "hoang",
                    "hoang@gmail.com",
                    "0888866668",
                    "hoang",
                    passwordEncoder.encode("hoang"),
                    true
            );
            UserEntity thiet = new UserEntity(
                    "thiet",
                    "truong",
                    "thiet@gmail.com",
                    "0888866668",
                    "thiet",
                    passwordEncoder.encode("thiet"),
                    true
            );
            UserEntity user = new UserEntity(
                    "User",
                    "system",
                    "user@gmail.com",
                    "0124541212",
                    "user",
                    passwordEncoder.encode("user"),
                    true
            );
            user.addRole(roleRepository.findByName(rUser.getName()).get());
            hai.addRole(roleRepository.findByName(rUser.getName()).get());
            thiet.addRole(roleRepository.findByName(rUser.getName()).get());
            quyet.addRole(roleRepository.findByName(rINSTRUCTOR.getName()).get());
            bao.addRole(roleRepository.findByName(rINSTRUCTOR.getName()).get());
            bao.addRole(roleRepository.findByName(rAdmin.getName()).get());
            hoang.addRole(roleRepository.findByName(rAdmin.getName()).get());
            userRepository.saveAll(
                    List.of(quyet, bao, hai, thiet, hoang, user)
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

            List<CategoryDTO> listCateDTO = new ArrayList<>();
            // Create Category
            CategoryEntity cateWeb = new CategoryEntity(
                    "Web Development"
            );
            CategoryEntity cateDesign = new CategoryEntity(
                    "Design"
            );
            CategoryEntity cateBusiness = new CategoryEntity(
                    "Business"
            );

            categoryRepository.saveAll(
                    List.of(
                            cateWeb, cateDesign, cateBusiness
                    )
            );

            // Create Course
            // IT category
            CourseEntity c1 = new CourseEntity(
                    "C++ Full Course",
                    "learning all about C++ in one course",
                    99.0,
                    "https://khaind.github.io/img/cpp_icon.png",
                    instruc1
            );
            CourseEntity c2 = new CourseEntity(
                    "Java Basic",
                    "learning Basic of Java",
                    99.0,
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSG6X8Lvc4P0tOpzliQfswFP0RB9R-sJh9HmQ&usqp=CAU",
                    instruc2
            );
            CourseEntity c3 = new CourseEntity(
                    "Python BeautifulSoup",
                    "Crawling Data from any Website",
                    22.99,
                    "https://i.morioh.com/0eae3e7fa4.png",
                    instruc3
            );
            //Design Category
            CourseEntity c4 = new CourseEntity(
                    "The Ultimate Drawing Course - Beginner to Advanced",
                    "Learn the #1 most important building block of all art",
                    11.99,
                    "https://i.ytimg.com/vi/ljyPcX2EuQA/maxresdefault.jpg",
                    instruc4
            );
            CourseEntity c5 = new CourseEntity(
                    "Complete Blender Creator: Learn 3D Modelling for Beginners",
                    "Use Blender to Create Beautiful 3D models for Video Games, 3D Printing & More. Beginners Level Course",
                    22.99,
                    "https://process.fs.teachablecdn.com/ADNupMnWyR7kCWRvm76Laz/resize=width:705/https://www.filepicker.io/api/file/G7ChKXpFScOhWP2HHZ6L",
                    instruc5
            );
            CourseEntity c6 = new CourseEntity(
                    "Illustrator 2021 MasterClass",
                    "Master Adobe Illustrator with this in-depth training for all levels.",
                    13.99,
                    "https://coursemarks.com/wp-content/uploads/2020/11/3580811_a2df.jpg",
                    instruc6
            );
            // Music Category
            CourseEntity c7 = new CourseEntity(
                    "Pianoforall - Incredible New Way To Learn Piano & Keyboard",
                    "Learn Piano in WEEKS not years. Play-By-Ear & learn to Read Music. Pop, Blues, Jazz, Ballads, Improvisation, Classical",
                    11.99,
                    "https://i.pinimg.com/originals/6c/4c/90/6c4c901210dff9d6faf47be022092a42.jpg",
                    instruc7
            );
            CourseEntity c8 = new CourseEntity(
                    "Complete Guitar Lessons System - Beginner to Advanced",
                    "All-in-one Guitar Course, Fingerstyle Guitar, Blues Guitar, Acoustic Guitar, Electric Guitar & Fingerpicking Guitarra",
                    12.99,
                    "https://coursemarks.com/wp-content/uploads/2020/11/667186_6d70_5.jpg",
                    instruc8
            );
            CourseEntity c9 = new CourseEntity(
                    "The Professional Guitar Masterclass",
                    "Learn The Tools Used By The World's Top Professional Guitar Players.",
                    16.99,
                    "https://cdnp3.stackassets.com/4f8d1831ae91827213409280ba0d283c7822ff7f/store/opt/596/298/397bb6dde3fc5ed87faa569eaf651d2672ad7ceca6f15555bb4a4952ab2c/product_39814_product_shot_wide.jpg",
                    instruc9
            );
            // Office Productivity
            CourseEntity c10 = new CourseEntity(
                    "Microsoft Excel - Total Course",
                    "Excel with this A-Z Microsoft Excel Course. Microsoft Excel 2010, 2013, 2016, Excel 2019 and Office 365",
                    11.99,
                    "https://static.wixstatic.com/media/8ff445_dbec5fe234434d8ba1ef9a2d5a676213~mv2.jpg/v1/fill/w_1000,h_500,al_c,q_85/8ff445_dbec5fe234434d8ba1ef9a2d5a676213~mv2.jpg",
                    instruc10
            );
            CourseEntity c11 = new CourseEntity(
                    "Microsoft Excel - Advanced Excel Formulas & Functions",
                    "Master 75+ MS Excel formulas and learn data analysis with a top Microsoft Excel & business intelligence instructor",
                    11.99,
                    "https://1.bp.blogspot.com/-6Vc_lfemBmQ/X2LoFwtHBhI/AAAAAAAAI8w/HbaUA36e6cwAlN5hbO5U7RIWTw_G4pOLQCLcBGAsYHQ/w1200-h630-p-k-no-nu/Microsoft-Excel-Advanced-Excel-Formulas-Functions.jpg",
                    instruc11
            );
            CourseEntity c12 = new CourseEntity(
                    "Visually Effective Excel Dashboards",
                    "Actionable Excel Tips (Templates Included) You Can Use Right Now to Create Eye-Catching Microsoft Excel Dashboards",
                    11.99,
                    "https://1.bp.blogspot.com/-SmNiYjTNbss/YL5rFSGSx4I/AAAAAAAAI0E/fERrKh3PkGkYgHV5EFAZ6fpoRVWjJ2MXgCNcBGAsYHQ/s750/photo_2021-06-08_00-22-54.jpg",
                    instruc12
            );
            // Healthy and Fitness Category
            CourseEntity c13 = new CourseEntity(
                    "Cognitive Behavioural Therapy (CBT) Practitioner Certificate",
                    "Become certified in time-proven Cognitive Behavioural Therapy to help people increase their mental health and wellbeing",
                    15.99,
                    "https://blog.vilmatech.com/wp-content/uploads/2020/03/cbt.jpg",
                    instruc13
            );
            CourseEntity c14 = new CourseEntity(
                    "Herbalism :: Introduction & Medicine Making Certificate",
                    "Take charge of your health with herbal medicine. Using herbs and natural holistic medicine is easy, effective and safe.",
                    20.99,
                    "https://downloadfreecourse.com/uploads/images/2020/webp/image_750x_5eca56e4ed8d9.webp",
                    instruc14
            );
            CourseEntity c15 = new CourseEntity(
                    "Become a SuperHuman: Naturally & Safely Boost Testosterone",
                    "Hack diet, exercise, & habits to safely boost your testosterone; the motivation, fitness, health, & happiness super drug",
                    18.99,
                    "https://downloadfreecourse.com/uploads/images/2020/webp/image_750x_5eca04dd86b03.webp",
                    instruc15
            );
            CourseEntity c16 = new CourseEntity(
                    "Learn Military Close Combat Training | Captain Chris Pizzo",
                    "Military Hand-To-Hand Self Defense System Lets You Humiliate Younger, Tougher, BIGGER and More Experienced Attackers.",
                    11.99,
                    "https://coursemarks.com/wp-content/uploads/2020/11/225172_977f_4.jpg",
                    instruc16
            );
            CourseEntity c17 = new CourseEntity(
                    "CBT for Depression, Anxiety, Phobias and Panic Attacks",
                    "Cognitive Behavioural Therapy for Depression, Anxiety, Phobias and Panic Attacks",
                    21.99,
                    "https://d3f1iyfxxz8i1e.cloudfront.net/courses/course_image/4f96e56a8caf.jpg",
                    instruc17
            );
            // Music Category
            CourseEntity c18 = new CourseEntity(
                    "Read Music FAST!",
                    "Learn to read music using my unique method: just see a note on a piano score and play it on the keyboard straight away",
                    11.99,
                    "https://i.ytimg.com/vi/xzh0YzL0oqg/maxresdefault.jpg",
                    instruc18
            );
            CourseEntity c19 = new CourseEntity(
                    "Music Theory for Electronic Producers - The Complete Course!",
                    "Join Successful students in Music Theory for Electronic Producers for Creating, Arranging, and Analysing Music Theory",
                    12.99,
                    "https://i.ytimg.com/vi/lZAazL2pYBI/maxresdefault.jpg",
                    instruc19
            );
            CourseEntity c20 = new CourseEntity(
                    "Play Modern Blues Now",
                    "Take your playing to the next level using proven effective methods simplified so you can play amazing modern blues ASAP.",
                    12.99,
                    "https://coursemarks.com/wp-content/uploads/2020/11/561718_4ed9.jpg",
                    instruc20
            );

            c1.setCategory(cateWeb);
            c2.setCategory(cateWeb);
            c3.setCategory(cateWeb);
            c4.setCategory(cateWeb);
            c5.setCategory(cateWeb);
            c6.setCategory(cateWeb);
            c7.setCategory(cateWeb);
            c8.setCategory(cateWeb);
            c9.setCategory(cateWeb);
            c10.setCategory(cateWeb);
            c11.setCategory(cateBusiness);
            c12.setCategory(cateBusiness);
            c13.setCategory(cateBusiness);
            c14.setCategory(cateBusiness);
            c15.setCategory(cateBusiness);
            c16.setCategory(cateDesign);
            c17.setCategory(cateDesign);
            c18.setCategory(cateDesign);
            c19.setCategory(cateDesign);
            c20.setCategory(cateDesign);

            coursesRepository.saveAll(
                    List.of(
                            c1, c2, c3, c4, c5,
                            c6, c7, c8, c9, c10,
                            c11, c12, c13, c14, c15,
                            c16, c17, c18, c19, c20
                    )
            );

            // Create Lesson
            LessonEntity l1 = new LessonEntity(
                    "https://www.youtube.com/watch?v=C7dPqrmDWxs&list=PLGYPpIsdZKnLRU3hBKDmUBRdzVdM0rS0z",
                    "Pharrell Williams - Happy (Official Video)",
                    "Pharrell Williams  Dear  G  I  R  L  Tour Dates",
                    coursesRepository.findAll().get(0)
            );
            LessonEntity l2 = new LessonEntity(
                    "https://www.youtube.com/watch?v=9bZkp7q19f0",
                    "PSY - GANGNAM STYLE(강남스타일) M/V",
                    "YG Entertainment Inc. (Music)",
                    coursesRepository.findAll().get(1)
            );
            LessonEntity l3 = new LessonEntity(
                    "https://www.youtube.com/watch?v=fWNaR-rxAic",
                    "Carly Rae Jepsen - Call Me Maybe",
                    "Get E•MO•TION on iTunes now:Sign up for Carly Rae Jepsen news here: Music video by Carly Rae Jepsen performing Call Me Maybe. (C) 2011 604 Records Inc.\n" +
                            "#VEVOCertified on June 8, 2012.",
                    coursesRepository.findAll().get(2)
            );
            LessonEntity l4 = new LessonEntity(
                    "https://www.youtube.com/watch?v=I-QfPUz1es8",
                    "Imagine Dragons - Bad Liar",
                    "Get Origins, ft. Natural, Zero, Machine and Bad Liar, out now: http://smarturl.it/OriginsID\n" +
                            "\n" +
                            "Directed By: Ryan Reichenfeld\n" +
                            "Dancer: Autumn Miller",
                    coursesRepository.findAll().get(3)
            );
            LessonEntity l5 = new LessonEntity(
                    "https://www.youtube.com/watch?v=gH476CxJxfg",
                    "Daniel Powter - Bad Day (Official Music Video) | Warner Vault",
                    "Bad Day by Daniel Powter from the album Daniel Powter © 2005\n" +
                            "\uD83D\uDD14  Subscribe & Turn on notifications to stay updated with new uploads!",
                    coursesRepository.findAll().get(4)
            );
            LessonEntity l6 = new LessonEntity(
                    "https://www.youtube.com/watch?v=dbK5bC9tmwM",
                    "[Vietsub+Lyrics] The Show - Lenka",
                    "I'm just a little bit caught in the middle\n" +
                            "Life is a maze and love is a riddle\n" +
                            "I don't know where to go, can't do it alone\n" +
                            "I've tried and I don't know why",
                    coursesRepository.findAll().get(5)
            );
            LessonEntity l7 = new LessonEntity(
                    "https://www.youtube.com/watch?v=gBmPZjOUppI",
                    "[Vietsub+Lyrics] Proud Of You - Fiona Fung",
                    "Love in your eyes\n" +
                            "Sitting silent by my side\n" +
                            "Going on, holding hand, walking trough the nights\n" +
                            "Hold me up, hol me tight, lift me up to touch the sky\n" +
                            "Teaching me to love with heart, helping me open my mind\n" +
                            "I can fly, i'm proud that I can fly",
                    coursesRepository.findAll().get(6)
            );
            LessonEntity l8 = new LessonEntity(
                    "https://www.youtube.com/watch?v=3gK_2XdjOdY",
                    "Titanic - My Heart Will Go On (Music Video)",
                    "Every night in my dreams\n" +
                            "I see you, I feel you\n" +
                            "That is how I know you go on",
                    coursesRepository.findAll().get(7)
            );
            LessonEntity l9 = new LessonEntity(
                    "https://www.youtube.com/watch?v=ulOb9gIGGd0",
                    "Westlife - My Love (Official Video)",
                    "An empty street, an empty house\n" +
                            "A hole inside my heart\n" +
                            "I'm all alone, the rooms are getting smaller.",
                    coursesRepository.findAll().get(8)
            );
            LessonEntity l10 = new LessonEntity(
                    "https://www.youtube.com/watch?v=64VZUNTmGQM",
                    "PARK BOM - YOU AND I M/V",
                    "That was the most played song on the radio in Korea’s HISTORY for a good time! Truly a masterpiece as Park Bom herself described",
                    coursesRepository.findAll().get(9)
            );
            LessonEntity l11 = new LessonEntity(
                    "https://www.youtube.com/watch?v=-Plg9lj6YwQ",
                    "The way to kick Love",
                    "DuoQuyenTinhYeu",
                    coursesRepository.findAll().get(10)
            );
            LessonEntity l12 = new LessonEntity(
                    "https://www.youtube.com/watch?v=tLtxoa1RAyE",
                    "Aloha | English Version | Video Lyrics",
                    "Oh the candlelight shimmers in the night\n" +
                            "We promise our love with glasses of wine\n" +
                            "I will always be here to protect and hold you tight\n" +
                            "Cause you’re the only one who trusts me",
                    coursesRepository.findAll().get(11)
            );
            LessonEntity l13 = new LessonEntity(
                    "https://www.youtube.com/watch?v=pBTp2RWxq-s",
                    "[Vietsub+Lyrics] I Do - 911",
                    "My whole world changed from the moment I met you\n" +
                            "And it would never be the same\n" +
                            "Felt like I knew that I'd always love you\n" +
                            "From the moment I heard your name\n" +
                            "Everything was perfect, I knew this love is worth it\n" +
                            "Our own miracle in the makin",
                    coursesRepository.findAll().get(12)
            );
            LessonEntity l14 = new LessonEntity(
                    "https://www.youtube.com/watch?v=nQY4dIxY1H4",
                    "Chris Medina - What Are Words (Official Video)",
                    "Music video by Chris Medina performing What Are Words. (C) 2011 19 Recordings, Inc.",
                    coursesRepository.findAll().get(13)
            );
            LessonEntity l15 = new LessonEntity(
                    "https://www.youtube.com/watch?v=btDd9rOlc2k",
                    "BIGBANG - MONSTER M/V",
                    "bukan monster. hanya seseorang yang diam-diam patah hati tanpa dia tau, aku pernah jatuh cinta.",
                    coursesRepository.findAll().get(14)
            );
            LessonEntity l16 = new LessonEntity(
                    "https://www.youtube.com/watch?v=amOSaNX7KJg",
                    "숀 (SHAUN) - 웨이백홈 (Way Back Home) [Lyric Video]",
                    "For those who think English ver made this song popular.\n" +
                            "This song got popular before English version was released, incase you don't know in korean music shows and charts this song topped Bts and Blackpink. ",
                    coursesRepository.findAll().get(15)
            );
            LessonEntity l17 = new LessonEntity(
                    "https://www.youtube.com/watch?v=h0UUqTCczHQ",
                    "lofi hip hop radio \uD83C\uDF31 beats to relax/study to",
                    "All pictures are collected by me or sent by fans, I just create more. So if there is any problem related to copyright, email me first and I will take care of it immediately!\n" +
                            "Thank you very much!!!",
                    coursesRepository.findAll().get(16)
            );
            LessonEntity l18 = new LessonEntity(
                    "https://www.youtube.com/watch?v=63nQdUoo388",
                    "Why so sad ? Lofi hip hop mix~ Stress Relief, Aesthetic Music",
                    "All pictures are collected by me or sent by fans, I just create more. So if there is any problem related to copyright, email me first and I will take care of it immediately!",
                    coursesRepository.findAll().get(17)
            );
            LessonEntity l19 = new LessonEntity(
                    "https://www.youtube.com/watch?v=Id6ARZyuxaw",
                    "Xe Đạp x Anh Yêu Em Nhiều Lắm - Nhạc Lofi Cực Chill Nhẹ Nhàng Thư Giãn 2021",
                    " Xe Đạp x Anh Yêu Em Nhiều Lắm\n" +
                            "Nhạc Lofi Cực Chill Nhẹ Nhàng Thư Giãn 2021\n" +
                            "More about Em Ơi",
                    coursesRepository.findAll().get(18)
            );
            LessonEntity l20 = new LessonEntity(
                    "https://www.youtube.com/watch?v=ArQHATe9zk0",
                    "Em Đi Xa Nơi Phương Trời Chỉ Có Mỗi Anh Nơi Này - Nhạc Lofi Chill 2021 - Mình Anh Nơi Này, Nàng Thơ",
                    "Em Đi Xa Nơi Phương Trời Chỉ Có Mỗi Anh Nơi Này" +
                            "Nhạc Lofi  Chill 2021 - Mình Anh Nơi Này, Nàng Thơ" +
                            " ore about Em Ơi",
                    coursesRepository.findAll().get(19)
            );
            lessonRepository.saveAll(
                    List.of(
                            l1, l2, l3, l4, l5,
                            l6, l7, l8, l9, l10,
                            l11, l12, l13, l14, l15,
                            l16, l17, l18, l19, l20
                    )
            );

            // Create Payment
            PaymentEntity p1 = new PaymentEntity(
                    Calendar.getInstance().getTime().toString(),
                    userRepository.findByUsername(user.getUsername()).get(),
                    coursesRepository.findAll().stream().findFirst().get()
            );
            PaymentEntity p2 = new PaymentEntity(
                    Calendar.getInstance().getTime().toString(),
                    userRepository.findByUsername(user.getUsername()).get(),
                    coursesRepository.findAll().get(18)
            );
            PaymentEntity p3 = new PaymentEntity(
                    Calendar.getInstance().getTime().toString(),
                    userRepository.findByUsername(user.getUsername()).get(),
                    coursesRepository.findAll().get(10)
            );
            PaymentEntity p4 = new PaymentEntity(
                    Calendar.getInstance().getTime().toString(),
                    userRepository.findByUsername(user.getUsername()).get(),
                    coursesRepository.findAll().get(4)
            );

            paymentRepository.saveAll(List.of(p1, p2, p3, p4));


            // Create Rate
            RateEntity r1 = new RateEntity(
                    4,
                    userRepository.findByUsername(user.getUsername()).get(),
                    coursesRepository.findAll().stream().findFirst().get()
            );
            RateEntity r2 = new RateEntity(
                    5,
                    userRepository.findByUsername(user.getUsername()).get(),
                    coursesRepository.findAll().get(18)
            );
            RateEntity r3 = new RateEntity(
                    3,
                    userRepository.findByUsername(user.getUsername()).get(),
                    coursesRepository.findAll().get(10)
            );
            RateEntity r4 = new RateEntity(
                    5,
                    userRepository.findByUsername(user.getUsername()).get(),
                    coursesRepository.findAll().get(4)
            );
            RateEntity r5 = new RateEntity(
                    4,
                    userRepository.findByUsername(user.getUsername()).get(),
                    coursesRepository.findAll().get(10)
            );
            RateEntity r6 = new RateEntity(
                    2,
                    userRepository.findByUsername(user.getUsername()).get(),
                    coursesRepository.findAll().stream().findFirst().get()
            );
            RateEntity r7 = new RateEntity(
                    4,
                    userRepository.findByUsername(user.getUsername()).get(),
                    coursesRepository.findAll().get(18)
            );
            RateEntity r8 = new RateEntity(
                    3,
                    userRepository.findByUsername(user.getUsername()).get(),
                    coursesRepository.findAll().get(10)
            );
            RateEntity r9 = new RateEntity(
                    1,
                    userRepository.findByUsername(user.getUsername()).get(),
                    coursesRepository.findAll().get(4)
            );
            RateEntity r11 = new RateEntity(
                    4,
                    userRepository.findByUsername(user.getUsername()).get(),
                    coursesRepository.findAll().stream().findFirst().get()
            );
            RateEntity r12 = new RateEntity(
                    4,
                    userRepository.findByUsername(user.getUsername()).get(),
                    coursesRepository.findAll().get(18)
            );
            RateEntity r13 = new RateEntity(
                    3,
                    userRepository.findByUsername(user.getUsername()).get(),
                    coursesRepository.findAll().get(10)
            );
            RateEntity r14 = new RateEntity(
                    2,
                    userRepository.findByUsername(user.getUsername()).get(),
                    coursesRepository.findAll().get(4)
            );
            RateEntity r15 = new RateEntity(
                    2,
                    userRepository.findByUsername(hai.getUsername()).get(),
                    coursesRepository.findAll().get(4)
            );
            RateEntity r16 = new RateEntity(
                    3,
                    userRepository.findByUsername(thiet.getUsername()).get(),
                    coursesRepository.findAll().get(7)
            );
            RateEntity r17 = new RateEntity(
                    2,
                    userRepository.findByUsername(hoang.getUsername()).get(),
                    coursesRepository.findAll().get(7)
            );
            rateRepository.saveAll(List.of(r1, r2, r3, r4, r5, r6, r7, r8,
                    r9, r11, r12, r13, r14, r15, r1));
        };
    }
}
