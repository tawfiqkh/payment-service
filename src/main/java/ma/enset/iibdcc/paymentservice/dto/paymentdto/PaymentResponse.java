package ma.enset.iibdcc.paymentservice.dto.paymentdto;

import ma.enset.iibdcc.paymentservice.entity.PaymentStatus;
import ma.enset.iibdcc.paymentservice.entity.PaymentType;
import ma.enset.iibdcc.paymentservice.entity.Student;

public record PaymentResponse(PaymentType type, PaymentStatus status, String file, Student student){}
