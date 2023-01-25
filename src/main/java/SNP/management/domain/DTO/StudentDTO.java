package SNP.management.domain.DTO;



import SNP.management.domain.entity.student.Student;
import SNP.management.domain.enumlist.GradeType;
import SNP.management.web.form.student.StudentSaveForm;
import SNP.management.web.form.student.StudentUpdateForm;
import SNP.management.domain.entity.study.StudyType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class StudentDTO {
    private Long id;

    private String name;
    private Integer age;
    private String birth;
    private String phone;
    private String email;
    private String parentName;
    private String parentPhone;
    private String gender;

    private StudyType studyType;

    private String city;
    private String street;
    private GradeType grade;
    private Integer gradeLv;
    private Integer speed;
    private Integer readLv;
    private Integer intLv;

    private String address;

    private Boolean registration;
    private String date;

    public StudentDTO() {
    }
    @QueryProjection
    public StudentDTO(String name, int age, String birth, String phone, String email, String parentName,
                      String parentPhone, String gender, StudyType studyType, String address,
                      GradeType grade, int gradeLv, int speed, int readLv, int intLv, Long teacherId, Boolean registration) {
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
        this.speed = speed;
        this.readLv = readLv;
        this.intLv = intLv;
        this.registration = registration;
        this.address = address;
    }

    public StudentDTO(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.age = student.getAge();
        this.birth = student.getBirth();
        this.phone = student.getPhone();
        this.email = student.getEmail();
        this.parentName = student.getParentName();
        this.parentPhone = student.getParentPhone();
        this.gender = student.getGender();
        this.studyType = student.getStudyType();
        this.grade = student.getGrade().getGrade();
        this.gradeLv = student.getGrade().getGradeLv();
        this.speed = student.getSkill().getSpeed();
        this.readLv = student.getSkill().getReadLv();
        this.intLv = student.getSkill().getIntLv();
        this.date = student.getDate();
    }

    public StudentDTO FormToSaveDTO(StudentSaveForm studentSaveForm) {
        this.name = studentSaveForm.getName();
        this.age = studentSaveForm.getAge();
        this.birth = studentSaveForm.getBirth();
        this.phone = studentSaveForm.getPhone();
        this.email = studentSaveForm.getEmail();
        this.parentName = studentSaveForm.getParentName();
        this.parentPhone = studentSaveForm.getParentPhone();
        this.gender = studentSaveForm.getGender();
        this.studyType = studentSaveForm.getStudyType();
        this.address = studentSaveForm.getAddress();
        this.grade = studentSaveForm.getGrade();
        this.gradeLv = studentSaveForm.getGradeLv();
        this.speed = studentSaveForm.getSpeed();
        this.readLv = studentSaveForm.getReadLv();
        this.intLv = studentSaveForm.getIntLv();
        this.registration = studentSaveForm.getRegistration();
        return this;
    }

    public StudentDTO FormToUpdateDTO(StudentUpdateForm studentUpdateForm) {
        this.name = studentUpdateForm.getName();
        this.age = studentUpdateForm.getAge();
        this.birth = studentUpdateForm.getBirth();
        this.phone = studentUpdateForm.getPhone();
        this.email = studentUpdateForm.getEmail();
        this.parentName = studentUpdateForm.getParentName();
        this.parentPhone = studentUpdateForm.getParentPhone();
        this.gender = studentUpdateForm.getGender();
        this.studyType = studentUpdateForm.getStudyType();
        this.address = studentUpdateForm.getAddress();
        this.grade= studentUpdateForm.getGrade();
        this.gradeLv = studentUpdateForm.getGradeLv();
        this.speed = studentUpdateForm.getSpeed();
        this.readLv = studentUpdateForm.getReadLv();
        this.intLv = studentUpdateForm.getIntLv();
        this.registration = studentUpdateForm.getRegistration();
        return this;
    }

}
