package SNP.management.repository.teacher;

import SNP.management.domain.TeacherDTO;
import SNP.management.entity.Teacher;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class TeacherRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public TeacherRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public void save(Teacher teacher) {
        em.persist(teacher);
    }

    public Teacher findById(Long id) {
        return em.find(Teacher.class, id);
    }

    public Teacher update(TeacherDTO teacherDTO) {
        Teacher foundTeacher = findById(teacherDTO.getId());
        foundTeacher.save(teacherDTO);
        return foundTeacher;
    }

    public void delete(Long id) {
        Teacher foundId = findById(id);
        em.remove(foundId);
    }

}
