package SNP.management.web.form;

import SNP.management.domain.DTO.StudyDTO;
import lombok.Data;

import java.util.List;

@Data
public class TodayStudyForm {

    private List<String> studyDetailList;
    private StudyDTO dayOfStudy;

    public TodayStudyForm(List<String> studyDetailList, StudyDTO dayOfStudy) {
        this.studyDetailList = studyDetailList;
        this.dayOfStudy = dayOfStudy;
    }
}
