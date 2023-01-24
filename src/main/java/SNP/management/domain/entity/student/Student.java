package SNP.management.domain.entity.student;


import SNP.management.domain.DTO.StudentDTO;
import SNP.management.domain.entity.Teacher;
import SNP.management.domain.entity.study.Study;
import SNP.management.domain.entity.study.StudyType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Enumerated(EnumType.STRING)
    private StudyType studyType;

    private String address;
    @Embedded
    private Grade grade;
    @Embedded
    private Skill skill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;



    @Column(name = "study_count")
    private int studyCount;
    @Column(insertable = false)
    private String date;

    private Boolean registration;

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
        this.studyType = studentDTO.getStudyType();
        this.address = studentDTO.getAddress();
        this.grade = new Grade().setGrade(studentDTO);
        this.skill = new Skill().setSkill(studentDTO);
        this.registration = studentDTO.getRegistration();
        this.date = studentDTO.getDate();
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
        this.studyType = studentDTO.getStudyType();
        this.address = studentDTO.getAddress();
        this.grade = new Grade().setGrade(studentDTO);
        this.skill = new Skill().setSkill(studentDTO);
        this.date = studentDTO.getDate();

        return this;
    }

}
