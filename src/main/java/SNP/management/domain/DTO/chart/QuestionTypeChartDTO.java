package SNP.management.domain.DTO.chart;

import SNP.management.domain.enumlist.QuestionType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class QuestionTypeChartDTO {

    private Integer average;
    private Integer numberOfQuestion;
    private Integer totalScore;
    private QuestionType questionType;
    private String questionTypeString;


    @QueryProjection
    public QuestionTypeChartDTO(Integer average, Integer numberOfQuestion, Integer totalScore, QuestionType questionType) {
        this.average = average;
        this.numberOfQuestion = numberOfQuestion;
        this.totalScore = totalScore;
        this.questionType = questionType;
    }
    public void setQuestionTypeString() {
        this.questionTypeString = this.questionType.value();
    }
}
