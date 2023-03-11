package SNP.management.domain.service.student;

import SNP.management.domain.DTO.LogDTO;
import SNP.management.domain.DTO.StudentDTO;
import SNP.management.domain.DTO.chart.TextBookChartDTO;
import SNP.management.domain.entity.student.QuestionLog;
import SNP.management.domain.entity.student.Student;

import SNP.management.domain.entity.student.StudentLog;
import SNP.management.domain.entity.study.Study;
import SNP.management.domain.entity.textbook.Question;
import SNP.management.domain.entity.textbook.TextBook;
import SNP.management.domain.enumlist.TextBookType;
import SNP.management.domain.repository.StudyDataJpa;
import SNP.management.domain.repository.StudyRepository;
import SNP.management.domain.repository.student.*;
import SNP.management.domain.repository.textbook.QuestionDataJpa;
import SNP.management.domain.repository.textbook.TextBookDataJpa;
import SNP.management.domain.service.schedule.RequestScheduleService;
import SNP.management.domain.exceptionlist.ScheduleException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class StudentLogService {
    private static final String NOT_EQUAL_QUESTION = "is not same TextBook Question";
    private static final String NONE_STUDENT_LOG = "not found StudentLog";
    private static final String NONE_TEXTBOOK = "not found TextBook";
    private static final TextBookType[] TEXTBOOK_ARRAY = TextBookType.values();

    private final StudentDataJpa studentDataJpa;
    private final StudyDataJpa studyDataJpa;
    private final StudentLogDataJpa studentLogDataJpa;
    private final QuestionLogDataJpa questionLogDataJpa;
    private final TextBookDataJpa textBookDataJpa;
    private final QuestionDataJpa questionDataJpa;
    private final StudyRepository studyRepository;
    private final RequestScheduleService requestScheduleService;
    private final StudentLogRepository studentLogRepository;
    private final QuestionLogRepository questionLogRepository;


    protected void saveFirstLog(Student student) {
        student.changeStudy(studyRepository.getFirstStudy(student.getStudyType()));
        StudentLog studentLog = studentLogDataJpa.save(StudentLog.createFirstStudentLog(student));
        log.info("studentLog.getStudyType() = {}", studentLog.getStudyType());
    }

    public void studentLogUpdater(StudentDTO studentDTO, Student student) {
        student.changeStudyType(studentDTO.getStudyType());
        saveFirstLog(student);
    }
    public void saveTodayLog(LogDTO logDTO, Integer today) {

        if (requestScheduleService.hasTodaySchedule(logDTO.getStudentId(), today)) {
            createStudentLog(logDTO);
        } else {
            throw new ScheduleException(ScheduleException.NONE_SCHEDULE);
        }
    }

    public List<LogDTO> findAllByStudentId(Long studentId) {
        List<LogDTO> logDTOList = new ArrayList<>();
        List<StudentLog> studentLogList = studentLogRepository.findFetchAllByStudentId(studentId);

        for (StudentLog studentLog : studentLogList) {
            logDTOList.add(LogDTO.createLogDTOByStudentLog(studentLog));
        }
        return logDTOList;
    }

    private void createStudentLog(LogDTO logDTO) {
        Student student = studentDataJpa.findById(logDTO.getStudentId()).orElseThrow(IllegalArgumentException::new);

        Study study = studyDataJpa.findByDetailAndStudyTypeAndNumberAndCount(
                        logDTO.getStudyDetail(), logDTO.getStudyType(), logDTO.getStudyCount())
                .orElseThrow(IllegalArgumentException::new);

        if (logDTO.hasLogId()) {
            updateStudentLog(logDTO, study, student);
            return;
        }

        Optional<TextBook> textBookOptional = hasTextBook(logDTO);
        if (textBookOptional.isPresent()) {
            TextBook textBook = textBookOptional.get();
            createWithTextBook(logDTO, student, study, textBook);
        } else {
            createNoneTextBook(logDTO, student, study);
        }

    }

    private void createNoneTextBook(LogDTO logDTO, Student student, Study study) {
        StudentLog studentLog = studentLogDataJpa.save(StudentLog.createStudentLogNoneTextBook(student, study, logDTO));
        student.changeStudyStatus(studentLog);
    }

    private void createWithTextBook(LogDTO logDTO, Student student, Study study, TextBook textBook) {
        StudentLog studentLog = studentLogDataJpa.save(StudentLog.createStudentLog(student, study, textBook, logDTO));
        questionLogSaveAll(logDTO, textBook, studentLog);
    }

    private Optional<TextBook> hasTextBook(LogDTO logDTO) {
        if (logDTO.hasTextBookCode()) {
            return getTextBook(logDTO);
        } else {
            return Optional.empty();
        }
    }

    private Optional<TextBook> getTextBook(LogDTO logDTO) {
        Optional<TextBook> findByCode = textBookDataJpa.findByCode(logDTO.getTextBookCode());
        if (findByCode.isEmpty()) {
            throw new IllegalArgumentException(NONE_TEXTBOOK);
        }
        return findByCode;
    }

    private void updateStudentLog(LogDTO logDTO, Study study, Student student) {
        StudentLog studentLog = studentLogDataJpa.findById(logDTO.getId()).orElseThrow(() -> new IllegalArgumentException(NONE_STUDENT_LOG));
        Optional<TextBook> textBookOptional = hasTextBook(logDTO);
        if (textBookOptional.isPresent()) {
            log.info("studentUpdate textBook has = {}", true);
            TextBook logDTOTextBook = textBookOptional.get();
            questionLogUpdate(logDTO, logDTOTextBook, studentLog);
            studentLog.updateStudentLogWithTextBook(logDTO, study, student, logDTOTextBook);
        } else {
            log.info("studentUpdate textBook has = {}", false);
            studentLog.updateStudentLogNoneTextBook(logDTO, study, student);
        }


    }

    private void questionLogUpdate(LogDTO logDTO, TextBook logDTOTextBook, StudentLog studentLog) {
        Map<Integer, Integer> answerMap = logDTO.getAnswerMap();
        List<QuestionLog> questionLogList = studentLog.getQuestionLog();

        if (isSameTextBook(logDTOTextBook, studentLog.getTextBook())) {
            log.info("studentUpdate textBook is same = {}", true);

            updateQuestionLogList(studentLog, answerMap, questionLogList);
        } else {
            log.info("studentUpdate textBook is same = {}", false);
            List<Question> questionList = logDTOTextBook.getQuestionList();
            questionLogList.clear();
            createQuestionLogList(questionList, answerMap, studentLog);
        }

    }

    private void updateQuestionLog(StudentLog studentLog, Map<Integer, Integer> answerMap, List<QuestionLog> questionLogList) {
        double totalQuestionScore = questionLogList.size() * 10;
        double totalStudentScore = 0;
        for (int i = 0; i < answerMap.size(); i++) {
            Integer score = answerMap.get(i + 1);
            QuestionLog questionLog = questionLogList.get(i);
            questionLog.changeQuestionScore(score);
            totalStudentScore += score;
        }
        studentLog.intelligibilityCalculator(totalQuestionScore, totalStudentScore);
    }

    private void updateQuestionLogList(StudentLog studentLog, Map<Integer, Integer> answerMap, List<QuestionLog> questionLogList) {
        if (isLogSizeEqual(questionLogList, answerMap)) {
            updateQuestionLog(studentLog, answerMap, questionLogList);
        } else {
            throw new IllegalArgumentException(NOT_EQUAL_QUESTION);
        }
    }



    private boolean isSameTextBook(TextBook logDTOTextBook, TextBook textBook) {
        String existingCode = textBook.getCode();
        String newCode = logDTOTextBook.getCode();
        return existingCode.equals(newCode);
    }

    private void questionLogSaveAll(LogDTO logDTO, TextBook textBook, StudentLog studentLog) {
        List<Question> questionList = questionDataJpa.findByTextBookId(textBook.getId());
        Map<Integer, Integer> answerMap = logDTO.getAnswerMap();
        if (isSizeEqual(questionList, answerMap)) {
            createQuestionLogList(questionList, answerMap, studentLog);
            return;
        }
        throw new IllegalArgumentException(NOT_EQUAL_QUESTION);
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

        for (QuestionLog questionLog : questionLogList) {
            questionLogRepository.saveAndFlush(questionLog);
        }
    }

    private boolean isSizeEqual(List<Question> questionList, Map<Integer, Integer> answerMap) {
        return questionList.size() == answerMap.size();
    }

    private boolean isLogSizeEqual(List<QuestionLog> questionLogList, Map<Integer, Integer> answerMap) {
        return questionLogList.size() == answerMap.size();
    }


    public Map<TextBookType, List<TextBookChartDTO>> getTextBookChart(Long studentId) {
        Map<TextBookType, List<TextBookChartDTO>> textBookChartMap = new HashMap<>();
        for (TextBookType textBookType : TEXTBOOK_ARRAY) {
            List<TextBookChartDTO> textBookChartDTOList = studentLogRepository.findStudentLogAvgByStudentIdAndCode(studentId, textBookType.code());
            if (!textBookChartDTOList.isEmpty()) {
                textBookChartMap.put(textBookType, textBookChartDTOList);
            }
        }
        return textBookChartMap;
    }

    public LogDTO getStudentLog(Long logId) {
        StudentLog studentLog = studentLogDataJpa.findById(logId).orElseThrow(() -> new IllegalArgumentException("not found studentLog"));
        LogDTO logDTO = LogDTO.createLogDTOByStudentLog(studentLog);
        if (studentLog.hasTextBook()) {
            log.info("getStudentLog has textBook = {}",true);
            convertQuestionLogListToMap(studentLog, logDTO);
        }

        return logDTO;
    }

    private void convertQuestionLogListToMap(StudentLog studentLog, LogDTO logDTO) {
        List<QuestionLog> questionLogList = studentLog.getQuestionLog();
        Map<Integer, Integer> answerMap = new LinkedHashMap<>();
        for (int i = 0; i < questionLogList.size(); i++) {
            answerMap.put(i + 1, questionLogList.get(i).getScore());
        }
        logDTO.setAnswerMap(answerMap);
    }
}
