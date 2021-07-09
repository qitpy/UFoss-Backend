
// package com.smartdev.ufoss.config;

// import com.smartdev.ufoss.entity.*;

// import com.smartdev.ufoss.repository.*;
// import com.smartdev.ufoss.service.CategoryService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.crypto.password.PasswordEncoder;

// import java.util.List;

// @Configuration
// public class DefaultDataConfig {
//     @Autowired
//     CategoryService categoryService;

//     @Bean
//     CommandLineRunner dataInitial(
//             CategoryRepository categoryRepository,
//             CoursesRepository coursesRepository,
//             InstructorRepository instructorRepository,
//             LessonRepository lessonRepository,
//             RateRepository rateRepository,
//             UserRepository userRepository,
//             PasswordEncoder passwordEncoder,
//             RoleRepository roleRepository,
//             PermissionRepository permissionRepository
//     ) {
//         return args -> {

//             // Create PERMISSION
//             PermissionEntity pCourseRead = new PermissionEntity("course:read");
//             PermissionEntity pCourseWrite = new PermissionEntity("course:write");
//             /*PermissionEntity pCourseCreate = new PermissionEntity("course:create");
//             PermissionEntity pCourseUpdate = new PermissionEntity("course:update");
//             PermissionEntity pCourseDelete = new PermissionEntity("course:delete");*/
//             PermissionEntity pUserRead = new PermissionEntity("user:read");
//             PermissionEntity pUserWrite = new PermissionEntity("user:write");
//             /*PermissionEntity pUserCreate = new PermissionEntity("user:create");
//             PermissionEntity pUserUpdate = new PermissionEntity("user:update");
//             PermissionEntity pUserDelete = new PermissionEntity("user:delete");*/
//             PermissionEntity pLessonRead = new PermissionEntity("lesson:read");
//             PermissionEntity pLessonWrite = new PermissionEntity("lesson:write");
//             PermissionEntity pInstructorRead = new PermissionEntity("instructor:read");
//             PermissionEntity pInstructorWrite = new PermissionEntity("instructor:write");
//             PermissionEntity pRateRead = new PermissionEntity("rate:read");
//             PermissionEntity pRateWrite = new PermissionEntity("rate:write");

//             permissionRepository.saveAll(List.of(
//                     pCourseRead, pCourseWrite, pUserRead,
//                     pUserWrite, pLessonRead, pLessonWrite,
//                     pInstructorRead, pInstructorWrite, pRateRead,
//                     pRateWrite
//             ));

//             // Create ROLE
//             RoleEntity rUser = new RoleEntity("USER");
//             RoleEntity rAdmin = new RoleEntity("ADMIN");
//             RoleEntity rINSTRUCTOR = new RoleEntity("INSTRUCTOR");

//             rUser.addPermission(permissionRepository.findByName(pUserRead.getName()).get());
//             rUser.addPermission(permissionRepository.findByName(pUserWrite.getName()).get());
//             rUser.addPermission(permissionRepository.findByName(pCourseRead.getName()).get());
//             rUser.addPermission(permissionRepository.findByName(pLessonRead.getName()).get());
//             rUser.addPermission(permissionRepository.findByName(pInstructorRead.getName()).get());
//             rUser.addPermission(permissionRepository.findByName(pRateRead.getName()).get());
//             rUser.addPermission(permissionRepository.findByName(pRateWrite.getName()).get());

//             rAdmin.addPermission(permissionRepository.findByName(pCourseRead.getName()).get());
//             rAdmin.addPermission(permissionRepository.findByName(pCourseWrite.getName()).get());
//             rAdmin.addPermission(permissionRepository.findByName(pUserRead.getName()).get());
//             rAdmin.addPermission(permissionRepository.findByName(pUserWrite.getName()).get());
//             rAdmin.addPermission(permissionRepository.findByName(pLessonRead.getName()).get());
//             rAdmin.addPermission(permissionRepository.findByName(pLessonWrite.getName()).get());
//             rAdmin.addPermission(permissionRepository.findByName(pInstructorRead.getName()).get());
//             rAdmin.addPermission(permissionRepository.findByName(pInstructorWrite.getName()).get());
//             rAdmin.addPermission(permissionRepository.findByName(pRateRead.getName()).get());
//             rAdmin.addPermission(permissionRepository.findByName(pRateWrite.getName()).get());

//             rINSTRUCTOR.addPermission(permissionRepository.findByName(pCourseRead.getName()).get());
//             rINSTRUCTOR.addPermission(permissionRepository.findByName(pCourseWrite.getName()).get());
//             rINSTRUCTOR.addPermission(permissionRepository.findByName(pLessonRead.getName()).get());
//             rINSTRUCTOR.addPermission(permissionRepository.findByName(pLessonWrite.getName()).get());
//             rINSTRUCTOR.addPermission(permissionRepository.findByName(pInstructorRead.getName()).get());
//             rINSTRUCTOR.addPermission(permissionRepository.findByName(pInstructorWrite.getName()).get());
//             rINSTRUCTOR.addPermission(permissionRepository.findByName(pRateRead.getName()).get());

