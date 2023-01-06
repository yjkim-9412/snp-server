package SNP.management.service.student;

import SNP.management.domain.ScheduleDTO;
import SNP.management.entity.student.Classes;
import SNP.management.entity.student.Student;
import SNP.management.repository.student.StudentRepositoryImp;
import SNP.management.service.schedule.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImp extends Classes implements StudentService, ScheduleService {


     final StudentRepositoryImp studentRepository;


    /**
     * 스케줄 처음 생성할때만 사용 그 이외에는 단독 사용 금지
     * @param student
     * @param scheduleDTO
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
        List<Classes> classesByStudentId = studentRepository.findClassesByStudentId(student.getId());

        if (classesByStudentId.isEmpty()){
            log.info("classesByStudentId = {}", classesByStudentId.isEmpty());
            createScheduleFor(student, scheduleDTO);
        } else {
            checkDuplicateAndSave(student, scheduleDTO, classesByStudentId);
        }
    }

    @Override
    public void checkDuplicateAndSave(Student student, ScheduleDTO scheduleDTO, List<Classes> classesByStudentId) {
        for (Classes classes : classesByStudentId) {
            for (Map.Entry<Integer, String> es : scheduleDTO.getScheduleMap().entrySet()){
                if (classes.getDayOfWeek().getDayInt() != es.getKey()
                        && !classes.getTime().equals(es.getValue())) {
                    studentRepository.saveSchedule(classes);
                } else if (classes.getDayOfWeek().getDayInt() == es.getKey()
                        && !classes.getTime().equals(es.getValue())) {
                    Classes changeTime = classes.changeTime(es.getValue());
                    studentRepository.saveSchedule(changeTime);
                } else if (classes.getDayOfWeek().getDayInt() != es.getKey()
                        && classes.getTime().equals(es.getValue())) {
                    Classes changeDayOfWeek = classes.changeDayOfWeek(es.getKey());
                    studentRepository.saveSchedule(changeDayOfWeek);
                }
            }
        }
    }
}
