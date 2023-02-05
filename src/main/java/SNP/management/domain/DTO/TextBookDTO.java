package SNP.management.domain.DTO;

import SNP.management.domain.entity.textbook.TextBook;
import SNP.management.domain.enumlist.TextBookType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TextBookDTO {

    private Long id;
    private String code;
    private String classification;
    private TextBookType textBookType;
    private String name;
    private Integer numberOfCharacters;
    private Integer questionCount;
    private Integer categoryId;
    private String categoryName;

    public TextBookDTO(TextBookType textBookType, String name, Integer numberOfCharacters, Integer categoryId) {
        this.classification = textBookType.getString();
        this.name = name;
        this.numberOfCharacters = numberOfCharacters;
        this.categoryId= categoryId;
        this.textBookType = textBookType;
    }



    private TextBookDTO(TextBook textBook) {
        this.id = textBook.getId();
        this.code = textBook.getCode();
        this.classification = textBook.getTextBookType().getString();
        this.name = textBook.getName();
        this.numberOfCharacters = textBook.getNumberOfCharacters();
        this.questionCount = textBook.getQuestionCount();
        this.categoryName = textBook.getCategory().getName();
        this.categoryId = textBook.getCategory().getId();
        this.textBookType = textBook.getTextBookType();
    }

    public void changeTextBookDTO(TextBook textBook) {
        this.id = textBook.getId();
        this.code = textBook.getCode();
        this.textBookType = textBook.getTextBookType();
        this.name = textBook.getName();
        this.numberOfCharacters = textBook.getNumberOfCharacters();
        this.questionCount = textBook.getQuestionCount();
        this.categoryName = textBook.getCategory().getName();
        this.classification = this.textBookType.getString();
    }

    public static TextBookDTO createDTO(TextBook textBook) {
        return new TextBookDTO(textBook);
    }
    @QueryProjection
    public TextBookDTO(Long id, String code, TextBookType textBookType, String name, Integer numberOfCharacters, Integer questionCount, Integer categoryId, String categoryName) {
        this.id = id;
        this.code = code;
        this.textBookType = textBookType;
        this.name = name;
        this.numberOfCharacters = numberOfCharacters;
        this.questionCount = questionCount;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.classification = textBookType.getString();
    }

    public boolean hasCode() {
        String code = this.code;
        return code != null && !code.equals("");
    }
}
