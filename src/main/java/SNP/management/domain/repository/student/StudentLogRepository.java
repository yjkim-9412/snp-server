package SNP.management.domain.repository.student;

import SNP.management.domain.entity.student.QStudentLog;
import SNP.management.domain.entity.student.Student;
import SNP.management.domain.entity.student.StudentLog;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

import static SNP.management.domain.entity.student.QStudentLog.*;
import static SNP.management.domain.entity.study.QStudy.*;

@Repository

public class StudentLogRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;


    public StudentLogRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
        this.em = em;
    }

    public Optional<StudentLog> findLastDateByStudentIdAndStudyType(Student studentParam){
        QStudentLog studentLogSub = new QStudentLog("studentLogSub");
        StudentLog studentLog = queryFactory.selectFrom(QStudentLog.studentLog)
                .join(QStudentLog.studentLog.study, study).fetchJoin()
                .where(QStudentLog.studentLog.createDate.eq(JPAExpressions.
                        select(studentLogSub.createDate.max())
                        .from(studentLogSub)
                        .where(studentLogSub.studyType.eq(studentParam.getStudyType())
                                .and(studentLogSub.student.eq(studentParam)))
                ))
                .fetchOne();
        return Optional.ofNullable(studentLog);
    }
}
