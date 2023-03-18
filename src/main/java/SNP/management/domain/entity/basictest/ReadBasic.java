package SNP.management.domain.entity.basictest;

import SNP.management.domain.entity.BaseEntity;
import SNP.management.domain.entity.student.Student;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "READ_BASIC")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReadBasic extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long readBasicId;

    private Integer score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "average_id")
    private BasicTestAvg basicTestAvg;


    public static ReadBasic createReadBasic(Integer score, Student student, BasicTestAvg basicTestAvg) {
        return new ReadBasic(score, student, basicTestAvg);
    }

    public ReadBasic(Integer score, Student student, BasicTestAvg basicTestAvg) {
        this.score = score;
        this.student = student;
        this.basicTestAvg = basicTestAvg;
    }
}
