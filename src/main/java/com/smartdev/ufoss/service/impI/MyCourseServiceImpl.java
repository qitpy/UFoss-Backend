package com.smartdev.ufoss.service.impI;
import com.smartdev.ufoss.entity.CourseEntity;
import com.smartdev.ufoss.entity.UserEntity;
import com.smartdev.ufoss.exception.HandleException;
import com.smartdev.ufoss.repository.UserRepository;
import com.smartdev.ufoss.service.MyCourseSevice;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;


@AllArgsConstructor
@Service

public class MyCourseServiceImpl implements MyCourseSevice {
    private UserRepository userRepository;

    @Override
    public List<CourseEntity> getMyCourseByUserId(UUID userId) {

        List<CourseEntity> courseEntities = new ArrayList<>();
        UserEntity user = userRepository.findById(userId).orElseThrow(
                () -> new HandleException("userId: "+userId+" does not exist"));
        user.getPayment().stream().forEach(P -> courseEntities.add(P.getCourse()));

        return courseEntities;

    }
}
