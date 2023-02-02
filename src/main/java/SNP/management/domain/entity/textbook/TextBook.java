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
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TEXT_BOOK", indexes = @Index(name = "idx_code", columnList = "code"))
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
    private String name;

    @Column(name = "text_book_type")
    @Enumerated(EnumType.STRING)
    private TextBookType textBookType;

    @Column(name = "number_of_characters")
    private Integer numberOfCharacters;
    @Column(name = "question_count")
    private Integer questionCount;

    @OneToMany(mappedBy = "textBook",orphanRemoval = true)
    private List<Question> questionList = new ArrayList<>();

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
        textBook.name = textBookDTO.getName();
        return textBook;
    }

    public void ChangeTextBook(TextBookDTO textBookDTO, Category category) {
        this.category = category;
        this.textBookType = textBookDTO.getTextBookType();
        this.numberOfCharacters = textBookDTO.getNumberOfCharacters();
    }
    public void createQuestionCount(Integer questionSize){
        this.questionCount = questionSize;
    }
}
