package SNP.management.domain.entity.textbook;

import SNP.management.domain.DTO.TextBookDTO;
import SNP.management.domain.entity.BaseEntity;
import SNP.management.domain.entity.Category;
import SNP.management.domain.enumlist.TextBookType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Table(name = "TEXT_BOOK")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Slf4j
public class TextBook extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "text_book_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private String code;

    @Column(name = "text_book_type")
    @Enumerated(EnumType.STRING)
    private TextBookType textBookType;

    @Column(name = "number_of_characters")
    private Integer numberOfCharacters;
    @Column(name = "question_count")
    private Integer questionCount;



    public void createCode() {
        if (this.id != null) {
            this.code = this.getTextBookType().code() + this.id;
            log.info("code = {}", this.code);
        }else {
            throw new NullPointerException("createCode id is empty");
        }
    }
    public static TextBook createTextBook(TextBookDTO textBookDTO, Category category) {
        TextBook textBook = new TextBook();
        textBook.category = category;
        textBook.textBookType = textBookDTO.getTextBookType();
        textBook.numberOfCharacters = textBookDTO.getNumberOfCharacters();
        return textBook;
    }
}
