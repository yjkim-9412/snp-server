package SNP.management.domain.DTO;

import SNP.management.domain.entity.student.Grade;
import SNP.management.domain.entity.student.Schedule;
import SNP.management.domain.entity.student.Student;
import SNP.management.domain.enumlist.GradeType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TodayScheduleDTO {

    private Long id;
    private String time;
    private String studentName;
    private String parentPhone;
    private String stepName;
    private String grade;
    private Integer gradeLv;



    @QueryProjection
    public TodayScheduleDTO(Long id, String time, String studentName, String parentPhone, String stepName, Grade grade) {
        this.id = id;
        this.time = time;
        this.studentName = studentName;
        this.parentPhone = parentPhone;
        this.stepName = stepName;
        this.grade = grade.getGradeType().string();
        this.gradeLv = grade.getGradeLv();
    }



    public TodayScheduleDTO(Schedule schedule) {
        Student student = schedule.getStudent();
        Grade StudentGrade = student.getGrade();
        this.id = student.getId();
        this.time = schedule.getTime();
        this.studentName = student.getName();
        this.parentPhone = student.getParentPhone();
        this.stepName = student.getStudy().getDetail();
        this.grade = StudentGrade.getGradeType().string();
    }


}
