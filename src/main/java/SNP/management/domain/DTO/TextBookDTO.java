package SNP.management.domain.DTO;

import SNP.management.domain.entity.textbook.TextBook;
import SNP.management.domain.enumlist.TextBookType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TextBookDTO {

    private Long id;
    private String code;
    private TextBookType textBookType;
    private String name;
    private Integer numberOfCharacters;
    private Integer questionCount;
    private Integer categoryId;
    private String categoryName;

    public TextBookDTO(TextBookType textBookType, String name, Integer numberOfCharacters, Integer categoryId) {
        this.textBookType = textBookType;
        this.name = name;
        this.numberOfCharacters = numberOfCharacters;
        this.categoryId= categoryId;
    }

    private TextBookDTO(TextBook textBook) {
        this.id = textBook.getId();
        this.code = textBook.getCode();
        this.textBookType = textBook.getTextBookType();
        this.name = textBook.getName();
        this.numberOfCharacters = textBook.getNumberOfCharacters();
        this.questionCount = textBook.getQuestionCount();
        this.categoryName = textBook.getCategory().getName();
        this.categoryId = textBook.getCategory().getId();
    }

    public void changeTextBookDTO(TextBook textBook) {
        this.id = textBook.getId();
        this.code = textBook.getCode();
        this.textBookType = textBook.getTextBookType();
        this.name = textBook.getName();
        this.numberOfCharacters = textBook.getNumberOfCharacters();
        this.questionCount = textBook.getQuestionCount();
        this.categoryName = textBook.getCategory().getName();
    }

    public static TextBookDTO createDTO(TextBook textBook) {
        return new TextBookDTO(textBook);
    }
}