//             roleRepository.saveAll(List.of(rUser, rAdmin, rINSTRUCTOR));

//             // Create User
//             UserEntity bao = new UserEntity(
//                     "Quoc",
//                     "Bao",
//                     "quocbao@gmail.com",
//                     "0888866668",
//                     "bao",
//                     passwordEncoder.encode("bao"),
//                     true
//             );
//             UserEntity quyet = new UserEntity(
//                     "Quyet",
//                     "Maito",
//                     "maitocode@gmail.com",
//                     "0888866668",
//                     "quyet",
//                     passwordEncoder.encode("quyet"),
//                     true
//             );
//             UserEntity hai = new UserEntity(

//                     "Tamaumi",
//                     "KIS",
//                     "ngochai20101998@gmail.com",
//                     "0888866668",
//                     "hai",
//                     passwordEncoder.encode("hai"),
//                     true
//             );
//             UserEntity hoang = new UserEntity(
//                     "nhat",
//                     "hoang",
//                     "hoang@gmail.com",
//                     "0888866668",
//                     "hoang",
//                     passwordEncoder.encode("hoang"),
//                     true
//             );
//             UserEntity thiet = new UserEntity(
//                     "thiet",
//                     "truong",
//                     "thiet@gmail.com",
//                     "0888866668",
//                     "thiet",
//                     passwordEncoder.encode("thiet"),
//                     true
//             );
//             UserEntity user = new UserEntity(
//                     "User",
//                     "system",
//                     "user@gmail.com",
//                     "0124541212",
//                     "user",
//                     passwordEncoder.encode("user"),
//                     true
//             );
//             user.addRole(roleRepository.findByName(rUser.getName()).get());
//             hai.addRole(roleRepository.findByName(rUser.getName()).get());
//             thiet.addRole(roleRepository.findByName(rUser.getName()).get());
//             quyet.addRole(roleRepository.findByName(rINSTRUCTOR.getName()).get());
//             bao.addRole(roleRepository.findByName(rINSTRUCTOR.getName()).get());
//             bao.addRole(roleRepository.findByName(rAdmin.getName()).get());
//             hoang.addRole(roleRepository.findByName(rAdmin.getName()).get());
//             userRepository.saveAll(
//                     List.of(quyet, bao, hai, thiet, hoang, user)
//             );

//             // Create Instructor
//             InstructorEntity instruc1 = new InstructorEntity(
//                     "Colt",
//                     "Steele",
//                     "coltSteele@gmail.com",
//                     "088668813",
//                     "Hi! I'm Colt. I'm a developer with a serious love for teaching. I've spent the last few years teaching people to program at 2 different immersive bootcamps where I've helped hundreds of people become web developers and change their lives. My graduates work at companies like Google, Salesforce, and Square."
//             );
//             InstructorEntity instruc2 = new InstructorEntity(
//                     "Jose",
//                     "Portilla",
//                     "josePortilla@gmail.com",
//                     "088668823",
//                     "ose Marcial Portilla has a BS and MS in Mechanical Engineering from Santa Clara University and years of experience as a professional instructor and trainer for Data Science and programming"
//             );
//             InstructorEntity instruc3 = new InstructorEntity(
//                     "Kirill",
//                     "Eremenko",
//                     "kirillEremenko@gmail.com",
//                     "088668866",
//                     "My name is Kirill Eremenko and I am super-psyched that you are reading this!.Professionally, I am a Data Science management consultant with over five years of experience in finance, retail, transport and other industries"
//             );
//             InstructorEntity instruc4 = new InstructorEntity(
//                     "Hadelin de",
//                     "Ponteves",
//                     "HadelinDePonteves@gmail.com",
//                     "088668234",
//                     "Hadelin is the co-founder and CEO at BlueLife AI, which leverages the power of cutting edge Artificial Intelligence to empower businesses to make massive profits by innovating, automating processes and maximizing efficiency."
//             );
//             InstructorEntity instruc5 = new InstructorEntity(
//                     "Jean-Paul",
//                     "Roberts",
//                     "paulRobrets@gmail.com",
//                     "088662166",
//                     "I am a programmer with over 30 years' experience gained in a range of industries, the last 16 with IBM."
//             );
//             InstructorEntity instruc6 = new InstructorEntity(
//                     "Tim",
//                     "Buchalka",
//                     "timBuchalka@gmail.com",
//                     "088667766",
//                     "Tim's been a professional software developer for over 35 years.  During his career, he has worked for major companies such as Fujitsu, Mitsubishi, and Saab."
//             );
//             InstructorEntity instruc7 = new InstructorEntity(
//                     "Angela",
//                     "Yu",
//                     "angelaYu@gmail.com",
//                     "088668877",
//                     "I'm Angela, I'm a developer with a passion for teaching. I'm the lead instructor at the London App Brewery, London's leading Programming Bootcamp. I've helped hundreds of thousands of students learn to code and change their lives by becoming a developer"
//             );
//             InstructorEntity instruc8 = new InstructorEntity(
//                     "Ardit",
//                     "Sulce",
//                     "arditSulce@gmail.com",
//                     "088888866",
//                     "Hi, I am Ardit! I am a Python programmer and teacher. I graduated in 2013 with a Master of Science in Geospatial Technologies from the University of Muenster in Germany."
//             );
//             InstructorEntity instruc9 = new InstructorEntity(
//                     "Kajo",
//                     "Rudziński",
//                     "kajoRudziński@gmail.com",
//                     "088213866",
//                     "Teacher at Univ, have 3 experiment years of teaching at school"
//             );
//             InstructorEntity instruc10 = new InstructorEntity(
//                     "Zaid",
//                     "Sabih",
//                     "zaidSabih@gmail.com",
//                     "088668556",
//                     "My name is Zaid Al-Quraishi, I am an ethical hacker, a computer scientist, and the founder and CEO of zSecurity. I just love hacking and breaking the rules, but don’t get me wrong as I said I am an ethical hacker."
//             );
//             InstructorEntity instruc11 = new InstructorEntity(
//                     "Nathan",
//                     "House",
//                     "nathanHouse@gmail.com",
//                     "088668246",
//                     "Nathan has over 24 years experience in cyber security where I have advised  some of largest companies in the world, assuring security on  multi-million and multi-billion pound projects"
//             );
//             InstructorEntity instruc12 = new InstructorEntity(
//                     "Zulqarnain",
//                     "Hayat",
//                     "zulqarnainHayat@gmail.com",
//                     "088668866",
//                     "I have done Master in Computer science (IT) and additionally I have more than 17 years of professional experience UNIX administration, Oracle database administration with different flavors (Solaris, Linux, Oracle Linux, Red Hat) and working as role  Database specialist.I have completed command over following functions and skills."
//             );
//             InstructorEntity instruc13 = new InstructorEntity(
//                     "Keino",
//                     "Campbell",
//                     "keinoCampbell@gmail.com",
//                     "088662166",
//                     "I am an IELTS and TOEFL specialist who has developed courses for international exams for 15 years. I have lived and taught in multiple  countries, including China, Brazil, The Ivory Coast, Kazakhstan and Georgia."
//             );
//             InstructorEntity instruc14 = new InstructorEntity(
//                     "Jaysen",
//                     "Batchelor",
//                     "jaysenBatchelor@gmail.com",
//                     "088665566",
//                     "At the age of 17 I began a 6th month internship with an animation studio out of Utah. After my internship was up, I was hired on to build backgrounds and to design props and characters for animation projects."
//             );
//             InstructorEntity instruc15 = new InstructorEntity(
//                     "Quinton",
//                     "Batchelor",
//                     "quintonBatchelor@gmail.com",
//                     "088907866",
//                     "Hi, I've been taking photos for years ranging from portraits, wildlife, and product. When I first started It was very hard and confusing to understand the basics of photography"
//             );

