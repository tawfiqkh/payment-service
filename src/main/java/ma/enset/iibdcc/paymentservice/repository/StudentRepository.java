package ma.enset.iibdcc.paymentservice.repository;

import ma.enset.iibdcc.paymentservice.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

     Optional<List<Student>> findBySector(String sector);

     Optional<Student> findByCode(String sector);


}