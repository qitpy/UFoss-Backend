package com.smartdev.ufoss.service.impI;

import com.smartdev.ufoss.converter.PaymentConverter;
import com.smartdev.ufoss.converter.UserConverter;
import com.smartdev.ufoss.dto.PaymentDTO;
import com.smartdev.ufoss.dto.PaymentDTOGet;
import com.smartdev.ufoss.dto.UserDTO;
import com.smartdev.ufoss.entity.CourseEntity;
import com.smartdev.ufoss.entity.PaymentEntity;
import com.smartdev.ufoss.entity.UserEntity;
import com.smartdev.ufoss.repository.CoursesRepository;
import com.smartdev.ufoss.repository.PaymentRepository;
import com.smartdev.ufoss.repository.UserRepository;
import com.smartdev.ufoss.service.PaymentSevice;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaymentServiceImpl implements PaymentSevice {
    private PaymentRepository paymentRepository;
    private UserRepository userRepository;
    private CoursesRepository coursesRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, UserRepository userRepository, CoursesRepository coursesRepository) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
        this.coursesRepository = coursesRepository;
    }

    @Override
    public List<PaymentDTOGet> getAllPayments() {
        List<PaymentEntity> paymentEntities = paymentRepository.findAll();
        List<PaymentDTOGet> paymentDTOGets = new ArrayList<>();

        for(PaymentEntity payment: paymentEntities){
            paymentDTOGets.add(PaymentConverter.toDTO(payment, new PaymentDTOGet()));
        }
        return  paymentDTOGets;
    }

    @Override
    public PaymentDTOGet getPaymentById(UUID id) {
        Optional<PaymentEntity> paymentEntity = paymentRepository.findById(id);
        PaymentDTOGet paymentDTOGet = PaymentConverter.toDTO(paymentEntity.get(), new PaymentDTOGet());
        return paymentDTOGet;
    }

    @Override
    public List<PaymentDTOGet> getPaymentByUsernameID(UUID id) {
        List<PaymentDTOGet> paymentDTOGets = new ArrayList<>();
        Optional<UserEntity> user = userRepository.findById(id);
        List<PaymentEntity> paymentEntity = paymentRepository.findPaymentEntitiesByUser(user.get());
         if (paymentEntity != null)
         {
             for (PaymentEntity entity:paymentEntity){
                 System.out.println(entity.getCourse().getID());
                 paymentDTOGets.add(PaymentConverter.toDTO(entity, new PaymentDTOGet()));
             }
         } else {
             throw new IllegalStateException(  "The  userId = " + id + " does not exists.");
         }
        return paymentDTOGets;
    }


    @Override
        public List<PaymentDTOGet> addNewPayment(PaymentDTO newPayment) {
        UUID userId = UUID.fromString(newPayment.getUserId());
        List<PaymentDTOGet> paymentDTOGets = new ArrayList<>();
        for(String strCourseId :  newPayment.getCourId() ){
            UUID courseId = UUID.fromString(strCourseId);
            PaymentEntity paymentEntity = new PaymentEntity(
                    Calendar.getInstance().getTime().toString(),
                    userRepository.findById(userId).get(),
                    coursesRepository.findById(courseId).get()
            );
            //System.out.println("saveeeee"+paymentEntity);
            paymentRepository.save(paymentEntity);
            PaymentDTOGet paymentDTOGet = PaymentConverter.toDTO(paymentEntity, new PaymentDTOGet());
            paymentDTOGets.add(paymentDTOGet);
        }
        return paymentDTOGets;
    }
}
