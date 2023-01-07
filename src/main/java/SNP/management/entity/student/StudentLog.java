package SNP.management.entity.student;

import SNP.management.entity.Teacher;
import SNP.management.entity.study.Study;
import SNP.management.entity.study.StudyType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "STUDY_LOG")
public class StudentLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private Classes classes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;

    @Enumerated
    private StudyType studyType;

    private int step;

    private LocalDateTime date;

    public StudentLog saveStudyLog(Student student, int step, Study study) {
        this.student = student;
        this.studyType = student.getStudyType();
        this.step = step;
        this.study = study;
        return this;
    }

    public String getStudyTypeToString() {
        return this.studyType.toString();
    }
}
