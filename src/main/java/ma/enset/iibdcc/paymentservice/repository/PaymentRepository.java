package ma.enset.iibdcc.paymentservice.repository;

import ma.enset.iibdcc.paymentservice.entity.Payment;
import ma.enset.iibdcc.paymentservice.entity.PaymentStatus;
import ma.enset.iibdcc.paymentservice.entity.PaymentType;
import ma.enset.iibdcc.paymentservice.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {

    public Optional<List<Payment>> findByStudentCode(String code);

    public Optional<List<Payment>> findByStatus(PaymentStatus paymentStatus);

    public Optional<List<Payment>> findByType(PaymentType paymentType);

    public Optional<List<Payment>> findByStudent(Student student);

    public Optional<List<Payment>> findByStudentSector(String sector);

}
