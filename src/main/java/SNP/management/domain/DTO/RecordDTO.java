package SNP.management.domain.DTO;

import SNP.management.domain.entity.student.Classes;
import SNP.management.domain.enumlist.DayOfWeek;
import SNP.management.web.form.student.RecordForm;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class RecordDTO {

    private Long id;
    private String time;
    private String teacherName;
    private String studentName;
    private String parentPhone;
    private String stepName;



    @QueryProjection
    public RecordDTO(Long id, String time, String teacherName, String studentName, String parentPhone, String stepName) {
        this.id = id;
        this.time = time;
        this.teacherName = teacherName;
        this.studentName = studentName;
        this.parentPhone = parentPhone;
        this.stepName = stepName;
    }

    public RecordDTO FormToDTO(RecordForm recordForm) {
        this.time = recordForm.getTime();
        this.teacherName = recordForm.getTeacherName();
        this.studentName = recordForm.getStudentName();
        this.parentPhone = recordForm.getParentPhone();
        this.stepName = recordForm.getStepName();
        return this;
    }

    public RecordDTO (Classes classes) {
        this.id = classes.getStudent().getId();
        this.time = classes.getTime();
        this.teacherName = classes.getStudent().getTeacher().getName();
        this.studentName = classes.getStudent().getName();
        this.parentPhone = classes.getStudent().getParentPhone();
        this.stepName = classes.getStudent().getStudy().getDetail();
    }


}
