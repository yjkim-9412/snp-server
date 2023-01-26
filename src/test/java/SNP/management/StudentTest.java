package SNP.management;

import SNP.management.domain.DTO.ScheduleDTO;
import SNP.management.domain.DTO.StudentDTO;
import SNP.management.domain.DTO.TeacherDTO;
import SNP.management.domain.enumlist.StudyType;
import SNP.management.domain.enumlist.GradeType;
import SNP.management.domain.repository.schedule.ScheduleRepository;
import SNP.management.domain.repository.teacher.TeacherRepository;
import SNP.management.domain.service.schedule.ScheduleService;
import SNP.management.domain.service.student.StudentServiceImp;
import SNP.management.web.form.student.ScheduleForm;
import SNP.management.web.form.student.StudentSaveForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

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
    ScheduleService scheduleService;
    @Autowired
    ScheduleRepository scheduleRepository;

    @BeforeEach
    void before(){
        TeacherDTO teacherDTO = new TeacherDTO("teacher1", "010-7777-7777", "ttt@test.com", "password111!");



        StudentSaveForm studentSaveForm = new StudentSaveForm("student1",15,"941205","010-9999-3333",
                "test@test.com","father","010-6666-3333","남", StudyType.A_CLASS,
                GradeType.MIDDLE,3);
        StudentDTO studentDTO = new StudentDTO().FormToSaveDTO(studentSaveForm);
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
    void duplicate() {// 중복검사
        // given
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setEmail("test@test.com");

        //when, then
        assertThatThrownBy(() -> studentService.duplicate(studentDTO))
                .isExactlyInstanceOf(DuplicateKeyException.class);

    }
}
