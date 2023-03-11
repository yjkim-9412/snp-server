package SNP.management.domain.service;

import SNP.management.domain.DTO.StudentDTO;
import SNP.management.domain.DTO.StudyDTO;
import SNP.management.domain.entity.student.Student;
import SNP.management.domain.entity.student.StudentLog;
import SNP.management.domain.entity.study.Study;
import SNP.management.domain.enumlist.StudyType;
import SNP.management.domain.repository.StudyDataJpa;
import SNP.management.domain.repository.student.StudentDataJpa;
import SNP.management.domain.repository.student.StudentLogRepository;
import SNP.management.domain.service.schedule.RequestScheduleService;
import SNP.management.domain.exceptionlist.ScheduleException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class StudyService {

    private final RequestScheduleService requestScheduleService;
    private final StudyDataJpa studyDataJpa;
    private final StudentDataJpa studentDataJpa;
    private final StudentLogRepository studentLogRepository;

    /**
     *
     * @param id studentId
     * @param today dayOfWeek
     * @return StudyDTO
     */
    public StudyDTO getTodayStudy(Long id, Integer today) {
        if (requestScheduleService.hasTodaySchedule(id, today)) {
            return todayStudyHandler(id);
        }
        throw new IllegalArgumentException(ScheduleException.NONE_SCHEDULE);
    }

    private StudyDTO todayStudyHandler(Long id) {
        Student student = studentDataJpa.findById(id).orElseThrow(IllegalAccessError::new);
        StudentLog studentLog = studentLogRepository.findLastDateByStudentIdAndStudyType(student).orElseThrow(IllegalArgumentException::new);
        Study study = studentLog.getStudy();
        if (study != null) {
            StudyDTO studyDTO = StudyDTO.createStudyDTO(student.getId(), compareWithLog(study, studentLog), studentLog);
            studyDTO.setStudentInfo(StudentDTO.createStudentDTO(student));
            return studyDTO;
        }
        throw new NullPointerException("studentLog.study is Null");
    }


    private Study compareWithLog(Study study, StudentLog studentLog) {
        int StudyCount = studentLog.getStudyCount();
        int step = studentLog.getStudy().getStep();
        String detail = studentLog.getStudy().getDetail();
        StudyType studyType = studentLog.getStudyType();

        if (isNextStep(study, studentLog)) {
            return getNextStepStudy(step, studyType);
        }
        return getStillSameStudy(StudyCount, detail, studyType);
    }

    public Study getNextStepStudy(int step, StudyType studyType) {
        int nextStep = step + 1;
        return studyDataJpa.findByStudyTypeAndStep(studyType, nextStep)
                .orElseThrow(IllegalArgumentException::new);
    }

    public Study getStillSameStudy(int studyCount, String detail, StudyType studyType) {
        int nextStudyCount = studyCount + 1;
        return getStudyNumberOfDays(detail, studyType, nextStudyCount);
    }

    public Study getStudyNumberOfDays(String detail, StudyType studyType, int nextStudyCount) {
        log.info("nextStudyCount = {}", nextStudyCount);

        return studyDataJpa.findByDetailAndStudyTypeAndNumberAndCount(
                        detail, studyType, nextStudyCount)
                .orElseThrow(IllegalArgumentException::new);
    }

    public boolean isNextStep(Study study, StudentLog studentLog) throws IllegalArgumentException {
        log.info("studentLog.getStudyCount() ={}",studentLog.getStudyCount());
        log.info("study.getNumberOfDays() ={}",study.getNumberOfDays());

        if (studentLog.getStudyCount() < study.getNumberOfDays()) {
            log.info("isNextStep ={}",false);
            return false;
        } else if (studentLog.getStudyCount() >= studentLog.getStudy().getNumberOfDays()) {
            log.info("isNextStep ={}",true);

            return true;
        }
        throw new IllegalArgumentException("Illegal StudyCount");
    }
}
