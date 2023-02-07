package SNP.management.domain.DTO;

import SNP.management.domain.entity.student.StudentLog;
import SNP.management.domain.entity.study.Study;
import SNP.management.domain.enumlist.StudyType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class StudyDTO {

    private Long studentId;
    private Long studyId;
    private String studyDetail;
    private Integer currentStudyCount;
    private String StudyTypeString;
    private StudyType studyType;
    private String lastDate;
    private StudentDTO studentInfo;

    private StudyDTO(Long id, Study study, StudentLog studentLog) {
        this.studentId = id;
        this.studyDetail = study.getDetail();
        this.currentStudyCount = studentLog.getStudyCount() + 1;
        this.lastDate = studentLog.getCreateDate().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        this.studyType = studentLog.getStudyType();
        this.StudyTypeString = this.studyType.string();
    }

    public static StudyDTO createStudyDTO(Long id, Study study, StudentLog studentLog) {
        return new StudyDTO(id, study, studentLog);
    }

    public StudyDTO(String studyDetail) {
        this.studyDetail = studyDetail;
    }

    public void setStudentInfo(StudentDTO studentDTO) {
        this.studentInfo = studentDTO;
    }
}
