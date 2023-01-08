package SNP.management.entity.study;

import SNP.management.domain.entity.student.Student;
import SNP.management.domain.entity.student.StudentLog;
import SNP.management.domain.entity.study.AType;
import SNP.management.domain.entity.study.BType;
import SNP.management.domain.entity.study.Study;
import SNP.management.domain.entity.study.StudyType;
import SNP.management.domain.repository.student.StudentRepositoryImp;
import SNP.management.domain.service.student.StudentServiceImp;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static SNP.management.entity.study.QStudy.*;
import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
class StudyLogTest extends StudentLog{

    @PersistenceContext
    EntityManager em;
    JPAQueryFactory queryFactory;

    @BeforeEach
    void before(){
        queryFactory = new JPAQueryFactory(em);
        Study aType1 = new AType();
        aType1.saveType(1,"기초","정독1 (큰)", 3, 3, false, "없음");
        em.persist(aType1);

        Study bType1 = new BType();
        bType1.saveType(1,"중급","논술1 O/T(중)", 2, 2, true, "없음");
        em.persist(bType1);
        em.flush();
        em.clear();
    }


    @Autowired
    StudentServiceImp studentService;
    @Autowired
    StudentRepositoryImp studentRepository;


    @Test
    void saveLog() {
        //given
        Student student = new Student().testStudentType("student1", 15, "4432321", StudyType.A_CLASS);
        studentRepository.save(student);

        Study aType = new AType();
        aType.saveType(2,"기초","정독2 (중)", 3, 3, false, "없음");
        em.persist(aType);
        em.flush();
        em.clear();

        StudentLog studentLog = new StudentLog().saveStudyLog(student, 5, aType);
        em.persist(studentLog);
        em.flush();

        //when
        if (student.getStudyType().equals(studentLog.getStudyType())) {

            Study studyType = findClassType(studentLog);
            List<Study> classTypeList = findAllClassType(studentLog);


            //then
                assertThat(studyType.getStudyType()).isEqualTo(student.getStudyType().toString());

        }



    }

    private Study findClassType (StudentLog studentLog) {
        
        return queryFactory
                .select(study)
                .from(study)
                .where(study.id.eq(studentLog.getStudy().getId()))
                .fetchOne();
    }

    private List<Study> findAllClassType (StudentLog studentLog) {
        return queryFactory
                .select(study)
                .from(study)
                .where(study.studyType.eq(studentLog.getStudyTypeToString()))
                .fetch();
    }





}