package SNP.management.domain.entity.basictest;

import SNP.management.domain.entity.BaseEntity;
import SNP.management.domain.entity.student.Student;
import SNP.management.domain.enumlist.basictest.StudyHabitType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "STUDY_HABITS")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyHabits extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyHabitId;
    private Integer score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @Enumerated(EnumType.STRING)
    private StudyHabitType studyHabitType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "average_id")
    private BasicTestAvg basicTestAvg;


    public static StudyHabits createStudyHabits(Integer score, Student student, BasicTestAvg basicTestAvg, StudyHabitType studyHabitType) {
        return new StudyHabits(score, student, basicTestAvg, studyHabitType );
    }

    private StudyHabits(Integer score, Student student, BasicTestAvg basicTestAvg, StudyHabitType studyHabitType) {
        this.score = score;
        this.student = student;
        this.basicTestAvg = basicTestAvg;
        this.studyHabitType = studyHabitType;
    }
}
