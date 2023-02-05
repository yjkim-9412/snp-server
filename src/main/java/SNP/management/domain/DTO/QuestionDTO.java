package SNP.management.domain.DTO;

import SNP.management.domain.entity.textbook.Question;
import SNP.management.domain.enumlist.AnswerType;
import SNP.management.domain.enumlist.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@NoArgsConstructor
public class QuestionDTO {

    private QuestionType QuestionType;
    private Integer number;
    private AnswerType answerType;

    private QuestionDTO(QuestionType QuestionType, Integer number, AnswerType answerType) {
        this.QuestionType = QuestionType;
        this.number = number;
        this.answerType = answerType;
    }

    public static QuestionDTO createQuestionDTO(Question question) {
        return new QuestionDTO(question.getQuestionType(), question.getNumber(), question.getAnswerType());
    }


}