//             instructorRepository.saveAll(
//                     List.of(
//                             instruc1, instruc2, instruc3, instruc4,
//                             instruc5, instruc6, instruc7, instruc8,
//                             instruc9, instruc10, instruc11, instruc12,
//                             instruc13, instruc14, instruc15
//                     )
//             );

//             // Create Category
//             CategoryEntity cateWeb = new CategoryEntity(
//                     "Web Development"
//             );
//             CategoryEntity cateDesign = new CategoryEntity(
//                     "Design"
//             );
//             CategoryEntity cateBusiness = new CategoryEntity(
//                     "Business"
//             );

//             categoryRepository.saveAll(
//                     List.of(
//                             cateWeb, cateDesign, cateBusiness
//                     )
//             );

//             // Create Course
//             // web category
//             CourseEntity c0 = new CourseEntity(
//                     "The Web Developer Bootcamp 2021",
//                     "COMPLETELY REDONE - The only course you need to learn web development - HTML, CSS, JS, Node, and More!",
//                     15.93,
//                     "https://img-c.udemycdn.com/course/240x135/625204_436a_3.jpg"
//             );


//             CourseEntity c1 = new CourseEntity(
//                     "Angular - The Complete Guide (2021 Edition)",
//                     "Master Angular 12 (formerly \"Angular 2\") and build awesome, reactive web apps with the successor of Angular.js",
//                     17.05,
//                     "https://img-c.udemycdn.com/course/240x135/756150_c033_2.jpg"
//             );


//             CourseEntity c2 = new CourseEntity(
//                     "The Complete 2021 Web Development Bootcamp",
//                     "Become a full-stack web developer with just one course. HTML, CSS, Javascript, Node, React, MongoDB and more!",
//                     10.52,
//                     "https://img-c.udemycdn.com/course/240x135/1565838_e54e_12.jpg"
//             );


//             CourseEntity c3 = new CourseEntity(
//                     "The Complete JavaScript Course 2021: From Zero to Expert!",
//                     "The modern JavaScript course for everyone! Master JavaScript with projects, challenges and theory. Many courses in one!",
//                     13.09,
//                     "https://img-c.udemycdn.com/course/240x135/851712_fc61_6.jpg"
//             );


//             CourseEntity c4 = new CourseEntity(
//                     "Modern React with Redux",
//                     "Master React and Redux with React Router, Webpack, and Create-React-App.  Includes Hooks!",
//                     14.00,
//                     "https://img-c.udemycdn.com/course/240x135/705264_caa9_11.jpg"
//             );


