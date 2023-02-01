package SNP.management.domain.DTO;

import SNP.management.domain.enumlist.EyeBall;
import SNP.management.domain.enumlist.StudyType;
import lombok.Data;


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

    private Long textBookCode;
    private Double intelligibility;
    private int processingTime;


    private String memo;
    private StudyType studyType;
    private Integer dayOfWeek;


    public void eyeBallCalculator() {
        this.rapidEyeball *= EYE_BALL_A_FULL + (EYE_BALL_A * eyeBallCount.getCount());
    }


}
