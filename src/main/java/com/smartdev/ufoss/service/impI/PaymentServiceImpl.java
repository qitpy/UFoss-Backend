package com.smartdev.ufoss.service.impI;

import com.smartdev.ufoss.converter.PaymentConverter;
import com.smartdev.ufoss.dto.PaymentDTO;
import com.smartdev.ufoss.dto.PaymentGetDTO;
import com.smartdev.ufoss.entity.PaymentEntity;
import com.smartdev.ufoss.entity.UserEntity;
import com.smartdev.ufoss.repository.CoursesRepository;
import com.smartdev.ufoss.repository.PaymentRepository;
import com.smartdev.ufoss.repository.UserRepository;
import com.smartdev.ufoss.service.PaymentSevice;
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
    public List<PaymentGetDTO> getAllPayments() {
        List<PaymentEntity> paymentEntities = paymentRepository.findAll();
        List<PaymentGetDTO> paymentGetDTOS = new ArrayList<>();

        for(PaymentEntity payment: paymentEntities){
            paymentGetDTOS.add(PaymentConverter.toDTO(payment, new PaymentGetDTO()));
        }
        return paymentGetDTOS;
    }

    @Override
    public PaymentGetDTO getPaymentById(UUID id) {
        Optional<PaymentEntity> paymentEntity = paymentRepository.findById(id);
        PaymentGetDTO paymentGetDTO = PaymentConverter.toDTO(paymentEntity.get(), new PaymentGetDTO());
        return paymentGetDTO;
    }

    @Override
    public List<PaymentGetDTO> getPaymentByUsernameID(UUID id) {
        List<PaymentGetDTO> paymentGetDTOS = new ArrayList<>();
        Optional<UserEntity> user = userRepository.findById(id);
        List<PaymentEntity> paymentEntity = paymentRepository.findPaymentEntitiesByUser(user.get());
         if (paymentEntity != null)
         {
             for (PaymentEntity entity:paymentEntity){
                 paymentGetDTOS.add(PaymentConverter.toDTO(entity, new PaymentGetDTO()));
             }
         } else {
             throw new IllegalStateException(  "The  userId = " + id + " does not exists.");
         }
        return paymentGetDTOS;
    }


    @Override
        public List<PaymentGetDTO> addNewPayment(PaymentDTO newPayment) {
        UUID userId = UUID.fromString(newPayment.getUserId());
        List<PaymentGetDTO> paymentGetDTOS = new ArrayList<>();
        for(String strCourseId :  newPayment.getCourId() ){
            UUID courseId = UUID.fromString(strCourseId);
            PaymentEntity paymentEntity = new PaymentEntity(
                    Calendar.getInstance().getTime().toString(),
                    userRepository.findById(userId).get(),
                    coursesRepository.findById(courseId).get()
            );
            paymentRepository.save(paymentEntity);
            PaymentGetDTO paymentGetDTO = PaymentConverter.toDTO(paymentEntity, new PaymentGetDTO());
            paymentGetDTOS.add(paymentGetDTO);
        }
        return paymentGetDTOS;
    }
}
