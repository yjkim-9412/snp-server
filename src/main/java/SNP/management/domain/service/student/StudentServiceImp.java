package SNP.management.domain.service.student;

import SNP.management.domain.DTO.ScheduleDTO;
import SNP.management.domain.DTO.StudentDTO;
import SNP.management.domain.entity.student.Student;
import SNP.management.domain.entity.Teacher;
import SNP.management.domain.entity.student.Classes;
import SNP.management.domain.repository.student.StudentRepositoryImp;
import SNP.management.domain.repository.teacher.TeacherRepository;
import SNP.management.domain.service.schedule.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImp extends Classes implements StudentService, ScheduleService {


    private final StudentRepositoryImp studentRepository;
    private final TeacherRepository teacherRepository;

    //학생 저장 업데이트
    @Override
    public Long save(StudentDTO studentDTO) {
        Student byId = studentRepository.findById(studentDTO.getId())
                .filter(s -> s.getId().equals(studentDTO.getId()))
                .orElse(null);

        //기존 학생 유무 검증
        if (byId != null) {
//            Optional<Student> student = studentRepository.findById(studentDTO.getId());
            //학생 담당 선생님 조회 검증
            checkTeacher(studentDTO, byId);
            //학생 업데이트
            studentRepository.save(byId);

            return byId.getId();
        }

        // 존재 학생 없을시 새로 생성
        Student student = new Student(studentDTO);
        //담당 선생님 조회
        checkTeacher(studentDTO, student);


        return studentRepository.save(student);
    }

    private void checkTeacher(StudentDTO studentDTO, Student getStudent) {
        Optional<Teacher> teacher = teacherRepository.findById(studentDTO.getTeacher_id());
        teacher.ifPresentOrElse(getStudent::connectTeacher,() ->log.info("Teacher is null"));
    }

    /**
     * 스케줄 처음 생성할때만 사용 그 이외에는 단독 사용 금지
     */
    @Override
    public void createScheduleFor(Student student, ScheduleDTO scheduleDTO) {
        for (Map.Entry<Integer, String> e : scheduleDTO.getScheduleMap().entrySet()) {
            Classes classes = super.saveClass(e.getKey(), e.getValue(), student);
            studentRepository.saveSchedule(classes);
        }
    }

    @Override
    public void addSchedule(Student student, ScheduleDTO scheduleDTO) {
        //해당학생 시간표 조회
        List<Classes> classesByStudentId = studentRepository.findClassesByStudentId(student.getId());


        if (classesByStudentId.isEmpty()){// 시간표가 없을때
            log.info("classesByStudentId = {}", classesByStudentId.isEmpty());
            createScheduleFor(student, scheduleDTO);
        } else {// 시간표가 있을때 //기존 시간표와 파라미터 시간표 매치,검증
            checkDuplicateAndSave(student, scheduleDTO, classesByStudentId);
        }
    }

    /**
     * 기존시간표와 파라미터 시간표 비교후 업데이트
     * @param student
     * @param scheduleDTO
     * @param classesByStudentId
     */
    @Override
    public void checkDuplicateAndSave(Student student, ScheduleDTO scheduleDTO, List<Classes> classesByStudentId) {
        for (Classes classes : classesByStudentId) {// 해당 학생 기존 시간표
            for (Map.Entry<Integer, String> es : scheduleDTO.getScheduleMap().entrySet()){ // 파라미터 시간표

                if (classes.getDayOfWeek().getDayInt() != es.getKey()
                        && !classes.getTime().equals(es.getValue())) { //서로 일치하는 시간표가 없을때

                    studentRepository.saveSchedule(classes);

                } else if (classes.getDayOfWeek().getDayInt() == es.getKey()
                        && !classes.getTime().equals(es.getValue())) { // 요일만 일치할때

                    Classes changeTime = classes.changeTime(es.getValue());
                    studentRepository.saveSchedule(changeTime);

                } else if (classes.getDayOfWeek().getDayInt() != es.getKey()
                        && classes.getTime().equals(es.getValue())) {// 시간만 일치할때.

                    Classes changeDayOfWeek = classes.changeDayOfWeek(es.getKey());
                    studentRepository.saveSchedule(changeDayOfWeek);
                }

            }
        }
    }
}
