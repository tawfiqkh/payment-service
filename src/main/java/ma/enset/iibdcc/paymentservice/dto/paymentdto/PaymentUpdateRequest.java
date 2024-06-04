package ma.enset.iibdcc.paymentservice.dto.paymentdto;

import ma.enset.iibdcc.paymentservice.entity.PaymentStatus;
import ma.enset.iibdcc.paymentservice.entity.PaymentType;
import ma.enset.iibdcc.paymentservice.entity.Student;

import java.util.UUID;

public record PaymentUpdateRequest (UUID id, PaymentType type, PaymentStatus status, String file, Student student){}
