package SNP.management.domain.entity.textbook;

import SNP.management.domain.DTO.QuestionDTO;
import SNP.management.domain.entity.BaseEntity;
import SNP.management.domain.enumlist.AnswerType;
import SNP.management.domain.enumlist.QuestionType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "QUESTION")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Question extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "text_book_id")
    private TextBook textBook;

    @Column(name = "q_type_id")
    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    private Integer number;

    @Enumerated(EnumType.STRING)
    @Column(name = "answer_type")
    private AnswerType answerType;

    public static Question createQuestion(QuestionDTO questionDTO, TextBook textBook){
        return new Question(textBook, questionDTO.getQuestionType(), questionDTO.getNumber(), questionDTO.getAnswerType());
    }

    public void changeQuestion(QuestionDTO questionDTO, TextBook textBook) {
        this.textBook = textBook;
        this.questionType = questionDTO.getQuestionType();
        this.number = questionDTO.getNumber();
        this.answerType = questionDTO.getAnswerType();
    }

    private Question(TextBook textBook, QuestionType questionType, Integer number, AnswerType answerType) {
        this.textBook = textBook;
        this.questionType = questionType;
        this.number = number != null ?  number : 0;
        this.answerType = answerType;
        this.textBook.getQuestionList().add(this);
    }
}
