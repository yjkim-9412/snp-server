package SNP.management.domain.repository.student;

import SNP.management.domain.entity.student.QStudent;
import SNP.management.domain.entity.student.QStudentLog;
import SNP.management.domain.entity.student.Student;
import SNP.management.domain.entity.student.StudentLog;
import SNP.management.domain.entity.textbook.QTextBook;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static SNP.management.domain.entity.student.QStudent.*;
import static SNP.management.domain.entity.student.QStudentLog.*;
import static SNP.management.domain.entity.study.QStudy.*;
import static SNP.management.domain.entity.textbook.QTextBook.*;

@Repository
public class StudentLogRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public StudentLogRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
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

    public List<StudentLog> findFetchAllByStudentId(Long studentId) {
        return queryFactory.selectFrom(studentLog)
                .join(studentLog.student, student).fetchJoin()
                .join(studentLog.study, study).fetchJoin()
                .join(studentLog.textBook, textBook).fetchJoin()
                .where(studentLog.student.id.eq(studentId))
                .orderBy(studentLog.createDate.desc())
                .fetch();

    }
}