//             CourseEntity c5 = new CourseEntity(
//                     "The Complete Web Developer Course 2.0",
//                     "Learn Web Development by building 25 websites and mobile apps using HTML, CSS, Javascript, PHP, Python, MySQL & more!",
//                     15.51,
//                     "https://img-c.udemycdn.com/course/240x135/764164_de03_2.jpg"
//             );


//             CourseEntity c6 = new CourseEntity(
//                     "The Complete Angular Course: Beginner to Advanced",
//                     "The most comprehensive Angular 4 (Angular 2+) course. Build a real e-commerce app with Angular, Firebase and Bootstrap 4",
//                     19.41,
//                     "https://img-c.udemycdn.com/course/240x135/1247828_32bb.jpg"
//             );


//             CourseEntity c7 = new CourseEntity(
//                     "Understanding TypeScript - 2021 Edition",
//                     "Don't limit the Usage of TypeScript to Angular! Learn the Basics, its Features, Workflows and how to use it!",
//                     12.97,
//                     "https://img-c.udemycdn.com/course/240x135/947098_02ec_2.jpg"
//             );


//             CourseEntity c8 = new CourseEntity(
//                     "Angular & NodeJS - The MEAN Stack Guide [2021 Edition]",
//                     "Learn how to connect your Angular Frontend to a NodeJS & Express & MongoDB Backend by building a real Application",
//                     13.81,
//                     "https://img-c.udemycdn.com/course/240x135/833442_b26e_5.jpg"
//             );


//             CourseEntity c9 = new CourseEntity(
//                     "Learn and Understand AngularJS",
//                     "Master AngularJS and the Javascript concepts behind it, design custom directives, and build a single page application.",
//                     19.74,
//                     "https://img-c.udemycdn.com/course/240x135/289230_1056_16.jpg"
//             );


//             CourseEntity c10 = new CourseEntity(
//                     "The Complete WordPress Website Business Course",
//                     "Master WordPress with this Complete WordPress Course, without learning how to code and without any programming!",
//                     18.64,
//                     "https://img-c.udemycdn.com/course/240x135/520116_edf5_2.jpg"
//             );


//             CourseEntity c11 = new CourseEntity(
//                     "REST APIs with Flask and Python",
//                     "Build professional REST APIs with Python, Flask, Flask-RESTful, and Flask-SQLAlchemy",
//                     14.35,
//                     "https://img-c.udemycdn.com/course/240x135/970600_68be_4.jpg"
//             );


//             CourseEntity c12 = new CourseEntity(
//                     "Node with React: Fullstack Web Development",
//                     "Build and deploy fullstack web apps with NodeJS, React, Redux, Express, and MongoDB.",
//                     15.46,
//                     "https://img-c.udemycdn.com/course/240x135/1254420_f6cb_4.jpg"
//             );


//             CourseEntity c13 = new CourseEntity(
//                     "MERN Stack Front To Back: Full Stack React, Redux & Node.js",
//                     "Build and deploy a social network with Node.js, Express, React, Redux & MongoDB. Fully updated April 2019",
//                     12.53,
//                     "https://img-c.udemycdn.com/course/240x135/1646980_23f7_2.jpg"
//             );


//             CourseEntity c14 = new CourseEntity(
//                     "Progressive Web Apps (PWA) - The Complete Guide",
//                     "Build a Progressive Web App (PWA) that feels like an iOS & Android App, using Device Camera, Push Notifications and more",
//                     15.15,
//                     "https://img-c.udemycdn.com/course/240x135/1329100_571a.jpg"
//             );


//             CourseEntity c15 = new CourseEntity(
//                     "The Advanced Web Developer Bootcamp",
//                     "Learn React 16, Redux, D3, ES2015, Testing, CSS Flexbox, Animations, SVG, AJAX, and more!",
//                     18.58,
//                     "https://img-c.udemycdn.com/course/240x135/1218586_9f86.jpg"
//             );


//             CourseEntity c16 = new CourseEntity(
//                     "JavaScript: The Advanced Concepts (2021)",
//                     "Learn modern advanced JavaScript practices and be in the top 10% of JavaScript developers",
//                     16.89,
//                     "https://img-c.udemycdn.com/course/240x135/1501104_967d_13.jpg"
//             );


//             CourseEntity c17 = new CourseEntity(
//                     "Javascript for Beginners Learn by Doing Practical Exercises",
//                     "JavaScript for Beginners : Work closely with me doing exercises & learn more. I make Javascript easy for you guaranteed.",
//                     14.68,
//                     "https://img-c.udemycdn.com/course/240x135/405818_aa3f_3.jpg"
//             );


//             CourseEntity c18 = new CourseEntity(
//                     "The Complete Junior to Senior Web Developer Roadmap (2021)",
//                     "Go from Junior Developer to Senior Developer. Learn all the technical skills Senior Web Developers know!",
//                     13.49,
//                     "https://img-c.udemycdn.com/course/240x135/1650610_2673_6.jpg"
//             );


