package SNP.management.domain.entity.basictest;

import SNP.management.domain.DTO.BasicTestDTO;
import SNP.management.domain.entity.student.Student;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BASIC_TEST_AVERAGE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BasicTestAvg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long averageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "read_basic")
    private double readBasic;

    @Column(name = "intelligence")
    private double intelligence;

    @Column(name = "study_habits")
    private double studyHabits;

    public void CalculatorBasicTestAvg(BasicTestDTO basicTestDTO) {

    }

    public static BasicTestAvg createBasicTestAvg(Student student) {
        return new BasicTestAvg(student);
    }

    private BasicTestAvg(Student student) {
        this.student = student;
    }
}
