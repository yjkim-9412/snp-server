package SNP.management.domain.DTO;

import SNP.management.domain.enumlist.DayOfWeek;
import SNP.management.web.form.student.RecordForm;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class RecordDTO {


    private String time;
    private String teacherName;
    private String studentName;
    private String parentPhone;
    private String stepName;



    @QueryProjection
    public RecordDTO(String time, String teacherName, String studentName, String parentPhone, String stepName) {
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

}