//             CourseEntity c19 = new CourseEntity(
//                     "Angular (Full App) with Angular Material, Angularfire & NgRx",
//                     "Use Angular, Angular Material, Angularfire (+ Firebase with Firestore) and NgRx to build a real Angular App",
//                     17.13,
//                     "https://img-c.udemycdn.com/course/240x135/1512962_9f57.jpg"
//             );


//             CourseEntity c20 = new CourseEntity(
//                     "The Complete Developers Guide to MongoDB",
//                     "Master MongoDB and Mongoose design with a test-driven approach",
//                     11.03,
//                     "https://img-c.udemycdn.com/course/240x135/1000574_06cb.jpg"
//             );


//             CourseEntity c21 = new CourseEntity(
//                     "Go Java Full Stack with Spring Boot and Angular",
//                     "Become a Full Stack Java Developer. Build Your First Java Full Stack Application with Angular and Spring Boot.",
//                     13.73,
//                     "https://img-c.udemycdn.com/course/240x135/2023728_2d6d_4.jpg"
//             );

//             List.of(
//                     c0, c1, c2, c3, c4, c5,
//                     c6, c7, c8, c9, c10,
//                     c11, c12, c13, c14, c15,
//                     c16, c17, c18, c19, c20,
//                     c21
//             ).stream().forEach(c -> {
//                 c.setCategory(
//                         categoryRepository.findByName("Web Development").get()
//                 );
//                 c.setInstructor(
//                         instructorRepository.findAll().get(0)
//                 );
//             });

//             CourseEntity c0b = new CourseEntity(
//                     "Sales Training: Practical Sales Techniques",
//                     "Sales Hacking: Essential sales skills, sales strategies and sales techniques to sell just about anything!",
//                     12.26,
//                     "https://img-c.udemycdn.com/course/240x135/563478_2c35.jpg"
//             );


//             CourseEntity c1b = new CourseEntity(
//                     "Customer Service Mastery: Delight Every Customer",
//                     "Master Customer Service using this practical customer care course",
//                     10.07,
//                     "https://img-c.udemycdn.com/course/240x135/1919728_cf04_6.jpg"
//             );


//             CourseEntity c2b = new CourseEntity(
//                     "Business Development & B2B Sales for Startups- Sales Valley",
//                     "The Complete Startup Playbook for Business Development & B2B Sales to learn Lead Generation, Pitching, & Closing Deals.",
//                     11.76,
//                     "https://img-c.udemycdn.com/course/240x135/1449746_ca17_2.jpg"
//             );


//             CourseEntity c3b = new CourseEntity(
//                     "LinkedIn Marketing, Lead Generation & B2B Sales for LinkedIn",
//                     "LinkedIn Machine: The LinkedIn MasterClass to learn LinkedIn Marketing, Lead Generation, Business Development, B2B Sales",
//                     16.47,
//                     "https://img-c.udemycdn.com/course/240x135/1794901_d8ac_2.jpg"
//             );


//             CourseEntity c4b = new CourseEntity(
//                     "Sales Training: How To Close More Sales",
//                     "Closing Sales: Sales Training Course: Sales Skills; Selling Techniques; Sales Strategies for B2B, B2C Direct Sales",
//                     16.98,
//                     "https://img-c.udemycdn.com/course/240x135/1504268_d83f_13.jpg"
//             );


//             CourseEntity c5b = new CourseEntity(
//                     "Understanding Strategic Marketing",
//                     "A guide for managers and MBA students to master marketing theory and application",
//                     15.39,
//                     "https://img-c.udemycdn.com/course/240x135/554518_27fc_4.jpg"
//             );


//             CourseEntity c6b = new CourseEntity(
//                     "B2B Sales Objections Simplified",
//                     "B2B Sales Skill: Learn How to Handle the Most Common Sales Objections, Over the Phone and in Person!",
//                     14.26,
//                     "https://img-c.udemycdn.com/course/240x135/1512040_b75e_2.jpg"
//             );


//             CourseEntity c7b = new CourseEntity(
//                     "NLP In Sales Certification- Sell More, Persuade & Influence",
//                     "Get new sales with NLP skills for persuasion and influence - makes sales easy using Neuro-Linguistic Programming",
//                     19.82,
//                     "https://img-c.udemycdn.com/course/240x135/864848_58fc_6.jpg"
//             );


//             CourseEntity c8b = new CourseEntity(
//                     "The Complete Sales Prospecting Bootcamp Course",
//                     "Learn how to schedule meetings with top executives by crafting great cold emails, phone calling, LinkedIn and more!",
//                     14.83,
//                     "https://img-c.udemycdn.com/course/240x135/631534_7d93_4.jpg"
//             );


//             CourseEntity c9b = new CourseEntity(
//                     "B2B Sales - Close More Deals, Get More Leads, Work Less",
//                     "Drastically improve lead generation with apps, tools, and outsourcing.  Close more deals by using an effective script.",
//                     18.60,
//                     "https://img-c.udemycdn.com/course/240x135/395378_959f_7.jpg"
//             );


