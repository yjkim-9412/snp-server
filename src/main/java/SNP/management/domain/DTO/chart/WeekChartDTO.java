package SNP.management.domain.DTO.chart;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class WeekChartDTO {



    private Integer eyeballPractice;
    private Integer figureOne;
    private Integer figureTwo;
    private Integer startDay;
    private Integer endDay;

    @QueryProjection
    public WeekChartDTO(Integer eyeballPractice, Integer figureOne, Integer figureTwo, Integer startDay, Integer endDay) {

        this.eyeballPractice = eyeballPractice;
        this.figureOne = figureOne;
        this.figureTwo = figureTwo;
        this.startDay = startDay;
        this.endDay = endDay;
    }
}
