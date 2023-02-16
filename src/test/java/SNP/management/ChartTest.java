package SNP.management;

import SNP.management.domain.DTO.chart.*;
import SNP.management.domain.enumlist.EyeBall;
import SNP.management.domain.enumlist.TextBookType;
import SNP.management.domain.repository.student.QuestionLogRepository;
import SNP.management.domain.repository.student.StudentLogDataJpa;
import SNP.management.domain.repository.student.StudentLogRepository;
import SNP.management.domain.service.student.StudentLogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Transactional

public class ChartTest {
    @Autowired
    EntityManager em;
    @Autowired
    StudentLogDataJpa studentLogDataJpa;
    @Autowired
    StudentLogRepository studentLogRepository;
    @Autowired
    QuestionLogRepository questionLogRepository;
    @Autowired
    StudentLogService studentLogService;

    private static Long STUDENT_ID;


    @Test
    void getStudyDetailChart() {
        //given
        STUDENT_ID = 68L;

        //when
        List<StepChartDTO> studyDetailList = studentLogRepository.findProcessingTimeByStudentIdGroupByDetail(STUDENT_ID);
        System.out.println("EyeBall.values().length = " + EyeBall.values().length);

        //then
        for (StepChartDTO stepChartDTO : studyDetailList) {
            System.out.println("stepChartDTO.getStudyDetail() = " + stepChartDTO.getStudyDetail());
            System.out.println("stepChartDTO.getProcessingTime() = " + stepChartDTO.getProcessingTime());

        }

    }
    @Test
    void getDayChartDTO() {
        //given
        STUDENT_ID = 68L;
        //when
        List<DayChartDTO> dayChartList = studentLogRepository.findDayChartByStudentId(STUDENT_ID);
        //then
        for (DayChartDTO dayChartDTO : dayChartList) {
            System.out.println("dayChartDTO.toString() = " + dayChartDTO.toString());
        }
        
    }

    @Test
    void getQuestionTypeChartDTO() {
        //given
        STUDENT_ID = 68L;

        //when
        List<QuestionTypeChartDTO> questionTypeChartList = questionLogRepository.findQuestionTypeAvgByStudentIdGroupByQuestionType(STUDENT_ID);
        //then
        for (QuestionTypeChartDTO questionTypeChartDTO : questionTypeChartList) {
            System.out.println("questionTypeChartDTO.toString() = " + questionTypeChartDTO.toString());
        }
    }

    @Test
    void getCategoryChartDTO() {
        //given
        STUDENT_ID = 68L;
        //when
        List<CategoryChartDTO> categoryTypeChartList = questionLogRepository.findCategoryQuestionAvgByStudentIdGroupByCategoryName(STUDENT_ID);
        //then
        for (CategoryChartDTO categoryChartDTO : categoryTypeChartList) {
            System.out.println("categoryChartDTO.toString() = " + categoryChartDTO.toString());
        }
    }
    
    @Test
    void getTextBookChart() {
        //given
        STUDENT_ID = 68L;

        //when
        Map<TextBookType, List<TextBookChartDTO>> textBookChart = studentLogService.getTextBookChart(STUDENT_ID);

        //then
        for (Map.Entry<TextBookType, List<TextBookChartDTO>> et : textBookChart.entrySet()) {
            System.out.println("et.getKey() = " + et.getKey());
            for (TextBookChartDTO textBookChartDTO : et.getValue()) {
                System.out.println("textBookChartDTO.toString() = " + textBookChartDTO.toString());
            }
        }
        
    }
}
