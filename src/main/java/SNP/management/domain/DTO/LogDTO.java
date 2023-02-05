package SNP.management.domain.DTO;

import SNP.management.domain.enumlist.EyeBall;
import SNP.management.domain.enumlist.StudyType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;


@Data
public class LogDTO {
    private final static int EYE_BALL_A_FULL = 200;
    private final static int EYE_BALL_A = EYE_BALL_A_FULL / EyeBall.values().length;
    private Long id;
    private Long studentId;

    private String StudyDetail;
    private Integer studyCount;

    private Integer concentration;
    private Boolean concentrationAnswer;
    private Integer rapidEyeball;
    private EyeBall eyeBallCount;

    private Integer figureOne;
    private Integer figureTwo;

    private String textBookCode;
    private Double intelligibility;
    private Integer processingTime;
    private Integer readCount;


    private String memo;
    private StudyType studyType;
    private Integer dayOfWeek;

    private Map<Integer, Integer> answerMap;

    public void eyeBallCalculator() {
        if (this.rapidEyeball != null) {
            this.rapidEyeball *= EYE_BALL_A_FULL + (EYE_BALL_A * eyeBallCount.getCount());}
        else {this.rapidEyeball = 0;}
    }

    public LogDTO(Long studentId, String studyDetail, Integer studyCount, Integer concentration, Boolean concentrationAnswer, Integer rapidEyeball, EyeBall eyeBallCount, Integer figureOne, Integer figureTwo, String textBookCode, Double intelligibility, Integer processingTime, Integer readCount, String memo, StudyType studyType, Integer dayOfWeek, Map<Integer, Integer> answerMap) {
        this.studentId = studentId;
        this.StudyDetail = studyDetail;
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
}
