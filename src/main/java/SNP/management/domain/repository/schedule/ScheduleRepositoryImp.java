package SNP.management.domain.repository.schedule;

import SNP.management.domain.entity.student.Classes;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static SNP.management.domain.entity.student.QClasses.classes;
import static SNP.management.domain.entity.student.QStudent.student;

@Repository
@Slf4j
public class ScheduleRepositoryImp implements ScheduleRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public ScheduleRepositoryImp(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
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

    @Override
    public void saveSchedule(Classes classes) {
        em.persist(classes);
        em.flush();
    }
}
