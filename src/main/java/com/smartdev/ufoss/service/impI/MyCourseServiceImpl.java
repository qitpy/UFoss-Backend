package com.smartdev.ufoss.service.impI;
import com.smartdev.ufoss.converter.PaymentConverter;
import com.smartdev.ufoss.dto.PaymentGetDTO;
import com.smartdev.ufoss.entity.CourseEntity;
import com.smartdev.ufoss.entity.PaymentEntity;
import com.smartdev.ufoss.entity.UserEntity;
import com.smartdev.ufoss.repository.CoursesRepository;
import com.smartdev.ufoss.repository.PaymentRepository;
import com.smartdev.ufoss.repository.UserRepository;
import com.smartdev.ufoss.service.MyCourseSevice;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@AllArgsConstructor
@Service

public class MyCourseServiceImpl implements MyCourseSevice {
    private PaymentRepository paymentRepository;
    private UserRepository userRepository;
    private CoursesRepository coursesRepository;
    @Override
    public List<CourseEntity> getMyCourseByUserId(UUID userId) {
        System.out.println("no o day ");
        List<CourseEntity> courseEntities = new ArrayList<>();
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        List<PaymentEntity> paymentEntity = paymentRepository.findPaymentEntitiesByUser(userEntity.get());
        if(userEntity !=null) {
            for(PaymentEntity entity: paymentEntity){
                PaymentGetDTO paymentGetDTO = PaymentConverter.toDTO(entity, new PaymentGetDTO());
                Optional<CourseEntity> courseEntity = coursesRepository.findById(UUID.fromString(paymentGetDTO.getCourseId()));
                courseEntities.add(courseEntity.get());
            }
        }else {
            throw new IllegalStateException(
                    "The userId " + userId + " does not exists."
            );
        }
        return courseEntities;
    }
}
