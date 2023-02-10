package SNP.management.domain.service.student;

import SNP.management.domain.DTO.LogDTO;
import SNP.management.domain.DTO.StudentDTO;
import SNP.management.domain.entity.student.QuestionLog;
import SNP.management.domain.entity.student.Student;

import SNP.management.domain.entity.student.StudentLog;
import SNP.management.domain.entity.study.Study;
import SNP.management.domain.entity.textbook.Question;
import SNP.management.domain.entity.textbook.TextBook;
import SNP.management.domain.repository.StudyDataJpa;
import SNP.management.domain.repository.StudyRepository;
import SNP.management.domain.repository.student.QuestionLogDataJpa;
import SNP.management.domain.repository.student.StudentDataJpa;
import SNP.management.domain.repository.student.StudentLogDataJpa;
import SNP.management.domain.repository.student.StudentLogRepository;
import SNP.management.domain.repository.textbook.QuestionDataJpa;
import SNP.management.domain.repository.textbook.TextBookDataJpa;
import SNP.management.domain.service.schedule.ScheduleService;
import SNP.management.domain.exceptionlist.ScheduleException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class StudentLogService {
    private final StudentDataJpa studentDataJpa;
    private final StudyDataJpa studyDataJpa;
    private final StudentLogDataJpa studentLogDataJpa;
    private final QuestionLogDataJpa questionLogDataJpa;
    private final TextBookDataJpa textBookDataJpa;
    private final QuestionDataJpa questionDataJpa;
    private final ScheduleService scheduleService;
    private final StudyRepository studyRepository;

    private final StudentLogRepository studentLogRepository;



    public void saveFirstLog(Student student) {
        if (student.hasStudy()) {
            student.changeStudy(studyRepository.getFirstStudy(student.getStudyType()));
            StudentLog studentLog = studentLogDataJpa.save(StudentLog.createFirstStudentLog(student));
            log.info("studentLog.getStudyType() = {}",studentLog.getStudyType());
        }
    }

    public void StudentLogUpdater(StudentDTO studentDTO, Student student) {
        if (isUpdate(studentDTO, student)) {
            log.info("LogUpdate = {}", true);
            student.changeStudyType(studentDTO.getStudyType());
            saveFirstLog(student);
        }
    }

    public void saveTodayLog(LogDTO logDTO, Integer today) {
        if (scheduleService.hasTodaySchedule(logDTO.getStudentId(), today)) {
            logDTO.combineProcessingTime();
                createStudentLog(logDTO);
        }else {
            throw new ScheduleException(ScheduleException.NONE_SCHEDULE);
        }
    }

    public List<LogDTO> findAllByStudentId (Long studentId) {
        List<LogDTO> logDTOList = new ArrayList<>();
        List<StudentLog> studentLogList = studentLogRepository.findFetchAllByStudentId(studentId);

        for (StudentLog studentLog : studentLogList) {
            logDTOList.add(LogDTO.createLogDTOByStudentLog(studentLog));
        }
        return logDTOList;
    }

    private boolean isUpdate(StudentDTO studentDTO, Student student) {
        log.info("student.getStudyType() = {}",student.getStudyType());
        log.info("studentDTO.getStudyType() = {}",studentDTO.getStudyType());
        log.info("isUpdate = {}",!studentDTO.getStudyType().equals(student.getStudyType()) && studentDTO.getStudyType() != null);

        return !studentDTO.getStudyType().equals(student.getStudyType()) && studentDTO.getStudyType() != null;
    }

    private void createStudentLog(LogDTO logDTO) {
        Student student = studentDataJpa.findById(logDTO.getStudentId()).orElseThrow(IllegalArgumentException::new);

        Study study = studyDataJpa.findByDetailAndStudyTypeAndNumberAndCount(
                        logDTO.getStudyDetail(), logDTO.getStudyType(), logDTO.getStudyCount())
                .orElseThrow(IllegalArgumentException::new);

        if (logDTO.hasTextBookCode()) {
            TextBook textBook = textBookDataJpa.findByCode(logDTO.getTextBookCode()).orElseThrow(IllegalArgumentException::new);
            StudentLog studentLog = studentLogDataJpa.save(StudentLog.createStudentLog(student, study, textBook, logDTO));
            questionLogSaveAll(logDTO, textBook, studentLog);
            student.changeStudyStatus(studentLog);
        }else {
            StudentLog studentLog = studentLogDataJpa.save(StudentLog.createStudentLogNoneTextBook(student, study, logDTO));
            student.changeStudyStatus(studentLog);
        }
    }

    private void questionLogSaveAll(LogDTO logDTO, TextBook textBook, StudentLog studentLog) {
        List<Question> questionList = questionDataJpa.findByTextBookId(textBook.getId());
        Map<Integer, Integer> answerMap = logDTO.getAnswerMap();
        if (isSizeEqual(questionList, answerMap)) {
            createQuestionLogList(questionList, answerMap, studentLog);
            return;
        }
        throw new IllegalArgumentException("question size is not match");
    }

    private void createQuestionLogList(List<Question> questionList, Map<Integer, Integer> answerMap, StudentLog studentLog) {
        List<QuestionLog> questionLogList = new ArrayList<>();
        double totalQuestionScore = questionList.size() * 10;
        double totalStudentScore = 0;
        for (int i = 0; i < questionList.size(); i++) {
            Integer score = answerMap.get(i + 1);
            questionLogList.add(QuestionLog.createQuestionLog(questionList.get(i), studentLog, score));
            totalStudentScore += score;
        }
        studentLog.intelligibilityCalculator(totalQuestionScore, totalStudentScore);
        questionLogDataJpa.saveAll(questionLogList);
    }

    private boolean isSizeEqual(List<Question> questionList, Map<Integer, Integer> answerMap) {
        return questionList.size() == answerMap.size();
    }


}
