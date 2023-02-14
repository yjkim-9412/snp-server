package SNP.management;

import SNP.management.domain.DTO.chart.DayChartDTO;
import SNP.management.domain.DTO.chart.StepChartDTO;
import SNP.management.domain.enumlist.EyeBall;
import SNP.management.domain.repository.student.StudentLogDataJpa;
import SNP.management.domain.repository.student.StudentLogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
@Transactional

public class ChartTest {
    @Autowired
    EntityManager em;
    @Autowired
    StudentLogDataJpa studentLogDataJpa;
    @Autowired
    StudentLogRepository studentLogRepository;

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
}
