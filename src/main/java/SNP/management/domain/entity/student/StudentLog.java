package SNP.management.domain.entity.student;

import SNP.management.domain.DTO.LogDTO;
import SNP.management.domain.entity.BaseEntity;
import SNP.management.domain.entity.textbook.TextBook;
import SNP.management.domain.enumlist.EyeBall;
import SNP.management.domain.enumlist.StudyType;
import SNP.management.domain.entity.study.Study;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "STUDENT_LOG")
public class StudentLog extends BaseEntity {
    private final static int MIN = 60;
    private final static int EYEBALL_SIZE = EyeBall.values().length;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "text_book_id")
    private TextBook textBook;
    private Integer concentration;
    @Column(name = "concentration_answer")
    private Boolean concentrationAnswer;
    @Column(name = "rapid_eyeball")
    private Integer rapidEyeball;
    @Column(name = "eyeball_count")
    @Enumerated(EnumType.STRING)
    private EyeBall eyeBallCount;
    @Column(name = "eyeball_total")
    private Integer eyeballTotal;
    @Column(name = "figure_one")
    private Integer figureOne;
    @Column(name = "figure_one_clear")
    private Integer figureOneClear;
    @Column(name = "figure_two")
    private Integer figureTwo;
    @Column(name = "figure_two_clear")
    private Integer figureTwoClear;
    private Double intelligibility;
    @Column(name = "processing_time")
    private Integer processingTime;
    @Column(name = "processing_min")
    private Integer processingMin;
    @Column(name = "processing_sec")
    private Integer processingSec;
    @Column(name = "read_count")
    private Integer readCount;
    private String memo;

    @Enumerated(EnumType.STRING)
    @Column(name = "study_type")
    private StudyType studyType;

    @Column(name = "study_count")
    private Integer studyCount;


    @OneToMany(mappedBy = "studentLog")
    private List<QuestionLog> questionLog = new ArrayList<>();


    private StudentLog(Student student, Study study, TextBook textBook, Integer concentration, boolean concentrationAnswer,
                       Integer rapidEyeball, EyeBall eyeBallCount, Integer eyeballTotal, Integer figureOne, Integer figureTwo,
                       Integer figureOneClear, Integer figureTwoClear, String memo, StudyType studyType, Integer studyCount,
                       Integer processingMin, Integer processingSec, Integer readCount) {
        this.student = student;
        this.study = study;
        this.textBook = textBook;
        this.concentration = concentration;
        this.concentrationAnswer = concentrationAnswer;
        this.rapidEyeball = rapidEyeball;
        this.eyeBallCount = eyeBallCount;
        this.eyeballTotal = eyeballTotal;
        this.figureOne = figureOne;
        this.figureOneClear = figureOneClear;
        this.figureTwo = figureTwo;
        this.figureTwoClear = figureTwoClear;
        this.memo = memo;
        this.studyType = studyType;
        this.studyCount = studyCount;
        this.processingMin = processingMin;
        this.processingSec = processingSec;
        this.readCount = readCount;
        sumPractice();
    }

    private StudentLog(Student student, Study study, Integer concentration, boolean concentrationAnswer, Integer rapidEyeball, EyeBall eyeballCount,
                       Integer eyeballTotal, Integer figureOne, Integer figureTwo, String memo, StudyType studyType, Integer studyCount, Integer readCount) {
        this.student = student;
        this.study = study;
        this.concentration = concentration;
        this.concentrationAnswer = concentrationAnswer;
        this.rapidEyeball = rapidEyeball == null ? 0 : rapidEyeball;
        this.eyeBallCount = eyeballCount;
        this.eyeballTotal = eyeballTotal;
        this.figureOne = figureOne;
        this.figureTwo = figureTwo;
        this.memo = memo;
        this.studyType = studyType;
        this.studyCount = studyCount == null ? 0 : studyCount;
        this.readCount = readCount;
        sumEyeBall();
    }

    private StudentLog(Student student) {
        this.student = student;
        this.study = student.getStudy();
        this.studyType = student.getStudyType();
        this.studyCount = 0;

    }

    public void changeStudyCount(Integer studyCount) {
        this.studyCount = studyCount;
    }

    public static StudentLog createStudentLog(Student student, Study study, TextBook textBook, LogDTO logDTO) {
        return new StudentLog(student, study, textBook, logDTO.getConcentration(), logDTO.getConcentrationAnswer(),
                logDTO.getRapidEyeball(), logDTO.getEyeBallCount(), logDTO.getEyeballTotal(), logDTO.getFigureOne(), logDTO.getFigureTwo(),
                logDTO.getFigureOneClear(), logDTO.getFigureTwoClear(), logDTO.getMemo(), logDTO.getStudyType(), logDTO.getStudyCount(),
                logDTO.getProcessingMin(), logDTO.getProcessingSec(), logDTO.getReadCount());
    }

    public static StudentLog createStudentLogNoneTextBook(Student student, Study study, LogDTO logDTO) {
        return new StudentLog(student, study, logDTO.getConcentration(), logDTO.getConcentrationAnswer(), logDTO.getRapidEyeball(),
                logDTO.getEyeBallCount(), logDTO.getEyeballTotal(), logDTO.getFigureOne(), logDTO.getFigureTwo(),
                logDTO.getMemo(), logDTO.getStudyType(), logDTO.getStudyCount(), logDTO.getReadCount());
    }

    public static StudentLog createFirstStudentLog(Student student) {
        return new StudentLog(student);
    }

    public void updateStudentLogWithTextBook(Study study, Student student, TextBook textBook,
                                             Integer concentration, boolean concentrationAnswer,
                                             Integer rapidEyeball, EyeBall eyeBallCount, Integer eyeballTotal, Integer figureOne, Integer figureTwo,
                                             Integer figureOneClear, Integer figureTwoClear, String memo, StudyType studyType, Integer studyCount,
                                             Integer processingMin, Integer processingSec, Integer readCount) {
        this.student = student;
        this.study = study;
        this.textBook = textBook;
        this.concentration = concentration;
        this.concentrationAnswer = concentrationAnswer;
        this.rapidEyeball = rapidEyeball;
        this.eyeBallCount = eyeBallCount;
        this.eyeballTotal = eyeballTotal;
        this.figureOne = figureOne;
        this.figureOneClear = figureOneClear;
        this.figureTwo = figureTwo;
        this.figureTwoClear = figureTwoClear;
        this.memo = memo;
        this.studyType = studyType;
        this.studyCount = studyCount;
        this.processingMin = processingMin;
        this.processingSec = processingSec;
        this.readCount = readCount;
        sumPractice();
    }

    public void updateStudentLogNoneTextBook(
            Study study, Student student, Integer concentration, boolean concentrationAnswer, Integer rapidEyeball, EyeBall eyeballCount,
            Integer eyeballTotal, Integer figureOne, Integer figureTwo, String memo, StudyType studyType, Integer studyCount, Integer readCount) {
        this.student = student;
        this.study = study;
        this.concentration = concentration;
        this.concentrationAnswer = concentrationAnswer;
        this.rapidEyeball = rapidEyeball == null ? 0 : rapidEyeball;
        this.eyeBallCount = eyeballCount;
        this.eyeballTotal = eyeballTotal;
        this.figureOne = figureOne;
        this.figureTwo = figureTwo;
        this.memo = memo;
        this.studyType = studyType;
        this.studyCount = studyCount == null ? 0 : studyCount;
        this.readCount = readCount;
        sumEyeBall();
    }

    public boolean hasTextBook() {
        return this.textBook != null;
    }

    private void sumPractice() {
        sumProcessingTime();
        sumEyeBall();
    }

    private void sumProcessingTime() {
        this.processingTime = (this.processingMin * MIN) + this.processingSec;
    }

    private void sumEyeBall() {
        if (hasEyeballScore()) {
            this.eyeballTotal = (this.rapidEyeball * EYEBALL_SIZE) + this.eyeBallCount.count();
        } else {
            this.eyeballTotal = 0;
        }
    }

    public boolean hasEyeballScore() {
        return this.eyeBallCount != null;
    }

    public void intelligibilityCalculator(double totalQuestionScore, double totalStudentScore) {
        double result = totalStudentScore / totalQuestionScore * 100;
        this.intelligibility = Math.round(result * 100.0) / 100.0;
    }

}
