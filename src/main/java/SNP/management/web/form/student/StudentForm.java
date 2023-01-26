package SNP.management.web.form.student;

import SNP.management.domain.DTO.StudentDTO;
import SNP.management.domain.enumlist.StudyType;
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
    private Integer speed;
    private Integer readLv;
    private Integer intLv;
    private String date;


    public StudentForm(StudentDTO studentDTO) {
        this.id = studentDTO.getId();
        this.name = studentDTO.getName();
        this.gender = studentDTO.getGender();
        this.grade = studentDTO.getStudyType();
        this.gradeLv = studentDTO.getEmail();
        this.parentName = studentDTO.getParentName();
        this.parentPhone = studentDTO.getParentPhone();
        this.speed = studentDTO.getSpeed();
        this.readLv = studentDTO.getReadLv();
        this.intLv = studentDTO.getIntLv();
        this.date = studentDTO.getDate();
    }
}
