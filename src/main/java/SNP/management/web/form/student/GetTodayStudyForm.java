package SNP.management.web.form.student;

import SNP.management.domain.DTO.LogDTO;
import SNP.management.domain.DTO.StudyDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetTodayStudyForm {

    private List<StudyDTO> studyList = new ArrayList<>();
    private StudyDTO todayStudy;
    private List<LogDTO> studentLogList = new ArrayList<>();
}