//             CourseEntity c10b = new CourseEntity(
//                     "Sales Skills Mastery 1:  Sales Training For Beginners",
//                     "Selling Techniques and Sales Strategy that Every Salesperson Ought to Know - Sales Training For the Modern World",
//                     10.06,
//                     "https://img-c.udemycdn.com/course/240x135/1128438_3d6c_6.jpg"
//             );


//             CourseEntity c11b = new CourseEntity(
//                     "Business to Business Selling Skills",
//                     "B2B Selling Skills will have IMMEDIATE IMPACT on the success of Face-to-Face Salespeople who sell a considered purchase.",
//                     15.95,
//                     "https://img-c.udemycdn.com/course/240x135/428642_3526_5.jpg"
//             );


//             CourseEntity c12b = new CourseEntity(
//                     "Network Marketing - Mastering Online Strategies For MLM",
//                     "Network marketing attraction and prospecting strategies using social media and online marketing for MLM success.",
//                     19.39,
//                     "https://img-c.udemycdn.com/course/240x135/1301360_1c23.jpg"
//             );


//             CourseEntity c13b = new CourseEntity(
//                     "How to Write a Sponsorship Proposal That Earns - 2020 Update",
//                     "NEW module for webinars, digital events, virtual conferences and more! Proven methods for securing massive sponsorships.",
//                     10.24,
//                     "https://img-c.udemycdn.com/course/240x135/1783578_4c46_3.jpg"
//             );


//             CourseEntity c14b = new CourseEntity(
//                     "Presales and Lead Generation Sales Skills (Prospecting 4.0)",
//                     "Presales & lead generation: how to generate great leads, ask great questions in sales & generate the right leads",
//                     12.99,
//                     "https://img-c.udemycdn.com/course/240x135/2649642_efd1_2.jpg"
//             );


//             CourseEntity c15b = new CourseEntity(
//                     "Life Purpose Coach Sales Presentation Blueprint",
//                     "Complete presentation for marketing life purpose coaching and getting more clients.",
//                     14.09,
//                     "https://img-c.udemycdn.com/course/240x135/756462_1ab3_2.jpg"
//             );


//             CourseEntity c16b = new CourseEntity(
//                     "Strategies for Finding New Web Design & SEO Clients in 2021",
//                     "Create your unique marketing plan as we cover the exact lead generation strategies you can use to find new clients",
//                     16.98,
//                     "https://img-c.udemycdn.com/course/240x135/765190_4e95.jpg"
//             );


//             CourseEntity c17b = new CourseEntity(
//                     "Start Ebooks Selling Business for Free",
//                     "Create, Edit and Resell Ebooks and make 4-figures income.",
//                     19.85,
//                     "https://img-c.udemycdn.com/course/240x135/2518424_c2ff_4.jpg"
//             );


//             CourseEntity c18b = new CourseEntity(
//                     "Be The Best In Overcoming Sales Objections!",
//                     "Increase your sales achievements and upgrade your persuasion skills by learning 6 simple techniques.",
//                     16.77,
//                     "https://img-c.udemycdn.com/course/240x135/876064_fb19.jpg"
//             );


//             CourseEntity c19b = new CourseEntity(
//                     "Freight Brokerage - How to Start Your Own Business",
//                     "Move More Freight, Build Your Own Business and Become More Successful Faster...",
//                     13.53,
//                     "https://img-c.udemycdn.com/course/240x135/1293632_703f.jpg"
//             );


//             CourseEntity c20b = new CourseEntity(
//                     "Linkedin Sales Success: The Ultimate LinkedIn Sales Guide",
//                     "Learn How To Use LinkedIn To Attract Better Prospects, Generate More Leads & Boost Your Revenue",
//                     14.88,
//                     "https://img-c.udemycdn.com/course/240x135/657298_0954_2.jpg"
//             );

//             List.of(
//                     c0b, c1b, c2b, c3b, c4b, c5b,
//                     c6b, c7b, c8b, c9b, c10b,
//                     c11b, c12b, c13b, c14b, c15b,
//                     c16b, c17b, c18b, c19b, c20b
//             ).stream().forEach(c -> {
//                 c.setCategory(categoryRepository.findByName("Business").get());
//                 c.setInstructor(
//                         instructorRepository.findAll().get(1)
//                 );
//             });

//             CourseEntity c0c = new CourseEntity(
//                     "The Ultimate Drawing Course - Beginner to Advanced",
//                     "Learn the #1 most important building block of all art",
//                     10.10,
//                     "https://img-c.udemycdn.com/course/240x135/874012_c7f2_3.jpg"
//             );


//             CourseEntity c1c = new CourseEntity(
//                     "Illustrator 2021 MasterClass",
//                     "Master Adobe Illustrator with this in-depth training for all levels.",
//                     10.93,
//                     "https://img-c.udemycdn.com/course/240x135/1197206_7201_4.jpg"
//             );


//             CourseEntity c2c = new CourseEntity(
//                     "Graphic Design Masterclass - Learn GREAT Design",
//                     "The Ultimate Graphic Design Course Which Covers Photoshop, Illustrator, InDesign,Design Theory, Branding and Logo Design",
//                     12.04,
//                     "https://img-c.udemycdn.com/course/240x135/1643044_e281.jpg"
//             );


