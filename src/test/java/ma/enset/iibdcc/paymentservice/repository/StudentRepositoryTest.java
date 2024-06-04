package ma.enset.iibdcc.paymentservice.repository;

import ma.enset.iibdcc.paymentservice.entity.Student;
import ma.enset.iibdcc.paymentservice.utils.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    void setup() {
        saveAll();
    }

    private void saveAll() {
        studentRepository.saveAll(List.of(TestData.student1, TestData.student2, TestData.student3));
    }

    @Test
    void should_save_student_successfully() {

        List<Student> allStudents = studentRepository.findAll();
        assertThat(allStudents)
                .containsAll(List.of(TestData.student1, TestData.student2, TestData.student3))
                .doesNotContain(TestData.student4);
    }

    @Test
    void should_find_student_by_id_successfully() {
        Student student = studentRepository.findById(TestData.uuid2).get();
        assertThat(student).isEqualTo(TestData.student2);
    }

    @Test
    void should_find_students_by_sector_successfully() {
        List<Student> students = studentRepository.findBySector("IT").get();
        assertThat(students)
                .containsAll(List.of(TestData.student1, TestData.student2))
                .doesNotContainAnyElementsOf(List.of(TestData.student3, TestData.student4));
    }
}
