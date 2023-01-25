package SNP.management.domain.DTO;

import SNP.management.domain.entity.student.Schedule;
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



    @QueryProjection
    public TodayScheduleDTO(Long id, String time, String studentName, String parentPhone, String stepName) {
        this.id = id;
        this.time = time;
        this.studentName = studentName;
        this.parentPhone = parentPhone;
        this.stepName = stepName;
    }



    public TodayScheduleDTO(Schedule schedule) {
        this.id = schedule.getStudent().getId();
        this.time = schedule.getTime();
        this.studentName = schedule.getStudent().getName();
        this.parentPhone = schedule.getStudent().getParentPhone();
        this.stepName = schedule.getStudent().getStudy().getDetail();
    }


}
