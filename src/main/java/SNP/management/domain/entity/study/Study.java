package SNP.management.domain.entity.study;

import SNP.management.domain.entity.BaseEntity;
import SNP.management.domain.enumlist.StudyType;
import lombok.*;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "studyType", discriminatorType = DiscriminatorType.STRING)
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "STUDY")
public abstract class Study extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_id")
    private Long id;
    private int step;
    private String level;
    private String detail;
    @Column(name = "number_of_days")
    private int numberOfDays;
    private int accumulation;
    @Column(name = "ot")
    private boolean OT;
    private String introduce;

    private int stepCount;

    @Column(name = "study_type")
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
