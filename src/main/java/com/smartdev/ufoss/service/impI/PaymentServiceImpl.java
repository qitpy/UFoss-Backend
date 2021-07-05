package com.smartdev.ufoss.service.impI;

import com.smartdev.ufoss.converter.PaymentConverter;
import com.smartdev.ufoss.dto.PaymentDTO;
import com.smartdev.ufoss.dto.PaymentDTOGet;
import com.smartdev.ufoss.entity.CourseEntity;
import com.smartdev.ufoss.entity.InstructorEntity;
import com.smartdev.ufoss.entity.PaymentEntity;
import com.smartdev.ufoss.entity.UserEntity;
import com.smartdev.ufoss.repository.CoursesRepository;
import com.smartdev.ufoss.repository.PaymentRepository;
import com.smartdev.ufoss.repository.UserRepository;
import com.smartdev.ufoss.service.PaymentSevice;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentSevice {
    private PaymentRepository paymentRepository;
    private UserRepository userRepository;
    private CoursesRepository coursesRepository;

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
    public PaymentEntity getPaymentById(UUID id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "The payment with id " + id + "doesn't exit!"
                ));
    }

    @Override
    public PaymentDTOGet getPaymentByUsernameID(String id) {
            Optional<PaymentDTOGet>  paymentOptional = Optional.ofNullable(paymentRepository.findByUsernameId(id));
        return null;
    }


    @Override
    public PaymentEntity addNewPayment(PaymentDTO newPayment) {
        Optional<UserEntity> userEntity = userRepository.findById(newPayment.getUserEntity().getID());
        if(userEntity.isEmpty()){
            throw new IllegalStateException("User does not exist.");
        }
        List<PaymentEntity> paymentEntities = new ArrayList<>();
        List<CourseEntity> courseEntities = newPayment.getCourseEntities();
        System.out.println("payment neeee"+ newPayment);
        for(int i = 0; i<= newPayment.getCourseEntities().size(); i++){
//            PaymentEntity paymentEntity = new PaymentEntity(newPayment.getUserEntity(), courseEntities.next());

//            paymentEntities.add(new PaymentEntity(UserEntity , newPayment.getCourseEntities()[i]));
        }
       return null;
    }

    ;
}
