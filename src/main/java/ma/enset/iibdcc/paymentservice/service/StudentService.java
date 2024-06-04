package ma.enset.iibdcc.paymentservice.service;

import lombok.AllArgsConstructor;
import ma.enset.iibdcc.paymentservice.entity.Student;
import ma.enset.iibdcc.paymentservice.exception.StudentException.StudentNotFound;
import ma.enset.iibdcc.paymentservice.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class StudentService {

    private StudentRepository studentRepository;

    public Student getStudentById(UUID id) throws StudentNotFound {
        return studentRepository.findById(id)
                .orElseThrow(()-> new StudentNotFound("student with id:"+id+"not found"));
    }


}
