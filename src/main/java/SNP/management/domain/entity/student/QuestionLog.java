package SNP.management.domain.entity.student;

import SNP.management.domain.entity.BaseEntity;
import SNP.management.domain.entity.textbook.Question;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "QUESTION_LOG")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionLog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "q_log_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_log_id")
    private StudentLog studentLog;

    private Integer score;


    public void saveStudentLog(StudentLog studentLog) {
        this.studentLog = studentLog;
        studentLog.getQuestionLog().add(this);
    }

    private QuestionLog(Question question, StudentLog studentLog, int score) {
        this.question = question;
        this.score = score;
        this.saveStudentLog(studentLog);
    }

    public static QuestionLog createQuestionLog(Question question, StudentLog studentLog, Integer score) {
        return new QuestionLog(question, studentLog, score);
    }

}
