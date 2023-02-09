package SNP.management.web.form.student;

import SNP.management.domain.DTO.LogDTO;
import lombok.Data;

import java.util.Map;

@Data
public class SaveLogForm {

    private LogDTO logForm;
    private Map<Integer, Integer> answerMap;

}
