package SNP.management.domain.entity.study;

import SNP.management.domain.entity.BaseEntity;
import SNP.management.domain.enumlist.StudyType;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "STUDY")
@Getter
public class Study extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_id")
    private Long id;
    private Integer step;
    private String level;
    private String detail;
    @Column(name = "number_of_days")
    private Integer numberOfDays;
    private Integer accumulation;
    @Column(name = "ot")
    private Boolean OT;
    private String introduce;

    private Integer stepCount;

    @Column(name = "study_type")
    @Enumerated(EnumType.STRING)
    private StudyType studyType;



    public Study createStudy(Integer step, String level, String detail, Integer numberOfDays, Integer accumulation, Boolean OT, String introduce) {
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
