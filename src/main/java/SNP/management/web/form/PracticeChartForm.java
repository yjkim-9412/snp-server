package SNP.management.web.form;

import SNP.management.domain.DTO.chart.*;
import SNP.management.domain.enumlist.TextBookType;
import lombok.Data;

import java.util.List;

@Data
public class PracticeChartForm {
    private List<DayChartDTO> dayChart;
    private List<StepChartDTO> stepChart;
    private List<QuestionTypeChartDTO> QuestionChart;
    private List<CategoryChartDTO> categoryChart;
    private List<TextBookChartDTO> perusalList;
    private List<TextBookChartDTO> essayList;
    private List<TextBookChartDTO> NieList;
    private List<TextBookChartDTO> basicList;


    public PracticeChartForm(List<DayChartDTO> dayChart, List<StepChartDTO> stepChart, List<QuestionTypeChartDTO> questionChart, List<CategoryChartDTO> categoryChart) {
        this.dayChart = dayChart;
        this.stepChart = stepChart;
        this.QuestionChart = questionChart;
        this.categoryChart = categoryChart;
    }

    public void setTextBookChart(TextBookType textBookType, List<TextBookChartDTO> textBookChartList){
        switch (textBookType) {
            case PERUSAL:
                perusalList = textBookChartList;
                break;
            case ESSAY:
                essayList = textBookChartList;
                break;
            case BASIC:
                basicList = textBookChartList;
                break;
            case NIE:
                NieList = textBookChartList;
                break;
        }
    }
}
