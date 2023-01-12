package SNP.management.web.form.student;

import SNP.management.domain.DTO.ScheduleDTO;
import SNP.management.domain.DTO.StudentDTO;
import lombok.Data;

@Data
public class StudentForm {

    private StudentDTO studentInfo;
    private ScheduleDTO studentSchedule;

    public StudentForm(StudentDTO studentDTO, ScheduleDTO scheduleDTO) {
        this.studentInfo = studentDTO;
        this.studentSchedule = scheduleDTO;
    }
}
