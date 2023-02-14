package SNP.management.domain.DTO.chart;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class MonthChartDTO {

    private Integer eyeballPractice;
    private Integer figureOne;
    private Integer figureTwo;
    private Integer concentration;
    private Boolean concentrationAnswer;
    private Integer startDay;
    private Integer endDay;

    @QueryProjection
    public MonthChartDTO(Integer eyeballPractice, Integer figureOne, Integer figureTwo, Integer concentration, Boolean concentrationAnswer, Integer startDay, Integer endDay) {
        this.eyeballPractice = eyeballPractice;
        this.figureOne = figureOne;
        this.figureTwo = figureTwo;
        this.concentration = concentration;
        this.concentrationAnswer = concentrationAnswer;
        this.startDay = startDay;
        this.endDay = endDay;
    }

}
