package SNP.management.domain.DTO.chart;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StepChartDTO {


    private Integer processingTime;
    private String studyDetail;

    @QueryProjection
    public StepChartDTO(Integer processingTime, String studyDetail) {
        this.processingTime = processingTime;
        this.studyDetail = studyDetail;
    }
}