//             CourseEntity c3c = new CourseEntity(
//                     "Ultimate Adobe Photoshop Training: From Beginner to Pro",
//                     "Master Adobe Photoshop CC 2021 without any previous knowledge with this easy-to-follow course",
//                     12.09,
//                     "https://img-c.udemycdn.com/course/240x135/1046722_cbd7_6.jpg"
//             );


//             CourseEntity c4c = new CourseEntity(
//                     "The Ultimate Character Design School - Beginner to Advanced",
//                     "Learn the concept industries number one job skill",
//                     18.95,
//                     "https://img-c.udemycdn.com/course/240x135/2509634_a995_8.jpg"
//             );


//             CourseEntity c5c = new CourseEntity(
//                     "The Digital Painting MEGA Course: Beginner to Advanced",
//                     "Learn the #1 most important skill for digital artists!",
//                     12.99,
//                     "https://img-c.udemycdn.com/course/240x135/1458304_7c2b_3.jpg"
//             );


//             CourseEntity c6c = new CourseEntity(
//                     "Complete Beginner's Guide to Digital Art",
//                     "Master the foundations of digital drawing and illustration and create art like a pro",
//                     15.26,
//                     "https://img-c.udemycdn.com/course/240x135/327666_82b2_2.jpg"
//             );


//             CourseEntity c7c = new CourseEntity(
//                     "How to Draw and Sketch for Absolute Beginners pt 1",
//                     "Learn the Basics of Sketching and Drawing",
//                     15.03,
//                     "https://img-c.udemycdn.com/course/240x135/1047436_97b3.jpg"
//             );


//             CourseEntity c8c = new CourseEntity(
//                     "InDesign 2021 MasterClass",
//                     "Master the Industry-leading Page Design and Layout Application",
//                     14.03,
//                     "https://img-c.udemycdn.com/course/240x135/1372236_633f_4.jpg"
//             );


//             CourseEntity c9c = new CourseEntity(
//                     "Adobe Illustrator CC 2020 MasterClass",
//                     "Full course of adobe illustrator by a simple way",
//                     10.32,
//                     "https://img-c.udemycdn.com/course/240x135/3207717_3497_2.jpg"
//             );


//             CourseEntity c10c = new CourseEntity(
//                     "After Effects Essentials: Complete VFX and Motion GFX Guide",
//                     "Your complete guide to create amazing Visual Effects and Motion Graphics using Adobe After Effects CC 2017.",
//                     13.30,
//                     "https://img-c.udemycdn.com/course/240x135/1310714_bb31_2.jpg"
//             );


//             CourseEntity c11c = new CourseEntity(
//                     "Logo Design Mastery In Adobe Illustrator",
//                     "Learn Logo Design Theory, The Creative Process, How to Work With Clients & Master Adobe Illustrator Tools",
//                     12.64,
//                     "https://img-c.udemycdn.com/course/240x135/2401332_0c50_11.jpg"
//             );


//             CourseEntity c12c = new CourseEntity(
//                     "Painting Faces with the Power of Photoshop",
//                     "Master a Timeless Art Form using the Digital Medium",
//                     15.47,
//                     "https://img-c.udemycdn.com/course/240x135/1300596_6ef6.jpg"
//             );


//             CourseEntity c13c = new CourseEntity(
//                     "Design Theory Blitz: Quickly Understand GREAT Design",
//                     "Learn what makes some designs look better than others, and how to implement those design fundamentals in your own work.",
//                     19.77,
//                     "https://img-c.udemycdn.com/course/240x135/1106490_cb6a_2.jpg"
//             );


//             CourseEntity c14c = new CourseEntity(
//                     "Ultimate Adobe Photoshop CC Masterclass Basics To Advanced",
//                     "Photoshop Color Image Correction, 3D Effect, Shadow, Blending, Skin Tone Retouching, Masking, Blur Filter, Clipping Path",
//                     12.22,
//                     "https://img-c.udemycdn.com/course/240x135/3406388_848f_4.jpg"
//             );


//             CourseEntity c15c = new CourseEntity(
//                     "T-Shirt Design Workshop 01: Foundation",
//                     "Design a t-shirt like a professional: specialty printing methods, fabrics, inks, dyes, and washes.",
//                     16.37,
//                     "https://img-c.udemycdn.com/course/240x135/803058_9f0c_5.jpg"
//             );

//             List.of(
//                     c0c, c1c, c2c, c3c, c4c, c5c,
//                     c6c, c7c, c8c, c9c, c10c,
//                     c11c, c12c, c13c, c14c, c15c
//             ).stream().forEach(c -> {
//                 c.setCategory(categoryRepository.findByName("Design").get());
//                 c.setInstructor(
//                         instructorRepository.findAll().get(2)
//                 );
//             });

