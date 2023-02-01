package SNP.management.domain.repository;

import SNP.management.domain.entity.study.Study;
import SNP.management.domain.enumlist.StudyType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static SNP.management.domain.entity.study.QStudy.study;

@Repository
public class StudyRepository {
    @PersistenceContext
    EntityManager em;

    private final JPAQueryFactory queryFactory;

    public StudyRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Study getFirstStudy(StudyType studyType) {
        Study firstStep = queryFactory
                .selectFrom(study)
                .where(study.studyType.eq(studyType).and(study.step.eq(1)))
                .fetchOne();
        if (firstStep == null){throw new NullPointerException("getFirstStudy 값이 없음");}
        return firstStep;
    }
}
