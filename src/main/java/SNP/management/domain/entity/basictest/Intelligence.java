package SNP.management.domain.entity.basictest;

import SNP.management.domain.entity.BaseEntity;
import SNP.management.domain.entity.student.Student;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "INTELLIGENCE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Intelligence extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long intelligenceId;

    private Integer score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "average_id")
    private BasicTestAvg basicTestAvg;


    public static Intelligence createIntelligence(Integer score, Student student, BasicTestAvg basicTestAvg) {
        return new Intelligence(score, student, basicTestAvg);
    }

    protected Intelligence(Integer score, Student student, BasicTestAvg basicTestAvg) {
        this.score = score;
        this.student = student;
        this.basicTestAvg = basicTestAvg;
    }
}
