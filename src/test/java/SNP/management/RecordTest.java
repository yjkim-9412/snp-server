package SNP.management;

import SNP.management.domain.DTO.ScheduleDTO;
import SNP.management.domain.DTO.StudentDTO;
import SNP.management.domain.DTO.TeacherDTO;
import SNP.management.domain.entity.student.Student;
import SNP.management.domain.entity.study.Study;
import SNP.management.domain.entity.study.StudyType;
import SNP.management.domain.enumlist.GradeType;
import SNP.management.domain.repository.RecordRepository;
import SNP.management.domain.repository.student.StudentRepositoryImp;
import SNP.management.domain.repository.teacher.TeacherRepository;
import SNP.management.domain.service.schedule.ScheduleServiceImp;
import SNP.management.domain.service.student.StudentServiceImp;
import SNP.management.web.form.student.AddressForm;
import SNP.management.web.form.student.ScheduleForm;
import SNP.management.web.form.student.SkillForm;
import SNP.management.web.form.student.StudentSaveForm;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class RecordTest {


    @Autowired
    EntityManager em;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    StudentServiceImp studentService;
    @Autowired
    StudentRepositoryImp studentRepository;
    @Autowired
    ScheduleServiceImp scheduleService;
    @Autowired
    RecordRepository recordRepository;

    @BeforeEach
    void before(){

    }

    @Test
    void getFirstStudy() {
        //given
        StudyType type = StudyType.A_CLASS;

        //when
        Study firstStudy = recordRepository.getFirstStudy(type);

        //then
        assertThat(firstStudy.getStudyType()).isEqualTo(type);
        assertThat(firstStudy.getStep()).isEqualTo(1);
    }

    @Test
    void saveStudyToStudent() {
        //given
        StudyType type = StudyType.A_CLASS;
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
        //when
        Student student = studentRepository.findById(studentDTO.getId()).orElseThrow(NullPointerException::new);

        //then
        assertThat(student.getStudy().getStep()).isEqualTo(1);
    }
}
