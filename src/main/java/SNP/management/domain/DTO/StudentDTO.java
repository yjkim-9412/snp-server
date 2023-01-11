package SNP.management.domain.DTO;



import SNP.management.Web.form.student.StudentSaveForm;
import SNP.management.Web.form.student.StudentUpdateForm;
import SNP.management.domain.entity.study.StudyType;
import lombok.Data;

@Data
public class StudentDTO {
    private Long id;

    private String name;
    private int age;
    private String birth;
    private String phone;
    private String email;
    private String parentName;
    private String parentPhone;
    private String gender;

    private StudyType studyType;

    private String city;
    private String street;
    private String gradeName;
    private int gradeLv;
    private int speed;
    private int readLv;
    private int intLv;

    private Long teacher_id;

    public StudentDTO() {
    }

    public StudentDTO(String name, int age, String birth, String phone, String email, String parentName, String parentPhone, String gender, int study_count, StudyType studyType, String city, String street, String gradeName, int gradeLv, int speed, int readLv, int intLv, Long teacher_id) {
        this.name = name;
        this.age = age;
        this.birth = birth;
        this.phone = phone;
        this.email = email;
        this.parentName = parentName;
        this.parentPhone = parentPhone;
        this.gender = gender;
        this.studyType = studyType;
        this.city = city;
        this.street = street;
        this.gradeName = gradeName;
        this.gradeLv = gradeLv;
        this.speed = speed;
        this.readLv = readLv;
        this.intLv = intLv;
        this.teacher_id = teacher_id;
    }

    public StudentDTO FormToStudentDTO(StudentSaveForm studentSaveForm) {
        this.name = studentSaveForm.getName();
        this.age = studentSaveForm.getAge();
        this.birth = studentSaveForm.getBirth();
        this.phone = studentSaveForm.getPhone();
        this.email = studentSaveForm.getEmail();
        this.parentName = studentSaveForm.getParentName();
        this.parentPhone = studentSaveForm.getParentPhone();
        this.gender = studentSaveForm.getGender();
        this.studyType = studentSaveForm.getStudyType();
        this.city = studentSaveForm.getCity();
        this.street = studentSaveForm.getStreet();
        this.gradeName = studentSaveForm.getGradeName();
        this.gradeLv = studentSaveForm.getGradeLv();
        this.speed = studentSaveForm.getSpeed();
        this.readLv = studentSaveForm.getReadLv();
        this.intLv = studentSaveForm.getIntLv();
        this.teacher_id = studentSaveForm.getTeacher_id();
        return this;
    }

    public StudentDTO FormToStudentDTO(StudentUpdateForm studentUpdateForm) {
        this.name = studentUpdateForm.getName();
        this.age = studentUpdateForm.getAge();
        this.birth = studentUpdateForm.getBirth();
        this.phone = studentUpdateForm.getPhone();
        this.email = studentUpdateForm.getEmail();
        this.parentName = studentUpdateForm.getParentName();
        this.parentPhone = studentUpdateForm.getParentPhone();
        this.gender = studentUpdateForm.getGender();
        this.studyType = studentUpdateForm.getStudyType();
        this.city = studentUpdateForm.getCity();
        this.street = studentUpdateForm.getStreet();
        this.gradeName = studentUpdateForm.getGradeName();
        this.gradeLv = studentUpdateForm.getGradeLv();
        this.speed = studentUpdateForm.getSpeed();
        this.readLv = studentUpdateForm.getReadLv();
        this.intLv = studentUpdateForm.getIntLv();
        this.teacher_id = studentUpdateForm.getTeacher_id();
        return this;
    }
}
