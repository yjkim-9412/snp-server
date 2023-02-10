package SNP.management.domain.DTO;

import SNP.management.domain.entity.student.StudentLog;
import SNP.management.domain.entity.textbook.TextBook;
import SNP.management.domain.enumlist.EyeBall;
import SNP.management.domain.enumlist.StudyType;
import SNP.management.domain.enumlist.TextBookType;
import SNP.management.web.form.student.SaveLogForm;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.time.format.DateTimeFormatter;
import java.util.Map;


@Data
@NoArgsConstructor
@ToString
public class LogDTO {
    private final static int MIN = 60;

    private Long id;
    @NotNull
    private Long studentId;
    private String studyDetail;
    private Integer studyCount;
    private Integer concentration;
    private Boolean concentrationAnswer;
    @Digits(integer = 10,fraction = 0)
    private Integer rapidEyeball;

    private EyeBall eyeBallCount;
    private Integer figureOne;

    @Digits(integer = 10,fraction = 0)
    private Integer figureOneClear;

    private Integer figureTwo;

    @Digits(integer = 10,fraction = 0)
    private Integer figureTwoClear;
    private String textBookCode;
    private String textBookName;
    private TextBookType textBookType;
    private String textBookTypeString;
    private Double IntelligibilityReadOnly;
    private Integer processingTime;
    @Digits(integer = 60,fraction = 0)
    private int processingMin;
    @Digits(integer = 60,fraction = 0)
    private int processingSec;
    private Integer readCount;
    private String memo;
    private StudyType studyType;
    @Digits(integer = 7,fraction = 0)
    private Integer dayOfWeek;
    private String createDate;

    private Map<Integer, Integer> answerMap;


    public void combineProcessingTime() {
        this.processingTime = (this.processingMin * MIN) + this.processingSec;
    }

    private LogDTO(StudentLog studentLog){
        this.id = studentLog.getId();
        this.studentId = studentLog.getStudent().getId();
        this.studyDetail = studentLog.getStudy().getDetail();
        this.studyCount = studentLog.getStudyCount();
        this.concentration = studentLog.getConcentration();
        this.concentrationAnswer = studentLog.getConcentrationAnswer();
        this.rapidEyeball = studentLog.getRapidEyeball();
        this.eyeBallCount = studentLog.getEyeBallCount();
        this.figureOne = studentLog.getFigureOne();
        this.figureOneClear = studentLog.getFigureOneClear();
        this.figureTwo = studentLog.getFigureTwo();
        this.figureTwoClear = studentLog.getFigureTwoClear();
        if (studentLog.getTextBook() != null) {
            TextBook textBook = studentLog.getTextBook();
            this.textBookCode = textBook.getCode();
            this.textBookName = textBook.getName();
            this.textBookType = textBook.getTextBookType();
            this.textBookTypeString = this.textBookType.getString();
        }
        this.IntelligibilityReadOnly = studentLog.getIntelligibility();
        if (studentLog.getProcessingTime() != null) {
            this.processingTime = studentLog.getProcessingTime();
            this.processingMin = studentLog.getProcessingMin();
            this.processingSec = studentLog.getProcessingSec();
        }
        this.readCount = studentLog.getReadCount();
        this.memo = studentLog.getMemo();
        this.studyType = studentLog.getStudyType();
        this.createDate = studentLog.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    public LogDTO(Long studentId, String studyDetail, Integer studyCount, Integer concentration, Boolean concentrationAnswer, Integer rapidEyeball, EyeBall eyeBallCount, Integer figureOne, Integer figureTwo, String textBookCode, Double intelligibility, Integer processingTime, Integer readCount, String memo, StudyType studyType, Integer dayOfWeek, Map<Integer, Integer> answerMap) {
        this.studentId = studentId;
        this.studyDetail = studyDetail;
        this.studyCount = studyCount;
        this.concentration = concentration;
        this.concentrationAnswer = concentrationAnswer;
        this.rapidEyeball = rapidEyeball;
        this.eyeBallCount = eyeBallCount;
        this.figureOne = figureOne;
        this.figureTwo = figureTwo;
        this.textBookCode = textBookCode;
        this.IntelligibilityReadOnly = intelligibility;
        this.processingTime = processingTime;
        this.readCount = readCount;
        this.memo = memo;
        this.studyType = studyType;
        this.dayOfWeek = dayOfWeek;
        this.answerMap = answerMap;
    }


    public static LogDTO createLogDTO(SaveLogForm saveLogForm) {
        LogDTO logDTO = saveLogForm.getLogForm();
        logDTO.changeAnswerMap(saveLogForm.getAnswerMap());
        return logDTO;
    }
    public static LogDTO createLogDTOByStudentLog(StudentLog studentLog) {
        return new LogDTO(studentLog);
    }

    public void changeAnswerMap(Map<Integer, Integer> answerForm) {
        this.answerMap = answerForm;
    }

    public boolean hasTextBookCode() {
        return this.getTextBookCode() != null;
    }
}
