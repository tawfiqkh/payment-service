package ma.enset.iibdcc.paymentservice.dto.paymentdto;

import ma.enset.iibdcc.paymentservice.entity.PaymentStatus;
import ma.enset.iibdcc.paymentservice.entity.PaymentType;
import ma.enset.iibdcc.paymentservice.entity.Student;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

public record PaymentCreateRequest(PaymentType type, PaymentStatus status, Date date, MultipartFile file, UUID studentId){}

