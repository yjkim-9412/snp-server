package SNP.management.domain.repository.schedule;

import SNP.management.domain.DTO.RecordDTO;
import SNP.management.domain.entity.student.Classes;
import SNP.management.domain.entity.study.Study;
import SNP.management.domain.entity.study.StudyType;
import SNP.management.domain.enumlist.DayOfWeek;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static SNP.management.domain.entity.QTeacher.teacher;
import static SNP.management.domain.entity.student.QClasses.classes;
import static SNP.management.domain.entity.student.QStudent.student;
import static SNP.management.domain.entity.study.QStudy.study;

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
    @Override
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
    @Override
    public Study getFirstStudy(StudyType studyType) {
        Study firstStep = queryFactory
                .selectFrom(study)
                .where(study.studyType.eq(studyType).and(study.step.eq(1)))
                .fetchOne();
        if (firstStep == null){
            throw new NullPointerException("getFirstStudy 값이 없음");
        }
        return firstStep;
    }
    @Override
    public List<RecordDTO> findAllByDay(int day){
        DayOfWeek dayOfWeek = DayOfWeek.values()[day];
        List<RecordDTO> listDTO = new ArrayList<>();
        List<Classes> list = queryFactory.selectFrom(classes)
                .join(classes.student, student).fetchJoin()
                .join(student.study, study).fetchJoin()
                .join(student.teacher, teacher).fetchJoin()
                .where(classes.dayOfWeek.eq(dayOfWeek))
                .fetch();
        for (Classes classes : list) {
            listDTO.add(new RecordDTO(classes));
        }
        return listDTO;
    }
}
