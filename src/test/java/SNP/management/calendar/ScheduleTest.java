package SNP.management.calendar;

import SNP.management.Web.schedule.ScheduleDTO;
import SNP.management.domain.entity.student.Classes;
import SNP.management.domain.entity.student.Student;
import SNP.management.domain.repository.student.StudentRepositoryImp;
import SNP.management.domain.service.student.StudentServiceImp;
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
class ScheduleTest extends Classes{

    @Autowired
    EntityManager em;
    @Autowired
    StudentRepositoryImp studentRepository;

    @Autowired
    StudentServiceImp studentService;




    @Test
    void createSchedule() {
        //학생1 생성
        Student student = new Student().testStudent("student1", 14, "555555");
        studentRepository.save(student);


        //학생1 스케줄 생성
        ScheduleDTO scheduleDTO = createTestSchedule(student, 1, 3, 5);
        //스케줄 DB 추가
       studentService.createScheduleFor(student,scheduleDTO);
    }



    @Test
    void find() {
        //given
        //학생1 생성
        Student student = new Student().testStudent("student1", 14, "555555");
        studentRepository.save(student);

        //학생1 스케줄 생성 : Controller 에서 받은 파라미터
        ScheduleDTO scheduleDTO = createTestSchedule(student, 1, 3, 5);

        //학생1 스케줄 추가
        studentService.addSchedule(student, scheduleDTO);

        //학생2 생성
        Student student2 = new Student().testStudent("student2", 11, "223");
        studentRepository.save(student2);

        //학생2 스케줄 생성
        ScheduleDTO scheduleDTO2 = createTestSchedule(student, 2, 5, 4);

        //학생2 스케줄 추가
        studentService.createScheduleFor(student2, scheduleDTO2);
        em.flush();


        //when
        //학생1 시간표 리스트 출력
        List<Classes> classesList = studentRepository.findClassesByStudentId(student.getId());


        //then
                assertThat(classesList.size()).isEqualTo(scheduleDTO.getScheduleMap().size());


    }
    private ScheduleDTO createTestSchedule(Student student, int day1, int day2, int day3) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(student.getId());
        Map<Integer, String> scheduleMap = scheduleDTO.getScheduleMap();
        scheduleMap.put(day1, "14:00");
        scheduleMap.put(day2, "12:00");
        scheduleMap.put(day3, "16:00");
        return scheduleDTO;
    }
}