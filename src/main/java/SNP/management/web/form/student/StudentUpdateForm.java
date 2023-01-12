package SNP.management.web.form.student;

import SNP.management.domain.entity.study.StudyType;
import SNP.management.domain.enumlist.GradeType;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class StudentUpdateForm {

    @NotNull(message = "{NotNull.id}")
    private Long id;
    @NotBlank(message = "{NotNull.name}")
    private String name;

    private int age;
    private String birth;
    private String phone;

    @NotBlank
    @Email
    private String email;
    @NotBlank(message = "부모님 이름을 입력 해주세요")
    private String parentName;

    @NotBlank(message = "{NotBlank.phone}")
    private String parentPhone;
    @NotBlank(message = "{NotBlank.gender}")
    private String gender;
    private StudyType studyType;
    private String city;
    private String street;
    private GradeType grade;
    private int gradeLv;
    private int speed;
    private int readLv;
    private int intLv;
    private Long teacher_id;
}
