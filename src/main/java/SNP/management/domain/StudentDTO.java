package SNP.management.domain;



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
    private int study_count;

    private String studyType;

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

    public StudentDTO(String name, int age, String birth, String phone, String email, String parentName, String parentPhone, String gender, int study_count, String studyType, String city, String street, String gradeName, int gradeLv, int speed, int readLv, int intLv, Long teacher_id) {
        this.name = name;
        this.age = age;
        this.birth = birth;
        this.phone = phone;
        this.email = email;
        this.parentName = parentName;
        this.parentPhone = parentPhone;
        this.gender = gender;
        this.study_count = study_count;
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
}
