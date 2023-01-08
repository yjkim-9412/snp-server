package SNP.management.Web;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@ToString
public class ClassTypeDTO {

    private int step;
    private String level;
    private String detail;
    private int numberOfDays;
    private int accumulation;
    private boolean OT;
    private String studyType;
    private String introduce;

    @QueryProjection
    public ClassTypeDTO(int step, String level, String detail, int numberOfDays, int accumulation, boolean OT, String studyType) {
        this.step = step;
        this.level = level;
        this.detail = detail;
        this.numberOfDays = numberOfDays;
        this.accumulation = accumulation;
        this.OT = OT;
        this.studyType = studyType;
    }
}
