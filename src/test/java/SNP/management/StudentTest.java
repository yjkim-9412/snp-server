package SNP.management;

import SNP.management.domain.DTO.RecordDTO;
import SNP.management.domain.DTO.ScheduleDTO;
import SNP.management.domain.DTO.StudentDTO;
import SNP.management.domain.DTO.TeacherDTO;
import SNP.management.domain.entity.Teacher;
import SNP.management.domain.entity.student.Classes;
import SNP.management.domain.entity.study.StudyType;
import SNP.management.domain.enumlist.DayOfWeek;
import SNP.management.domain.enumlist.GradeType;
import SNP.management.domain.repository.RecordRepository;
import SNP.management.domain.repository.teacher.TeacherRepository;
import SNP.management.domain.service.schedule.ScheduleServiceImp;
import SNP.management.domain.service.student.StudentServiceImp;
import SNP.management.web.form.student.AddressForm;
import SNP.management.web.form.student.ScheduleForm;
import SNP.management.web.form.student.SkillForm;
import SNP.management.web.form.student.StudentSaveForm;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class StudentTest {
    @Autowired
    EntityManager em;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    StudentServiceImp studentService;
    @Autowired
    ScheduleServiceImp scheduleService;
    @Autowired
    RecordRepository recordRepository;

    @BeforeEach
    void before(){
        TeacherDTO teacherDTO = new TeacherDTO("teacher1", "010-7777-7777", "ttt@test.com", "password111!");
        Long teacherId = teacherRepository.save(teacherDTO);

        AddressForm addressForm = new AddressForm("부산시","해운대로");
        SkillForm skillForm = new SkillForm(200,5,6);

        StudentSaveForm studentSaveForm = new StudentSaveForm("student1",15,"941205","010-9999-3333",
                "xxe@test.com","father","010-6666-3333","남", StudyType.A_CLASS,
                GradeType.MIDDLE,3);
        studentSaveForm.setAddress(addressForm);
        studentSaveForm.setSkill(skillForm);
        studentSaveForm.setTeacherId(teacherId);
        StudentDTO studentDTO = new StudentDTO().FormToUpdateDTO(studentSaveForm);
        studentService.save(studentDTO);


        ScheduleForm scheduleForm = new ScheduleForm();
        scheduleForm.setMap(0, "12:00");
        scheduleForm.setMap(3, "14:00");
        scheduleForm.setMap(5, "16:00");
        scheduleService.addSchedule(new ScheduleDTO().FormToDTO(studentDTO.getId(),scheduleForm));

        em.flush();
        em.clear();
    }
    @Test
    void saveSchedule() {
        StudentDTO studentDTO = studentService.findById(1L);
        ScheduleForm scheduleForm = new ScheduleForm();
        scheduleForm.setMap(0, "12:00");
        scheduleForm.setMap(3, "14:00");
        scheduleForm.setMap(5, "16:00");
        scheduleService.addSchedule(new ScheduleDTO().FormToDTO(studentDTO.getId(),scheduleForm));
    }
    @Test
    void getSchedule() {
        StudentDTO studentDTO = studentService.findById(2L);
        ScheduleForm scheduleForm = new ScheduleForm();
        scheduleForm.setMap(0, "12:00");
        scheduleForm.setMap(3, "14:00");
        scheduleForm.setMap(4, "16:00");
        scheduleService.addSchedule(new ScheduleDTO().FormToDTO(studentDTO.getId(),scheduleForm));
        em.flush();
        em.clear();

        ScheduleDTO scheduleDTO = scheduleService.getSchedule(studentDTO.getId());
        assertThat(scheduleDTO.getScheduleMap()).isEqualTo(scheduleForm.getSchedule());
    }

    @Test
    void getDayOfClass() {
        List<Classes> allLast = recordRepository.findAllLast();
        for (Classes classes : allLast) {
            System.out.println("classes.getStudent().getName() = " + classes.getStudent().getName());
            System.out.println("classes.getStudent().getTeacher().getName() = " + classes.getStudent().getTeacher().getName());
        }
    }

    @Test
    void getTodaySchedule() {
        List<RecordDTO> allByDay = recordRepository.findAllByDay(2);
        for (RecordDTO recordDTO : allByDay) {
            System.out.println("recordDTO = " + recordDTO.toString());
        }
    }
}
