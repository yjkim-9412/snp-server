package SNP.management.domain.entity.student;

import SNP.management.domain.DTO.LogDTO;
import SNP.management.domain.entity.BaseEntity;
import SNP.management.domain.entity.textbook.Question;
import SNP.management.domain.entity.textbook.TextBook;
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
@Table(name = "STUDENT_LOG")
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

    @ManyToOne(fetch = FetchType.LAZY)
    private TextBook textBook;
    private Integer concentration;
    private Boolean concentrationAnswer;
    @Column(name = "rapid_eyeball")
    private Integer rapidEyeball;
    @Column(name = "figure_one")
    private Integer figureOne;
    @Column(name = "figure_two")
    private Integer figureTwo;

    private Double intelligibility;
    private Integer processingTime;
    private Integer readCount;
    private String memo;

    @Enumerated(EnumType.STRING)
    @Column(name = "study_type")
    private StudyType studyType;

    @Column(name = "study_count")
    private Integer studyCount;


    @OneToMany(mappedBy = "studentLog")
    private List<QuestionLog> questionLog = new ArrayList<>();

    private StudentLog(Student student, Schedule schedule, Study study,TextBook textBook, LogDTO logDTO ) {
        this.student = student;
        this.schedule = schedule;
        this.study = study;
        this.concentration = logDTO.getConcentration();
        this.concentrationAnswer = logDTO.getConcentrationAnswer();
        this.rapidEyeball = logDTO.getRapidEyeball();
        this.figureOne = logDTO.getFigureOne();
        this.figureTwo = logDTO.getFigureTwo();
        this.intelligibility = logDTO.getIntelligibility();
        this.memo = logDTO.getMemo();
        this.studyType = student.getStudyType();
        this.studyCount = logDTO.getStudyCount() == null? 0 : logDTO.getStudyCount();
        this.textBook = textBook;
    }
    private StudentLog(Student student) {
        this.student = student;
        this.study = student.getStudy();
        this.studyType = student.getStudy().getStudyType();
        this.studyCount = 0;

    }
    public void changeStudyCount(Integer studyCount){
        this.studyCount = studyCount;
    }

    public static StudentLog createStudentLog(Student student, Schedule schedule, Study study, TextBook textBook , LogDTO logDTO ){
        return new StudentLog(student, schedule, study,textBook, logDTO);
    }
    public static StudentLog createFirstStudentLog(Student student){
        return new StudentLog(student);
    }

}
