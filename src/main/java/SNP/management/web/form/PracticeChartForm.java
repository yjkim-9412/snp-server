package SNP.management.web.form;

import SNP.management.domain.DTO.chart.DayChartDTO;
import SNP.management.domain.DTO.chart.StepChartDTO;
import lombok.Data;

import java.util.List;

@Data
public class PracticeChartForm {
    List<DayChartDTO> dayChart;
    List<StepChartDTO> stepChart;

    public PracticeChartForm(List<DayChartDTO> dayChartDTOList, List<StepChartDTO> stepChartDTOList) {
        this.dayChart = dayChartDTOList;
        this.stepChart = stepChartDTOList;
    }
}
