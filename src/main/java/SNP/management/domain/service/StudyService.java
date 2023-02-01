package SNP.management.domain.service;

import SNP.management.domain.DTO.StudyDTO;
import SNP.management.domain.entity.student.Student;
import SNP.management.domain.entity.student.StudentLog;
import SNP.management.domain.entity.study.Study;
import SNP.management.domain.enumlist.StudyType;
import SNP.management.domain.repository.StudyDataJpa;
import SNP.management.domain.repository.schedule.ScheduleDataJpa;
import SNP.management.domain.repository.schedule.ScheduleRepository;
import SNP.management.domain.repository.student.StudentDataJpa;
import SNP.management.domain.repository.student.StudentLogDataJpa;
import SNP.management.domain.repository.student.StudentLogRepository;
import SNP.management.domain.repository.student.StudentRepository;
import SNP.management.domain.service.schedule.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StudyService {

    private final StudentLogDataJpa studentLogDataJpa;
    private final StudentLogRepository studentLogRepository;
    private final ScheduleDataJpa scheduleDataJpa;
    private final ScheduleService scheduleService;
    private final ScheduleRepository scheduleRepository;
    private final StudentRepository studentRepository;
    private final StudentDataJpa studentDataJpa;
    private final StudyDataJpa studyDataJpa;

    public StudyDTO getTodayStudy(Long id, Integer today) {
        if (scheduleService.hasTodaySchedule(id, today)) {
            return todayStudyHandler(id);
        }
        throw new IllegalArgumentException("student has not schedule");
    }

    private StudyDTO todayStudyHandler(Long id) {
        Student student = studentDataJpa.findByIdAndStudy(id).orElseThrow(IllegalArgumentException::new);
        StudentLog studentLog = studentLogRepository.findLastDateByStudentIdAndStudyType(student).orElseThrow(IllegalArgumentException::new);
        Study study = studentLog.getStudy();
        compareWithLog(study, studentLog);
        if (study != null) {
            return StudyDTO.createStudyDTO(student.getId(), compareWithLog(study, studentLog), studentLog);
        }
        throw new NullPointerException("studentLog.study is Null");
    }

    private Study compareWithLog(Study study, StudentLog studentLog) {
        int nextStudyCount = studentLog.getStudyCount() + 1;
        int nextStep = studentLog.getStudy().getStep() + 1;
        String detail = studentLog.getStudy().getDetail();
        StudyType studyType = studentLog.getStudyType();

        if (isNextStep(study, studentLog)) {
            return studyDataJpa.findByStudyTypeAndStep(studyType, nextStep)
                    .orElseThrow(IllegalArgumentException::new);
        }
        return studyDataJpa.findByDetailAndStudyTypeAndNumberOfDaysLessThanEqual(
                        detail, studyType, nextStudyCount)
                .orElseThrow(IllegalArgumentException::new);
    }

    private boolean isNextStep(Study study, StudentLog studentLog) throws IllegalArgumentException {
        if (studentLog.getStudyCount() < study.getNumberOfDays()) {
            return false;
        } else if (studentLog.getStudyCount() >= studentLog.getStudy().getNumberOfDays()) {
            return true;
        }
        throw new IllegalArgumentException();
    }
}
