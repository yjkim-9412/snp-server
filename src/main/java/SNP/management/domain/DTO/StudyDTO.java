package SNP.management.domain.DTO;

import SNP.management.domain.entity.student.StudentLog;
import SNP.management.domain.entity.study.Study;
import SNP.management.domain.enumlist.StudyType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Data
@NoArgsConstructor
public class StudyDTO {

    private Long studentId;
    private String studyDetail;
    private Integer currentStudyCount;
    private String StudyTypeString;
    private StudyType studyType;
    private String lastDate;
    private StudentDTO studentInfo;

    private StudyDTO(Long id, Study study, StudentLog studentLog) {
        this.studentId = id;
        this.studyDetail = study.getDetail();
        this.currentStudyCount = studentLog.getStudyCount();
        this.lastDate = studentLog.getCreateDate().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        this.studyType = studentLog.getStudyType();
        this.StudyTypeString = this.studyType.string();
    }
    private void setTodayStudyDTO(Long id, Study study, StudentLog studentLog) {
        this.studentId = id;
        this.studyDetail = study.getDetail();
        this.currentStudyCount = studyDayCalculator(studentLog, study);
        this.lastDate = studentLog.getCreateDate().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        this.studyType = studentLog.getStudyType();
        this.StudyTypeString = this.studyType.string();
    }

    private int studyDayCalculator(StudentLog studentLog, Study study) {
        Study logStudy = studentLog.getStudy();
        if (logStudy.getId() == study.getId()) {
            return studentLog.getStudyCount() + 1;
        }
         return 1;
    }

    public static StudyDTO createStudyDTO(Long id, Study study, StudentLog studentLog) {
        StudyDTO studyDTO = new StudyDTO();
        studyDTO.setTodayStudyDTO(id, study, studentLog);
        return studyDTO;
    }

    public StudyDTO(String studyDetail) {
        this.studyDetail = studyDetail;
    }

    public void setStudentInfo(StudentDTO studentDTO) {
        this.studentInfo = studentDTO;
    }

}
