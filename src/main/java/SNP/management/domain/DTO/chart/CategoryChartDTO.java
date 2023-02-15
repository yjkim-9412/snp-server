package SNP.management.domain.DTO.chart;

import SNP.management.domain.enumlist.QuestionType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class CategoryChartDTO {

    private Integer average;
    private Integer numberOfQuestion;
    private Integer totalScore;
    private String categoryName;


    @QueryProjection
    public CategoryChartDTO(Integer average, Integer numberOfQuestion, Integer totalScore, String categoryName) {
        this.average = average;
        this.numberOfQuestion = numberOfQuestion;
        this.totalScore = totalScore;
        this.categoryName = categoryName;
    }
}
