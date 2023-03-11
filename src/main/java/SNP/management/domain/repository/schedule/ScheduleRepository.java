package SNP.management.domain.repository.schedule;

import SNP.management.domain.DTO.QTodayScheduleDTO;
import SNP.management.domain.DTO.TodayScheduleDTO;
import SNP.management.domain.entity.student.Schedule;
import SNP.management.domain.enumlist.DayOfWeek;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

import static SNP.management.domain.entity.QTeacher.teacher;
import static SNP.management.domain.entity.student.QSchedule.*;
import static SNP.management.domain.entity.student.QStudent.student;
import static SNP.management.domain.entity.study.QStudy.study;

@Repository
@Slf4j
public class ScheduleRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public ScheduleRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<Schedule> findClassesByStudentId(Long id ) {
        log.info("before id = {}",id);

        return queryFactory
                .select(schedule)
                .from(schedule)
                .innerJoin(schedule.student, student)
                .where(schedule.student.id.eq(id))
                .fetch();
    }

    public List<Schedule> findAllLast() {
        LocalDate date = LocalDate.now();
        DayOfWeek dayOfWeek = DayOfWeek.values()[date.getDayOfWeek().getValue()];

        log.info("dayOfWeek = {}",dayOfWeek);

        List<Schedule> list = queryFactory.selectFrom(schedule)
                .leftJoin(schedule.student, student).fetchJoin()
                .leftJoin(student.study, study).fetchJoin()
                .leftJoin(student.teacher, teacher).fetchJoin()
                .where(schedule.dayOfWeek.eq(dayOfWeek))
                .fetch();


        return list;
    }


    public List<TodayScheduleDTO> findAllByDay(DayOfWeek dayOfWeek) {
        return queryFactory
                .select(new QTodayScheduleDTO(student.id, schedule.time,
                        student.name, student.parentPhone, study.detail,student.grade))
                .from(schedule)
                .join(schedule.student, student)
                .leftJoin(student.study, study)
                .where(schedule.dayOfWeek.eq(dayOfWeek))
                .fetch();
    }


}
