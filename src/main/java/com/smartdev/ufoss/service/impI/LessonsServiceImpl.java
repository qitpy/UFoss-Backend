package com.smartdev.ufoss.service.impI;

import com.smartdev.ufoss.entity.CourseEntity;
import com.smartdev.ufoss.entity.LessonEntity;
import com.smartdev.ufoss.exception.HandleException;
import com.smartdev.ufoss.repository.CoursesRepository;
import com.smartdev.ufoss.repository.LessonRepository;
import com.smartdev.ufoss.repository.UserRepository;
import com.smartdev.ufoss.security.JwtConfig;
import com.smartdev.ufoss.service.LessonsService;
import com.smartdev.ufoss.service.PaymentSevice;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LessonsServiceImpl implements LessonsService {

    private final LessonRepository lessonRepository;
    private final CoursesRepository coursesRepository;
    private final FileStorageService fileStorageService;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;
    private final UserRepository userRepository;
    private final PaymentSevice paymentSevice;
    private static final Logger logger = LoggerFactory.getLogger(LessonsServiceImpl.class);


    public LessonsServiceImpl(LessonRepository lessonRepository,
                              CoursesRepository coursesRepository,
                              FileStorageService fileStorageService, JwtConfig jwtConfig, SecretKey secretKey, UserRepository userRepository, PaymentSevice paymentSevice) {
        this.lessonRepository = lessonRepository;
        this.coursesRepository = coursesRepository;
        this.fileStorageService = fileStorageService;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
        this.userRepository = userRepository;
        this.paymentSevice = paymentSevice;
    }


    public List<LessonEntity> findByCourseId(UUID courseId){
        Optional<CourseEntity> courseOptional = coursesRepository.findById(courseId);
        if (courseOptional.isEmpty()) {
            throw new IllegalStateException(
                    "The course with id " + courseId + " does not exists"
            );
        }
        return lessonRepository.findAllByCourse(courseOptional.get());
    }

    public LessonEntity getLessonByIdAndCourse(UUID courseId, UUID id) {
        Optional<CourseEntity> courseOptional = coursesRepository.findById(courseId);
        if (courseOptional.isEmpty()) {
            throw new IllegalStateException(
                    "The course with id " + courseId + " does not exists"
            );
        }
        return lessonRepository.findByIDAndCourse(id,courseOptional.get())
                .orElseThrow(() ->
                    new IllegalStateException(
                            "The lessons does not exists"
                    )
                );
    }

    @Override
    public ResponseEntity<Resource> getLessonVideo(UUID courseId, String fileName, HttpServletRequest request) {

        String token = request.getHeader(jwtConfig.getAuthorizationHeader())
                .replace(jwtConfig.getTokenPrefix(), "");
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        String usernameFromToken = body.getSubject();

        UUID userId = userRepository.findByUsername(usernameFromToken).orElseThrow(
                () -> new HandleException("user not found")).getID();

        if (!paymentSevice.isPaid(userId, courseId)) {
            throw new HandleException("Buy course to see this lesson");
        }

        Optional<CourseEntity> courseOptional = coursesRepository.findById(courseId);
        if (courseOptional.isEmpty()) {
            throw new IllegalStateException(
                    "The course with id " + courseId + " does not exists"
            );
        }

        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    public LessonEntity uploadLesson(MultipartFile lesson, CourseEntity course) {
        String title = fileStorageService.storeFile(lesson);

        if (lessonRepository.existsByCourseAndTitle(course, title)) {
            throw new IllegalStateException(
                    "The Lesson with " + title + " does exists."
            );
        }

        String videoURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/courses/")
                .path(course.getID().toString())
                .path("/lessons/video/")
                .path(title)
                .toUriString();

        LessonEntity lessonEntity = new LessonEntity(title, videoURL);
        lessonEntity.setCourse(course);
        return lessonEntity;
    }

    public List<LessonEntity> uploadMultipleLesson(UUID courseId, MultipartFile[] lessons) {
        Optional<CourseEntity> courseOptional = coursesRepository.findById(courseId);
        if (courseOptional.isEmpty()) {
            throw new IllegalStateException(
                    "The course with id " + courseId + " does not exists"
            );
        }
        return Arrays.stream(lessons)
                .map(lesson -> uploadLesson(lesson, courseOptional.get()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteLessonById(UUID courseId, UUID lessonsId) {
        Optional<CourseEntity> courseOptional = coursesRepository.findById(courseId);
        if (courseOptional.isEmpty()) {
            throw new IllegalStateException(
                    "The course with id " + courseId + " does not exists"
            );
        }
        boolean exists = lessonRepository.existsById(lessonsId);
        if (!exists) {
            throw new IllegalStateException(
                    "The course with id " + lessonsId + "does not exist!"
            );
        }
        lessonRepository.deleteByIDAndCourse(lessonsId,courseOptional.get());
    }
}

