package SNP.management.web.form;

import SNP.management.domain.DTO.LogDTO;
import lombok.Data;

import java.util.Map;

@Data
public class StudentLogForm {
    private LogDTO studentLog;
    private Integer questionCount;
    private Map<Integer, Integer> answerMap;


    public static StudentLogForm createForm(LogDTO logDTO) {
        StudentLogForm studentLogForm = new StudentLogForm();

        studentLogForm.setAnswerMap(logDTO.getAnswerMap());
        studentLogForm.setQuestionCount(logDTO.getAnswerMap().size());
        logDTO.setAnswerMap(null);
        studentLogForm.setStudentLog(logDTO);
        return studentLogForm;
    }


}
