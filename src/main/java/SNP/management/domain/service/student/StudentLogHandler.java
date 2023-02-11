package SNP.management.domain.service.student;


import SNP.management.domain.DTO.StudentDTO;
import SNP.management.domain.entity.student.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StudentLogHandler{


    private final StudentLogService studentLogService;


    public void isUpdate(StudentDTO studentDTO, Student student) {
        log.info("isUpdate = {}",!studentDTO.getStudyType().equals(student.getStudyType()) && studentDTO.getStudyType() != null);
        if (!studentDTO.getStudyType().equals(student.getStudyType()) && studentDTO.getStudyType() != null) {
            studentLogService.studentLogUpdater(studentDTO, student);
        }
    }

    public void saveStudentLogHandler(Student student) {
        if (student.hasStudy()) {
            studentLogService.saveFirstLog(student);
        }
    }
}
