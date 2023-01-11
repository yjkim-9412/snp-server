package SNP.management.Web.form.student;

import SNP.management.domain.entity.study.StudyType;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class StudentSaveForm {

    @NotBlank(message = "{NotBlank.name}")
    private String name;

    private int age;
    private String birth;
    private String phone;

    @Email(message = "{Email}")
    @NotBlank
    private String email;
    @NotBlank(message = "{NotBlank.parent}")
    private String parentName;
    @NotBlank(message = "{NotBlank.phone}")
    private String parentPhone;
    private String gender;
    private int study_count;
    private StudyType studyType;
    private String city;
    private String street;
    private String gradeName;
    private int gradeLv;
    private int speed;
    private int readLv;
    private int intLv;
    private Long teacher_id;
}
