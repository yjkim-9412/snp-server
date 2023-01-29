package SNP.management.domain.repository.student;


import SNP.management.domain.DTO.StudentDTO;
import SNP.management.domain.entity.student.QStudent;
import SNP.management.domain.entity.student.Student;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static SNP.management.domain.entity.student.QStudent.*;
import static SNP.management.domain.entity.study.QStudy.*;


@Slf4j
@Repository
public class StudentRepository {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public StudentRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Long save(Student student) {
        em.persist(student);
        em.flush();
        return student.getId();
    }
    public Optional<Student> findById(Long id) {
        return Optional.ofNullable(em.find(Student.class, id));
    }

    public List<Student> findAllByName(String name) {
        List<Student> students = queryFactory
                .selectFrom(student)
                .where(student.name.eq(name))
                .fetch();
        return students;
    }

    public Optional<Student> findByEmail(String email) {
        Student student = queryFactory.selectFrom(QStudent.student)
                .where(QStudent.student.email.eq(email))
                .fetchOne();
        return Optional.ofNullable(student);
    }

    public List<StudentDTO> findByAll() {
        List<StudentDTO> listDTO = new ArrayList<>();
        List<Student> students = queryFactory
                .selectFrom(student)
                .fetch();
        for (Student student : students) {
            listDTO.add(new StudentDTO(student));
        }
        return listDTO;
    }
    public List<StudentDTO> findByAllAndStudy() {
        List<StudentDTO> listDTO = new ArrayList<>();
        List<Student> students = queryFactory
                .select(student)
                .from(student)
                .leftJoin(student.study, study).fetchJoin()
                .fetch();
        for (Student student : students) {
            StudentDTO studentDTO = new StudentDTO(student);
            studentDTO.setStudy(student);
            listDTO.add(studentDTO);
        }
        return listDTO;
    }

    public void delete(Student student) {
        em.remove(student);
    }

}
