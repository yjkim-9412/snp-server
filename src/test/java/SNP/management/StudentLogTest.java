package SNP.management;

import SNP.management.domain.DTO.LogDTO;
import SNP.management.domain.DTO.StudyDTO;
import SNP.management.domain.entity.student.Schedule;
import SNP.management.domain.entity.student.Student;
import SNP.management.domain.entity.student.StudentLog;
import SNP.management.domain.entity.study.Study;
import SNP.management.domain.entity.textbook.Question;
import SNP.management.domain.entity.textbook.TextBook;
import SNP.management.domain.enumlist.DayOfWeek;
import SNP.management.domain.enumlist.EyeBall;
import SNP.management.domain.enumlist.StudyType;
import SNP.management.domain.repository.StudyDataJpa;
import SNP.management.domain.repository.schedule.ScheduleDataJpa;
import SNP.management.domain.repository.schedule.ScheduleRepository;
import SNP.management.domain.repository.student.StudentDataJpa;
import SNP.management.domain.repository.student.StudentLogDataJpa;
import SNP.management.domain.repository.student.StudentLogRepository;
import SNP.management.domain.repository.student.StudentRepository;
import SNP.management.domain.repository.textbook.TextBookDataJpa;
import SNP.management.domain.service.StudyService;
import SNP.management.domain.service.student.StudentLogService;
import SNP.management.web.resolver.SessionConst;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class StudentLogTest {
    
    @PersistenceContext
    EntityManager em;
    @Autowired
    StudentLogDataJpa studentLogDataJpa;
    @Autowired
    TextBookDataJpa textBookDataJpa;
    @Autowired
    StudentLogRepository studentLogRepository;
    @Autowired
    ScheduleDataJpa scheduleDataJpa;
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentDataJpa studentDataJpa;
    @Autowired
    StudyService studyService;
    @Autowired
    StudentLogService studentLogService;

    @Autowired
    StudyDataJpa studyDataJpa;

    @Autowired
    HttpSession session;

    private static Long STUDENT_ID;
    @BeforeEach
    void beforeEach() {
        session.setAttribute(SessionConst.LOGIN_TEACHER,"xxt1205@gmail.com");
//        Long studentId = 55L;
//        int today = 1;
//        Student student = studentDataJpa.findByIdAndStudy(studentId)
//                .orElseThrow(IllegalArgumentException::new);
//        StudentLog studentLog = StudentLog.createFirstStudentLog(student);
//        studentLogDataJpa.save(studentLog);
//        STUDENT_ID = studentId;
    }

    @Test
    void getToDo() {
        //given
        Long studentId = STUDENT_ID;
        int today = 1;
        DayOfWeek dayOfWeek = DayOfWeek.values()[today];
        System.out.println("dayOfWeek = " + dayOfWeek);

        //when
        Student student = studentDataJpa.findByIdAndStudy(studentId).orElseThrow(IllegalArgumentException::new);
        Schedule schedule = scheduleDataJpa.findByStudentIdAndDayOfWeek(student.getId(), dayOfWeek).orElseThrow(IllegalArgumentException::new);
        StudentLog studentLog = studentLogRepository.findLastDateByStudentIdAndStudyType(student).orElseThrow(IllegalArgumentException::new);
            studentLog.changeStudyCount(3);


        //then
        if (studentLog.getStudy() != null) {
            if (studentLog.getStudyCount() < studentLog.getStudy().getNumberOfDays()) {
                Optional<Study> result = studyDataJpa.findByDetailAndStudyTypeAndNumberAndLessCount(
                        studentLog.getStudy().getDetail(), studentLog.getStudyType(), studentLog.getStudyCount() + 1
                );
                assertThat(result.get()).isNotNull();
            } else if (studentLog.getStudyCount() >= studentLog.getStudy().getNumberOfDays()) {
                int nextStep = studentLog.getStudy().getStep()+1;
                Study study = studyDataJpa.findByStudyTypeAndStep(studentLog.getStudyType(), nextStep)
                        .orElseThrow(IllegalArgumentException::new);
                assertThat(study.getStep()).isEqualTo(studentLog.getStudy().getStep() +1);

            }
        }
    }

    @Test
    void saveFirstLog() {
        //given
        Long studentId = STUDENT_ID;
        int today = 1;
        //when
        Student student = studentDataJpa.findByIdAndStudy(studentId)
                .orElseThrow(IllegalArgumentException::new);
        StudentLog studentLog = StudentLog.createFirstStudentLog(student);
        studentLogDataJpa.save(studentLog);
        //then
        assertThat(studentLog.getId()).isNotNull();
    }

    @Test
    void getToDoService() {
        //given
        Long studentId = 68L;
        int today = 4;
        //when
        StudyDTO todayStudy = studyService.getTodayStudy(studentId, today);

        //then
        assertThat(todayStudy.getStudyDetail()).isEqualTo("2:2 (중)");
        assertThat(todayStudy.getCurrentStudyCount()).isEqualTo(3);
    }

    @Test
    void saveTodayLog() {
        //given
        Long studentId = STUDENT_ID;
        int today = 1;
        Map<Integer, Integer> map = new LinkedHashMap<>();
        int score = 10;
        for (int i = 1; i <= 10; i++) {
            map.put(i, score);
            score--;
        }
        StudyDTO todayStudy = studyService.getTodayStudy(studentId, today);
        LogDTO logDTO = new LogDTO(studentId,todayStudy.getStudyDetail(),todayStudy.getCurrentStudyCount(),30, false, 2, EyeBall.B,
                120,150, "B65", 65.5, 1300, 989, "memo", StudyType.A_CLASS, today, map);

        //when
        studentLogService.saveTodayLog(logDTO, today);
        //then

    }

    @Test
    void getLogDTOListByStudentId() {
        //given
        STUDENT_ID = 68L;

        //when
        List<LogDTO> resultList = studentLogService.findAllByStudentId( STUDENT_ID);

        //then
        assertThat(resultList).extracting("studentId",Long.class)
                .contains( STUDENT_ID);
        for (LogDTO logDTO : resultList) {
            System.out.println("logDTO.toString() = " + logDTO.toString());
        }
    }

    @Test
     void intelligibilityCalculator() {
        //given
        double totalQuestionScore = 80;
        double totalStudentScore = 33;

        //when
        double result =totalStudentScore / totalQuestionScore * 100;
        System.out.println("result = " + Math.round(result * 100.0) / 100.0);
        System.out.println("(totalStudentScore / totalQuestionScore) * 100 = " + (totalStudentScore / totalQuestionScore) * 100);
        //then

    }

    @Test
    void changeLogSameTextBook() {
        //given
        STUDENT_ID = 68L;
        Long logId = 98L;
        StudentLog studentLog = studentLogDataJpa.findById(logId).orElseThrow(IllegalArgumentException::new);
        Map<Integer, Integer> answerMap = new LinkedHashMap<>();
        Study study = studentLog.getStudy();

        List<Question> questionList = studentLog.getTextBook().getQuestionList();
        for (int i = 0; i < questionList.size(); i++) {
            answerMap.put(i + 1, i);
        }

        LogDTO logDTO = new LogDTO();
        logDTO.setAnswerMap(answerMap);
        logDTO.setTextBookCode(studentLog.getTextBook().getCode());
        logDTO.setId(studentLog.getId());
        logDTO.setStudentId(STUDENT_ID);
        logDTO.setStudyCount(study.getStepCount());
        logDTO.setStudyDetail(study.getDetail());
        logDTO.setStudyType(study.getStudyType());
        logDTO.setRapidEyeball(2);
        //when
        studentLogService.saveTodayLog(logDTO, 4);


        //then
        em.flush();
    }

    @Test
    void changeLogDifferentTextBook() {
        //given
        STUDENT_ID = 68L;
        Long logId = 98L;
        Long textBookId = 65L;
        StudentLog studentLog = studentLogDataJpa.findById(logId).orElseThrow(IllegalArgumentException::new);
        Map<Integer, Integer> answerMap = new LinkedHashMap<>();
        TextBook textBook = textBookDataJpa.findById(textBookId).orElseThrow(IllegalArgumentException::new);
        Study study = studentLog.getStudy();

        List<Question> questionList = textBook.getQuestionList();
        Integer[] studentScore = {1, 4, 2, 4, 5, 6, 7, 8, 1, 2};
        for (int i = 0; i < questionList.size(); i++) {
            answerMap.put(i + 1, studentScore[i]);
        }

        LogDTO logDTO = new LogDTO();
        logDTO.setAnswerMap(answerMap);
        logDTO.setTextBookCode(textBook.getCode());
        logDTO.setId(studentLog.getId());
        logDTO.setStudentId(STUDENT_ID);
        logDTO.setStudyCount(study.getStepCount());
        logDTO.setStudyDetail(study.getDetail());
        logDTO.setStudyType(study.getStudyType());
        logDTO.setRapidEyeball(2);
        //when
        studentLogService.saveTodayLog(logDTO, 4);


        //then
        em.flush();

    }
}
