package ma.enset.iibdcc.paymentservice.service;

import lombok.Data;
import ma.enset.iibdcc.paymentservice.dto.paymentdto.PaymentCreateRequest;
import ma.enset.iibdcc.paymentservice.entity.Payment;
import ma.enset.iibdcc.paymentservice.entity.PaymentStatus;
import ma.enset.iibdcc.paymentservice.entity.PaymentType;
import ma.enset.iibdcc.paymentservice.entity.Student;
import ma.enset.iibdcc.paymentservice.exception.PaymentException.PaymentNotFound;
import ma.enset.iibdcc.paymentservice.exception.StudentException.StudentNotFound;
import ma.enset.iibdcc.paymentservice.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import ma.enset.iibdcc.paymentservice.repository.StudentRepository;
import ma.enset.iibdcc.paymentservice.utils.FileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentService {

    private PaymentRepository paymentRepository;
    private StudentService studentService;
    private FileUtils fileUtils;

    public List<Payment> getAllPayments(){
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(UUID id) throws PaymentNotFound {
        return paymentRepository.findById(id)
                .orElseThrow(()-> new PaymentNotFound("payment with id: "+id+" not found"));
    }

    public List<Payment> getPaymentByType(PaymentType type) throws PaymentNotFound {
        return paymentRepository.findByType(type)
                .orElseThrow(()-> new PaymentNotFound("no payments with type: "+type+" found"));
    }

    public List<Payment> getPaymentsByStatus(PaymentStatus status) throws  PaymentNotFound{
        return paymentRepository.findByStatus(status)
                .orElseThrow(()-> new PaymentNotFound("no payments with status: " +status+ " found"));
    }

    public List<Payment> getStudentPayments(Student student) throws  PaymentNotFound{

        return paymentRepository.findByStudent(student)
                .orElseThrow(()-> new PaymentNotFound("no payment found for student:"+ student));
    }

    public List<Payment> getPaymentsByStudentCode(String studentCode) throws PaymentNotFound {
        return paymentRepository.findByStudentCode(studentCode)
                .orElseThrow(()-> new PaymentNotFound("no payment found for student with code: "+ studentCode));
    }

    public List<Payment> getSectorPayments(String sector) throws  PaymentNotFound{

        return paymentRepository.findByStudentSector(sector)
                .orElseThrow(()-> new PaymentNotFound("no payment found for sector: "+ sector));
    }

   public Payment updatePaymentStatus(PaymentStatus status, UUID id) throws PaymentNotFound {

        Payment payment = this.getPaymentById(id);
        payment.toBuilder()
                .status(status)
                .build();

        return paymentRepository.save(payment);
   }

   public Payment createPayment(PaymentCreateRequest payment) throws IOException, StudentNotFound {

        String fileUri = fileUtils.upload(payment.file());
        Student student = studentService.getStudentById(payment.studentId());
        Payment paymentToSave = Payment.builder()
                .date(payment.date())
                .status(payment.status())
                .type(payment.type())
                .file(fileUri)
                .student(student)
                .build();

        return paymentRepository.save(paymentToSave);
   }

}
