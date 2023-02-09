package SNP.management.domain.DTO;

import SNP.management.domain.enumlist.EyeBall;
import SNP.management.domain.enumlist.StudyType;
import SNP.management.web.form.student.SaveLogForm;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.Map;


@Data
@NoArgsConstructor
@ToString
public class LogDTO {
    private final static int EYE_BALL_A_FULL = 200;
    private final static int EYE_BALL_A = EYE_BALL_A_FULL / EyeBall.values().length;

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
    private Double intelligibility;
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

    private Map<Integer, Integer> answerMap;

    public void eyeBallCalculator() {
        if (this.rapidEyeball != null) {
            this.rapidEyeball *= EYE_BALL_A_FULL + (EYE_BALL_A * eyeBallCount.getCount());}
        else {this.rapidEyeball = 0;}
    }

    public void processingTimeCalculator() {
        this.processingTime = (this.processingMin * 60) + this.processingSec;
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
        this.intelligibility = intelligibility;
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

    public void changeAnswerMap(Map<Integer, Integer> answerForm) {
        this.answerMap = answerForm;
    }

}
