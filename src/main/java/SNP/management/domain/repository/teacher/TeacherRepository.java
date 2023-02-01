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
            Teacher save = Teacher.createTeacher(teacherDTO);
            em.persist(save);
            return save.getId();
        } else {
            throw new IllegalArgumentException("duplicate teacherId");}
    }


    public Optional<Teacher> findById(Long id) {
        return Optional.ofNullable(em.find(Teacher.class, id));
    }


    public void update(TeacherDTO teacherDTO) {
        Teacher teacher = findById(teacherDTO.getId()).orElseThrow(IllegalArgumentException::new);
        teacher.update(teacherDTO);
    }

    public void delete(Long id) {
        em.remove(findById(id).orElseThrow(IllegalArgumentException::new));
    }

    public Optional<Teacher> findByLogin(TeacherDTO teacherDTO) {
        Teacher findTeacher = queryFactory
                .selectFrom(teacher)
                .where(teacher.email.eq(teacherDTO.getEmail()).and(teacher.pw.eq(teacherDTO.getPw())))
                .fetchOne();
        return Optional.ofNullable(findTeacher);
    }

}
