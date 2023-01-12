package SNP.management.domain.repository.teacher;

import SNP.management.domain.DTO.TeacherDTO;
import SNP.management.domain.entity.QTeacher;
import SNP.management.domain.entity.Teacher;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

import static SNP.management.domain.entity.QTeacher.*;

@Repository
@Transactional
public class TeacherRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public TeacherRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Long save(TeacherDTO teacherDTO) {
        if (teacherDTO.getId() == null) {
            Teacher save = new Teacher().save(teacherDTO);
            em.persist(save);
            return save.getId();
        }
           em.persist( findById(teacherDTO.getId()).orElseThrow(NullPointerException::new));
        return teacherDTO.getId();
    }


    public Optional<Teacher> findById(Long id) {
        Teacher findTeacher = em.find(Teacher.class, id);
        return Optional.ofNullable(findTeacher);
    }


    public void update(TeacherDTO teacherDTO) {

        Optional<Teacher> findTeacher = findById(teacherDTO.getId());
        Teacher teacher = findTeacher.orElseThrow(IllegalAccessError::new);
        teacher.save(teacherDTO);
        em.persist(teacher);
    }

    public void delete(Long id) {
        Optional<Teacher> findTeacher = findById(id);
        em.remove(findTeacher.orElseThrow(IllegalAccessError::new));
    }

    public Optional<Teacher> findByLogin(TeacherDTO teacherDTO) {
        Teacher findTeacher = queryFactory
                .selectFrom(teacher)
                .where(teacher.email.eq(teacherDTO.getEmail()).and(teacher.pw.eq(teacherDTO.getPw())))
                .fetchOne();
        return Optional.ofNullable(findTeacher);
    }

}
