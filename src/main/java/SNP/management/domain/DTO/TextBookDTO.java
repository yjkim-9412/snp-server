package SNP.management.domain.DTO;

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

    public TextBookDTO(TextBookType textBookType, String name, Integer numberOfCharacters, String categoryName) {
        this.textBookType = textBookType;
        this.name = name;
        this.numberOfCharacters = numberOfCharacters;
        this.categoryName = categoryName;
    }
}
