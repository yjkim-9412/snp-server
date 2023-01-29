package SNP.management.domain.entity.student;

import SNP.management.domain.entity.BaseEntity;
import SNP.management.domain.enumlist.StudyType;
import SNP.management.domain.entity.Teacher;
import SNP.management.domain.entity.study.Study;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "STUDY_LOG")
public class StudentLog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedules_id")
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;

    private int concentration;
    @Column(name = "rapid_eyeball")
    private int rapidEyeball;
    @Column(name = "figure_one")
    private int figureOne;
    @Column(name = "figure_two")
    private int figureTwo;

    private double intelligibility;
    private String memo;

    @Enumerated
    @Column(name = "study_type")
    private StudyType studyType;

    @Column(name = "study_count")
    private Integer studyCount;

    @OneToMany(mappedBy = "studentLog")
    private List<QuestionLog> questionLog = new ArrayList<>();

}
