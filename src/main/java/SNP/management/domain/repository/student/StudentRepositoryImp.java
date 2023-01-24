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


@Slf4j
@Repository
public class StudentRepositoryImp implements StudentRepository {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public StudentRepositoryImp(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Long save(Student student) {
        em.persist(student);
        em.flush();
        return student.getId();
    }
    @Override
    public Optional<Student> findById(Long id) {
        return Optional.ofNullable(em.find(Student.class, id));
    }

    @Override
    public List<Student> findAllByName(String name) {
        List<Student> students = queryFactory
                .selectFrom(student)
                .where(student.name.eq(name))
                .fetch();
        return students;
    }

    @Override
    public Optional<Student> findByEmail(String email) {
        Student student = queryFactory.selectFrom(QStudent.student)
                .where(QStudent.student.email.eq(email))
                .fetchOne();
        return Optional.ofNullable(student);
    }

    @Override
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

    @Override
    public void delete(Student student) {
        em.remove(student);
    }

}