//             coursesRepository.saveAll(
//                     List.of(
//                             c0, c1, c2, c3, c4, c5,
//                             c6, c7, c8, c9, c10,
//                             c11, c12, c13, c14, c15,
//                             c16, c17, c18, c19, c20,
//                             c21, c0b, c1b, c2b, c3b, c4b, c5b,
//                             c6b, c7b, c8b, c9b, c10b,
//                             c11b, c12b, c13b, c14b, c15b,
//                             c16b, c17b, c18b, c19b, c20b,
//                             c0c, c1c, c2c, c3c, c4c, c5c,
//                             c6c, c7c, c8c, c9c, c10c,
//                             c11c, c12c, c13c, c14c, c15c
//                     )
//             );

//             coursesRepository.findAll().stream().forEach(c -> {
//                 RateEntity newRate = new RateEntity((int)(Math.random() * 7 + 4));
//                 newRate.setUser(
//                         userRepository.findByUsername("user").get()
//                 );
//                 newRate.setCourse(c);
//                 rateRepository.save(newRate);
//             });

//             // Create Lesson
//             LessonEntity l1 = new LessonEntity(
//                     "https://www.youtube.com/embed/watch?v=tRTfYsLy29I",
//                     "JavaScript Releases ES5, ES6+ and ESNext",
//                     "Become an advanced, confident, and modern JavaScript developer from scratch"
//             );
//             LessonEntity l2 = new LessonEntity(
//                     "https://www.youtube.com/embed/watch?v=PHixT6kZJMI",
//                     "Course Structure and Projects",
//                     "Become job-ready by understanding how JavaScript really works behind the scenes"
//             );
//             LessonEntity l3 = new LessonEntity(
//                     "https://www.youtube.com/embed/watch?v=u8I31l8Pwc4",
//                     "Introduction to Arrays",
//                     "JavaScript fundamentals: variables, if/else, operators, boolean logic, functions, arrays, objects, loops, strings, etc.");
//             LessonEntity l4 = new LessonEntity(
//                     "https://www.youtube.com/embed/watch?v=KkyIDI6rQJI",
//                     "Coding Challenge #4",
//                     "Modern OOP: Classes, constructors, prototypal inheritance, encapsulation, etc."
//             );
//             LessonEntity l5 = new LessonEntity(
//                     "https://www.youtube.com/embed/watch?v=44a4bD1_Ois",
//                     "Pathways and Section Roadmaps",
//                     "Asynchronous JavaScript: Event loop, promises, async/await, AJAX calls and APIs"
//             );
//             LessonEntity l6 = new LessonEntity(
//                     "https://www.youtube.com/embed/watch?v=ZFH1vxKWKP4",
//                     "PROJECT #3: Pig Game",
//                     "Modern tools for 2021 and beyond: NPM, Parcel, Babel and ES6 modules"
//             );
//             LessonEntity l7 = new LessonEntity(
//                     "https://www.youtube.com/embed/watch?v=uH-tVP8MUs8",
//                     "Scope and The Scope Chain",
//                     "Get friendly support in the Q&A area"
//             );
//             LessonEntity l8 = new LessonEntity(
//                     "https://www.youtube.com/embed/watch?v=3a0I8ICR1Vg",
//                     "Closures",
//                     "Build 6 beautiful real-world projects for your portfolio (not boring toy apps)"
//             );
//             LessonEntity l9 = new LessonEntity(
//                     "https://www.youtube.com/embed/watch?v=DPYCs9yWBuI",
//                     "PROJECT: \"Bankist\" App",
//                     "How to think and work like a developer: problem-solving, researching, workflows"
//             );

//             LessonEntity l10 = new LessonEntity(
//                     "https://www.youtube.com/embed/watch?v=Z_gjlIji8hU",
//                     "Project Overview and Planning (I)",
//                     "Asynchronous JavaScript: Event loop, promises, async/await, AJAX calls and APIs"
//             );

//             LessonEntity l11 = new LessonEntity(
//                     "https://www.youtube.com/embed/watch?v=3AKh0-PDsMw",
//                     "Prototypal Inheritance and The Prototype Chain",
//                     "Complex concepts like the 'this' keyword, higher-order functions, closures, etc."
//             );
//             LessonEntity l12 = new LessonEntity(
//                     "https://www.youtube.com/embed/watch?v=-6VFV8q0zmI",
//                     "Project Overview",
//                     "How to architect your code using flowcharts and common patterns"
//             );
//             LessonEntity l13 = new LessonEntity(
//                     "https://www.youtube.com/embed/watch?v=UI6lqHOVHic",
//                     "Managing Workout Data: Creating Classes",
//                     "Practice your skills with 50+ challenges and assignments (solutions included)"
//             );
//             LessonEntity l14 = new LessonEntity(
//                     "https://www.youtube.com/embed/watch?v=GfVMKkUk2Uo",
//                     "Chaining Promises",
//                     "Design your unique learning path according to your goals: course pathways"
//             );

//             List.of(
//                     l1, l2, l3, l4, l5,
//                     l6, l7, l8, l9, l10,
//                     l11, l12, l13, l14
//             ).stream().forEach(l -> {
//                 l.setCourse(c0);
//             });

//             lessonRepository.saveAll(
//                     List.of(
//                             l1, l2, l3, l4, l5,
//                             l6, l7, l8, l9, l10,
//                             l11, l12, l13, l14
//                     )
//             );
//         };
//     }
// }
