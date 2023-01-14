package SNP.management.domain.entity.study;

import lombok.*;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "studyType", discriminatorType = DiscriminatorType.STRING)
@Data
@Table(name = "STUDY")
public abstract class Study {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_id")
    private Long id;
    private int step;
    private String level;
    private String detail;
    private int numberOfDays;
    private int accumulation;
    private boolean OT;
    private String introduce;

    private int stepCount;

    @Column(name = "studyType",insertable = false,updatable = false)
    @Enumerated(EnumType.STRING)
    private StudyType studyType;


    public Study saveType(int step, String level, String detail, int numberOfDays, int accumulation, boolean OT, String introduce) {
        this.step = step;
        this.level = level;
        this.detail = detail;
        this.numberOfDays = numberOfDays;
        this.accumulation = accumulation;
        this.OT = OT;
        this.introduce = introduce;
        return this;
    }



}
