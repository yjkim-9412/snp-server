package SNP.management.domain.DTO;

import SNP.management.domain.entity.student.StudentLog;
import SNP.management.domain.entity.study.Study;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudyDTO {

    private Long studentId;
    private String studyDetail;
    private Integer studyCount;

    private StudyDTO(Long id, Study study, StudentLog studentLog) {
        this.studentId = id;
        this.studyDetail = study.getDetail();
        this.studyCount = studentLog.getStudyCount() + 1;
    }

    public static StudyDTO createStudyDTO(Long id, Study study, StudentLog studentLog) {
        return new StudyDTO(id, study, studentLog);
    }
}
