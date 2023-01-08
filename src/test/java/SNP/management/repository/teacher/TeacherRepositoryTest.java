package SNP.management.repository.teacher;

import SNP.management.Web.teacher.TeacherDTO;
import SNP.management.domain.entity.Teacher;
import SNP.management.domain.repository.teacher.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class TeacherRepositoryTest {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    EntityManager em;


    @BeforeEach
    void saveBefore() {
        TeacherDTO teacherDTO = new TeacherDTO("김영준", "01075251728", "xxt1205@gmail.com", "7525");
        Teacher teacher = new Teacher().save(teacherDTO);
        teacherRepository.save(teacher);
        em.flush();
        em.clear();
    }

    @Test
    void find() {
        //given
        TeacherDTO teacherDTO = new TeacherDTO("member2", "1111111", "abc@gmail.com", "7777");
        Teacher teacher = new Teacher().save(teacherDTO);
        teacherRepository.save(teacher);

        em.flush();
        em.clear();
        Teacher foundTeacher = teacherRepository.findById(teacher.getId()).get();

        assertThat(foundTeacher.getName()).isEqualTo(teacher.getName());

    }



    @Test
    void update() {
        TeacherDTO teacherDTO = new TeacherDTO("김덕수", "01075251728", "xxt1205@naver.com", "7525");
        Teacher teacher = new Teacher().save(teacherDTO);
        teacherRepository.save(teacher);
        em.flush();

        Teacher found = teacherRepository.findById(teacher.getId()).get();
        teacherDTO.setPhone("22222222");
        found.save(teacherDTO);
        em.persist(found);
        em.flush();

        assertThat(found.getPhone()).isEqualTo("22222222");



    }

    @Test
    void delete() {
    }
}