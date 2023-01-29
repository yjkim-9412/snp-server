package SNP.management.domain.repository.student;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository

public class StudentLogRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;


    public StudentLogRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
        this.em = em;
    }
}
