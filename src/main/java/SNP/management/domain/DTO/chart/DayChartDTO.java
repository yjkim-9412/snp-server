package SNP.management.domain.DTO.chart;

import SNP.management.domain.entity.student.StudentLog;
import lombok.Data;

import java.time.format.DateTimeFormatter;

@Data
public class DayChartDTO {

    private Integer figureOne;
    private Integer figureOneClear;
    private Integer figureTwo;
    private Integer figureTwoClear;
    private Integer rapidEyeball;
    private Integer eyeBallCount;
    private Integer eyeBallCountToNum;
    private Integer concentration;
    private Boolean concentrationAnswer;
    private String createDate;


    public DayChartDTO(StudentLog studentLog) {
        this.figureOne = studentLog.getFigureOne();
        this.figureOneClear = studentLog.getFigureOneClear();
        this.figureTwo = studentLog.getFigureTwo();
        this.figureTwoClear = studentLog.getFigureTwoClear();
        this.rapidEyeball = studentLog.getRapidEyeball();
        this.eyeBallCount= studentLog.getEyeBallCount().count();
        this.concentration = studentLog.getConcentration();
        this.concentrationAnswer = studentLog.getConcentrationAnswer();
        this.createDate = studentLog.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

    }

    public static DayChartDTO createDayChartDTO(StudentLog studentLog) {
        return new DayChartDTO(studentLog);
    }
}
