package SNP.management.domain.entity.student;

import SNP.management.domain.entity.BaseEntity;
import SNP.management.domain.entity.textbook.Question;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "QUESTION_LOG")
@Getter
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

    private int score;


    public void saveStudentLog(StudentLog studentLog) {
        this.studentLog = studentLog;
        studentLog.getQuestionLog().add(this);
    }
}
