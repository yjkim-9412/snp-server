package SNP.management.domain.service.student;

import SNP.management.domain.DTO.LogDTO;
import SNP.management.domain.DTO.StudentDTO;
import SNP.management.domain.entity.student.Student;

import SNP.management.domain.entity.student.StudentLog;
import SNP.management.domain.repository.student.StudentLogDataJpa;
import SNP.management.domain.repository.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class StudentLogService {

    private final StudentRepository studentRepository;
    private final StudentLogDataJpa studentLogDataJpa;


    public void save(LogDTO logDTO) {
        studentRepository.findById(logDTO.getStudentId()).orElseThrow(IllegalArgumentException::new);

    }

    public void saveFirstLog(Student student) {
        if (student.hasStudy()){
           studentLogDataJpa.save(StudentLog.createFirstStudentLog(student));
        }
    }

    public void StudentLogUpdater(StudentDTO studentDTO ,Student student) {
        if (isUpdate(studentDTO, student)){
            log.info("LogUpdate = {}", true);
            saveFirstLog(student);
        }
    }

    private boolean isUpdate(StudentDTO studentDTO, Student student) {
        return !studentDTO.getStudyType().equals(student.getStudyType()) && studentDTO.getStudyType() != null;
    }
}
