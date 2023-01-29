package SNP.management.domain.DTO;

import SNP.management.domain.enumlist.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {

    private Long id;
    private Long textBookId;
    private QuestionType QuestionType;
    private Integer number;
    private Boolean pattern;

    public QuestionDTO(QuestionType QuestionType, Integer number, Boolean pattern) {
        this.QuestionType = QuestionType;
        this.number = number;
        this.pattern = pattern;
    }


}
