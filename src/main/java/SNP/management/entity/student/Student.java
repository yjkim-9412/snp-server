package SNP.management.entity.student;


import SNP.management.domain.StudentDTO;
import SNP.management.entity.Teacher;
import SNP.management.entity.study.StudyType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "STUDENT")
public class Student {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Enumerated(EnumType.STRING)
    private StudyType studyType;

    @Embedded
    private Address address;
    @Embedded
    private Grade grade;
    @Embedded
    private Skill skill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public Student testStudent(String name, int age, String birth) {
        this.name = name;
        this.age = age;
        this.birth = birth;
        return this;
    }

    public Student testStudentType(String name, int age, String birth, StudyType studyType) {
        this.name = name;
        this.age = age;
        this.birth = birth;
        this.studyType = studyType;
        return this;
    }

    public Student(StudentDTO studentDTO) {
        this.id = studentDTO.getId();
        this.name = studentDTO.getName();
        this.age = studentDTO.getAge();
        this.birth = studentDTO.getBirth();
        this.phone = studentDTO.getPhone();
        this.email = studentDTO.getEmail();
        this.parentName = studentDTO.getParentName();
        this.parentPhone = studentDTO.getParentPhone();
        this.gender = studentDTO.getGender();
        this.study_count = studentDTO.getStudy_count();
        this.studyType = StudyType.valueOf(studentDTO.getStudyType());
        this.address = new Address().setAddress(studentDTO);
        this.grade = new Grade().setGrade(studentDTO);
        this.skill = new Skill().setSkill(studentDTO);
    }

    public void connectTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student changeStudent(StudentDTO studentDTO) {
        this.id = studentDTO.getId();
        this.name = studentDTO.getName();
        this.age = studentDTO.getAge();
        this.birth = studentDTO.getBirth();
        this.phone = studentDTO.getPhone();
        this.email = studentDTO.getEmail();
        this.parentName = studentDTO.getParentName();
        this.parentPhone = studentDTO.getParentPhone();
        this.gender = studentDTO.getGender();
        this.study_count = studentDTO.getStudy_count();
        this.studyType = StudyType.valueOf(studentDTO.getStudyType());
        this.address = new Address().setAddress(studentDTO);
        this.grade = new Grade().setGrade(studentDTO);
        this.skill = new Skill().setSkill(studentDTO);

        return this;
    }

}
