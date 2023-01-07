package SNP.management.repository.teacher;

import SNP.management.domain.TeacherDTO;
import SNP.management.entity.Teacher;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

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

    public Optional<Teacher> findById(Long id) {
        Teacher teacher = em.find(Teacher.class, id);
        return Optional.ofNullable(teacher);
    }

    public void update(TeacherDTO teacherDTO) {

        Optional<Teacher> teacher = findById(teacherDTO.getId());
        teacher.ifPresent(value ->value.save(teacherDTO));
    }

    public void delete(Long id) {
        Optional<Teacher> teacher = findById(id);
        teacher.ifPresent(value -> em.remove(id));
    }

}
