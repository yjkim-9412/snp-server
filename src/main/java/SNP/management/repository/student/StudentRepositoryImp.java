package SNP.management.repository.student;

import SNP.management.entity.student.Classes;
import SNP.management.entity.student.QClasses;
import SNP.management.entity.student.QStudent;
import SNP.management.entity.student.Student;
import SNP.management.service.student.StudentService;
import SNP.management.service.student.StudentServiceImp;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

import static SNP.management.entity.student.QClasses.*;
import static SNP.management.entity.student.QStudent.*;

@Slf4j
@Repository
public class StudentRepositoryImp implements StudentRepository, ClassesRepository{
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public StudentRepositoryImp(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public void save(Student student) {
        em.persist(student);
    }

    public Student findById(Long id) {
        return em.find(Student.class, id);
    }

    public List<Student> findAllByName(String name) {
        List<Student> students = queryFactory
                .selectFrom(student)
                .where(student.name.eq(name))
                .fetch();
        return students;
    }

    public void delete(Student student) {
        em.remove(student);
    }

    @Override
    public List<Classes> findClassesByStudentId(Long id ) {
        log.info("before id = {}",id);

        List<Classes> studentClassList = queryFactory
                .select(classes)
                .from(classes)
                .innerJoin(classes.student, student)
                .where(classes.student.id.eq(id))
                .fetch();
        log.info("after");
        for (Classes classes1 : studentClassList) {
            log.info("list={}", classes1);
        }
        return studentClassList;
    }

    public void saveSchedule(Classes classes) {
        em.persist(classes);
        em.flush();
    }
}
