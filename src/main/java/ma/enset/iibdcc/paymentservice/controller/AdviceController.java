package ma.enset.iibdcc.paymentservice.controller;

import ma.enset.iibdcc.paymentservice.exception.PaymentException.PaymentNotFound;
import ma.enset.iibdcc.paymentservice.exception.StudentException.StudentNotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(value = PaymentNotFound.class)
    public ResponseEntity<PaymentNotFound> handlePaymentException(PaymentNotFound ex){
        return ResponseEntity.badRequest().body(ex);
    }

    @ExceptionHandler(value = StudentNotFound.class)
    public ResponseEntity<StudentNotFound> handleStudentException(StudentNotFound ex){
        return ResponseEntity.badRequest().body(ex);
    }

}

