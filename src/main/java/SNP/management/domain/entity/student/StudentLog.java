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

    private void setStudentLogWithTextBook(Student student, Study study,TextBook textBook, LogDTO logDTO ) {
        this.student = student;
        this.study = study;
        this.concentration = logDTO.getConcentration();
        this.concentrationAnswer = logDTO.getConcentrationAnswer();
        this.rapidEyeball = logDTO.getRapidEyeball();
        this.eyeBallCount = logDTO.getEyeBallCount();
        this.eyeballTotal = logDTO.getEyeballTotal();
        this.figureOne = logDTO.getFigureOne();
        this.figureOneClear = logDTO.getFigureOneClear();
        this.figureTwo = logDTO.getFigureTwo();
        this.figureTwoClear = logDTO.getFigureTwoClear();
        this.memo = logDTO.getMemo();
        this.studyType = student.getStudyType();
        this.studyCount = logDTO.getStudyCount();
        this.textBook = textBook;
        this.processingMin = logDTO.getProcessingMin();
        this.processingSec = logDTO.getProcessingSec();
        this.readCount = logDTO.getReadCount();
        sumPractice();

    }



    private void setStudentLogNoneTextBook(Student student, Study study, LogDTO logDTO ) {
        this.student = student;
        this.study = study;
        this.concentration = logDTO.getConcentration();
        this.concentrationAnswer = logDTO.getConcentrationAnswer();
        this.rapidEyeball = logDTO.getRapidEyeball() == null? 0 : logDTO.getRapidEyeball();
        this.eyeBallCount = logDTO.getEyeBallCount();
        this.eyeballTotal = logDTO.getEyeballTotal();
        this.figureOne = logDTO.getFigureOne();
        this.figureTwo = logDTO.getFigureTwo();
        this.memo = logDTO.getMemo();
        this.studyType = student.getStudyType();
        this.studyCount = logDTO.getStudyCount() == null? 0 : logDTO.getStudyCount();
        this.readCount = logDTO.getReadCount();
        sumEyeBall();
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

    public void intelligibilityCalculator (double totalQuestionScore, double totalStudentScore) {
        double result =totalStudentScore / totalQuestionScore * 100;
        this.intelligibility = Math.round(result * 100.0) / 100.0;
    }
    private StudentLog(Student student) {
        this.student = student;
        this.study = student.getStudy();
        this.studyType = student.getStudyType();
        this.studyCount = 0;

    }

    public void changeStudyCount(Integer studyCount){
        this.studyCount = studyCount;
    }

    public static StudentLog createStudentLog(Student student, Study study, TextBook textBook , LogDTO logDTO ){
        StudentLog studentLog = new StudentLog();
        studentLog.setStudentLogWithTextBook(student, study, textBook, logDTO);
        return studentLog;
    }
    public static StudentLog createStudentLogNoneTextBook(Student student, Study study,  LogDTO logDTO ){
        StudentLog studentLog = new StudentLog();
        studentLog.setStudentLogNoneTextBook(student, study, logDTO);
        return studentLog;
    }


    public static StudentLog createFirstStudentLog(Student student){
        return new StudentLog(student);
    }

    public void updateStudentLogWithTextBook(LogDTO logDTO, Study study, Student student, TextBook textBook) {
        this.setStudentLogWithTextBook(student,study,textBook,logDTO);
    }
    public void updateStudentLogNoneTextBook(LogDTO logDTO, Study study, Student student) {
        this.setStudentLogNoneTextBook(student,study,logDTO);
    }

    public boolean hasTextBook () {
        return this.textBook != null;
    }

}
