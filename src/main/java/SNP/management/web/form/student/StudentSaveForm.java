package SNP.management.web.form.student;

import SNP.management.domain.entity.study.StudyType;
import SNP.management.domain.enumlist.GradeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static SNP.management.web.resolver.ValidationConst.*;

@Data
@AllArgsConstructor
public class StudentSaveForm {

    @NotBlank(message = NOT_BLANK_NAME)
    private String name;

    private int age;
    private String birth;
    private String phone;

    @Email(message = EMAIL_EMAIL)
    @NotBlank(message = EMAIL_EMAIL)
    private String email;
    @NotBlank(message = NOT_BLANK_NAME)
    private String parentName;
    @NotBlank(message = NOT_BLANK_PHONE)
    private String parentPhone;
    private String gender;
    private StudyType studyType;//수업 코스
    
    @NotNull
    private GradeType grade; //초,중,고
    @Range(min = 1, max = 6)
    private Integer gradeLv;//학년 단계 ex)1,2,3학년
    private Long teacherId;

    private AddressForm address;
    private SkillForm skill;

    public StudentSaveForm(String name, int age, String birth, String phone, String email, String parentName, String parentPhone, String gender, StudyType studyType, GradeType grade, Integer gradeLv) {
        this.name = name;
        this.age = age;
        this.birth = birth;
        this.phone = phone;
        this.email = email;
        this.parentName = parentName;
        this.parentPhone = parentPhone;
        this.gender = gender;
        this.studyType = studyType;
        this.grade = grade;
        this.gradeLv = gradeLv;
    }
}
