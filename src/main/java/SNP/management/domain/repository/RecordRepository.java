package SNP.management.domain.repository;

import SNP.management.domain.DTO.QRecordDTO;
import SNP.management.domain.DTO.RecordDTO;
import SNP.management.domain.entity.QTeacher;
import SNP.management.domain.entity.student.Classes;
import SNP.management.domain.entity.student.QClasses;
import SNP.management.domain.entity.student.QStudent;
import SNP.management.domain.entity.student.QStudentLog;
import SNP.management.domain.entity.study.QStudy;
import SNP.management.domain.enumlist.DayOfWeek;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

import static SNP.management.domain.entity.QTeacher.*;
import static SNP.management.domain.entity.student.QClasses.*;
import static SNP.management.domain.entity.student.QStudent.*;
import static SNP.management.domain.entity.student.QStudentLog.*;
import static SNP.management.domain.entity.study.QStudy.*;

@Repository
@Slf4j
public class RecordRepository {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public RecordRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }
    public List<Classes> findAllLast() {
        LocalDate date = LocalDate.now();
        DayOfWeek dayOfWeek = DayOfWeek.values()[date.getDayOfWeek().getValue()];

        log.info("dayOfWeek = {}",dayOfWeek);

        List<Classes> list = queryFactory.selectFrom(classes)
                .leftJoin(classes.student, student).fetchJoin()
                .leftJoin(student.study, study).fetchJoin()
                .leftJoin(student.teacher, teacher).fetchJoin()
                .where(classes.dayOfWeek.eq(dayOfWeek))
                .fetch();


        return list;
    }

}
