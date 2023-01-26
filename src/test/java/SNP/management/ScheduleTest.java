package SNP.management;

import SNP.management.domain.DTO.ScheduleDTO;
import SNP.management.domain.DTO.StudentDTO;
import SNP.management.domain.DTO.TeacherDTO;
import SNP.management.domain.DTO.TodayScheduleDTO;
import SNP.management.domain.entity.student.Schedule;
import SNP.management.domain.entity.student.Student;
import SNP.management.domain.entity.study.Study;
import SNP.management.domain.enumlist.StudyType;
import SNP.management.domain.enumlist.DayOfWeek;
import SNP.management.domain.enumlist.GradeType;
import SNP.management.domain.repository.StudyRepository;
import SNP.management.domain.repository.schedule.ScheduleDataJpa;
import SNP.management.domain.repository.schedule.ScheduleRepository;
import SNP.management.domain.repository.student.StudentRepositoryImp;
import SNP.management.domain.repository.teacher.TeacherRepository;
import SNP.management.domain.service.schedule.ScheduleService;
import SNP.management.domain.service.student.StudentServiceImp;
import SNP.management.web.form.student.ScheduleForm;
import SNP.management.web.form.student.StudentSaveForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class ScheduleTest {


    @Autowired
    EntityManager em;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    StudentServiceImp studentService;
    @Autowired
    StudentRepositoryImp studentRepository;
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    ScheduleDataJpa scheduleDataJpa;
    @Autowired
    StudyRepository studyRepository;

    @BeforeEach
    void before(){

    }

    @Test
    void getFirstStudy() {
        //given
        StudyType type = StudyType.A_CLASS;

        //when
        Study firstStudy = studyRepository.getFirstStudy(type);

        //then
        assertThat(firstStudy.getStudyType()).isEqualTo(type);
        assertThat(firstStudy.getStep()).isEqualTo(1);
    }

    @Test
    void saveStudyToStudent() {
        //given
        TeacherDTO teacherDTO = new TeacherDTO("teacher1", "010-7777-7777", "ttt@test.com", "password111!");


        StudentSaveForm studentSaveForm = new StudentSaveForm("student1",15,"941205","010-9999-3333",
                "xxe@test.com","father","010-6666-3333","남", StudyType.A_CLASS,
                GradeType.MIDDLE,3);

        StudentDTO studentDTO = new StudentDTO().FormToSaveDTO(studentSaveForm);
        studentService.save(studentDTO);


        ScheduleForm scheduleForm = new ScheduleForm();
        scheduleForm.setMap(2, "12:00");
        scheduleForm.setMap(3, "14:00");
        scheduleForm.setMap(5, "16:00");
        scheduleService.addSchedule(new ScheduleDTO().FormToDTO(studentDTO.getId(),scheduleForm));

        em.flush();
        em.clear();
        //when
        Student student = studentRepository.findById(studentDTO.getId()).orElseThrow(NullPointerException::new);

        //then
        assertThat(student.getStudy().getStep()).isEqualTo(1);
    }

    @Test
    void getDayOfClassJAVA() {// 오늘자 학생 수업 가져오기 java LocalDate 기준
        List<Schedule> allLast = scheduleRepository.findAllLast();
        for (Schedule schedule : allLast) {
            System.out.println("classes.getStudent().getName() = " + schedule.getStudent().getName());
            System.out.println("classes.getStudent().getTeacher().getName() = " + schedule.getStudent().getTeacher().getName());
        }
    }

    @Test
    void getDayOfClassAPI() {// 특정 요일 수업 학생  가져오기 API 파라미터 기준
                List<TodayScheduleDTO> allByDay = scheduleRepository.findAllByDay(DayOfWeek.values()[1]);
        for (TodayScheduleDTO todayScheduleDTO : allByDay) {
            System.out.println("recordDTO = " + todayScheduleDTO.toString());
        }
    }

    @Test
    void getDayOfClassAPIDataJpa() {// 특정 요일 수업 학생  가져오기 API 파라미터 기준 DataJpa
        List<TodayScheduleDTO> allByDay = scheduleDataJpa.findTodayScheduleDTO(DayOfWeek.values()[1]);
        for (TodayScheduleDTO todayScheduleDTO : allByDay) {
            System.out.println("recordDTO = " + todayScheduleDTO.toString());
        }
    }
    @Test
    void getScheduleByStudentId() {

        //when
        ScheduleDTO scheduleDTO = scheduleService.findByStudentId(42L);
        //then
        assertThat(scheduleDTO.getScheduleMap().size()).isEqualTo(2);

    }
}
