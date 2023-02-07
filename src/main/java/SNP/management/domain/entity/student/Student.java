package SNP.management.domain.entity.student;


import SNP.management.domain.DTO.StudentDTO;
import SNP.management.domain.entity.BaseEntity;
import SNP.management.domain.entity.Teacher;
import SNP.management.domain.entity.study.Study;
import SNP.management.domain.enumlist.StudyType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "STUDENT")
public class Student extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer age;
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


    private void setStudent(StudentDTO studentDTO) {
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
        this.grade = Grade.createGrade(studentDTO);
        this.skill = Skill.createSkill(studentDTO);
        this.registration = studentDTO.getRegistration();
        this.studyCount = studentDTO.getStudyCount() == null ? 0
                : studentDTO.getStudyCount();
    }

    public static Student createStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setStudent(studentDTO);
        return student;
    }

    public void changeStudent(StudentDTO studentDTO) {
        this.setStudent(studentDTO);
    }

    public void changeStudy(Study study) {
        this.study = study;
        this.studyType = study.getStudyType();
        this.studyCount = 1;
    }

    public boolean hasStudy(){
        return this.getStudy() != null;
    }

    public void changeStudyStatus(StudentLog studentLog) {
        this.studyCount = studentLog.getStudyCount();
        this.studyType = studentLog.getStudyType();
    }

    public void changeStudyType(StudyType studyType) {
        this.studyType = studyType;
    }
}
