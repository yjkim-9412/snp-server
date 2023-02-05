package SNP.management.web.form;

import SNP.management.domain.DTO.QuestionDTO;
import SNP.management.domain.DTO.TextBookDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TextBookForm {
    private TextBookDTO textBookForm;
    private List<QuestionDTO> questionArray;

    public TextBookForm(TextBookDTO textBookForm, List<QuestionDTO> questionArray) {
        this.textBookForm = textBookForm;
        this.questionArray = questionArray;
    }
}
