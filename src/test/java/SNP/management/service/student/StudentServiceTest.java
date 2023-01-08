package SNP.management.service.student;

import SNP.management.Web.student.StudentDTO;
import SNP.management.domain.entity.student.Student;
import SNP.management.domain.repository.student.StudentRepositoryImp;
import SNP.management.domain.repository.teacher.TeacherRepository;
import SNP.management.domain.service.student.StudentServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class StudentServiceTest {
    @Autowired
    EntityManager em;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    StudentRepositoryImp studentRepository;
    @Autowired
    StudentServiceImp studentService;
    @Test
    void saveAndFindById() {
        //give
        StudentDTO studentDTO = new StudentDTO("student1",15,"951205","010-8888-8888",
                "xxt1205@gmail.com","아빠","010-6664-6664",
                "남자",0,"A_CLASS","부산시","금단로",
                "고등학생",3,300,5,32,1L);

        // when
        Long save = studentService.save(studentDTO);
        em.flush();
        em.clear();

        Optional<Student> student = studentRepository.findById(save);
        // then
        student.ifPresentOrElse(value -> {
            assertThat(value.getId()).isEqualTo(save);
        }, () -> System.out.println("assert null"));
        ;


    }
}