package SNP.management.web.form.student;

import SNP.management.domain.DTO.ScheduleDTO;
import SNP.management.domain.DTO.StudentDTO;
import SNP.management.domain.entity.study.StudyType;
import lombok.Data;

@Data
public class StudentForm {
    private Long id;
    private String name;
    private String gender;
    private StudyType grade;
    private String gradeLv;
    private String parentName;
    private String parentPhone;
    private boolean speed;
    private boolean readLv;
    private boolean intLv;
    private String date;

}
