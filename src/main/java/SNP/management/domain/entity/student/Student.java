package SNP.management.domain.entity.student;


import SNP.management.domain.DTO.StudentDTO;
import SNP.management.domain.entity.BaseEntity;
import SNP.management.domain.entity.Teacher;
import SNP.management.domain.entity.study.Study;
import SNP.management.domain.enumlist.GradeType;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "study_type")
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



    private Student(String name, Integer age, String birth, String phone, String email, String parentName, String parentPhone,
                    String gender, StudyType studyType, String address, GradeType gradeType, Integer gradeLv, int speed, int readLv, int intLv,
                    boolean registration, Integer studyCount) {
        this.name = name;
        this.age = age;
        this.birth = birth;
        this.phone = phone;
        this.email = email;
        this.parentName = parentName;
        this.parentPhone = parentPhone;
        this.gender = gender;
        this.studyType = studyType;
        this.address = address;
        this.grade = Grade.createGrade(gradeType, gradeLv);
        this.skill = Skill.createSkill(speed, readLv, intLv);
        this.registration = registration;
        this.studyCount = studyCount == null ? 0
                : studyCount;
    }

    public static Student createStudent(StudentDTO studentDTO) {
        return new Student(studentDTO.getName(), studentDTO.getAge(), studentDTO.getBirth(), studentDTO.getPhone(), studentDTO.getEmail(),
                studentDTO.getParentName(), studentDTO.getParentPhone(), studentDTO.getGender(), studentDTO.getStudyType(), studentDTO.getAddress(),
                studentDTO.getGrade(), studentDTO.getGradeLv(), studentDTO.getSpeed(), studentDTO.getReadLv(), studentDTO.getIntLv(),
                studentDTO.isRegistration(), studentDTO.getStudyCount());

    }

    public void changeStudent(String name, Integer age, String birth, String phone, String email, String parentName, String parentPhone,
                              String gender, StudyType studyType, String address, GradeType gradeType, Integer gradeLv, int speed, int readLv, int intLv,
                              boolean registration, Integer studyCount) {
        this.name = name;
        this.age = age;
        this.birth = birth;
        this.phone = phone;
        this.email = email;
        this.parentName = parentName;
        this.parentPhone = parentPhone;
        this.gender = gender;
        this.studyType = studyType;
        this.address = address;
        this.grade = Grade.createGrade(gradeType, gradeLv);
        this.skill = Skill.createSkill(speed, readLv, intLv);
        this.registration = registration;
        this.studyCount = studyCount == null ? 0
                : studyCount;
    }

    public void changeStudy(Study study) {
        this.study = study;
        this.studyType = study.getStudyType();
        this.studyCount = 1;
    }

    public boolean hasStudy() {
        return this.getStudy() != null;
    }

    public void changeStudyStatus(StudentLog studentLog) {
        this.studyCount = studentLog.getStudyCount();
        this.studyType = studentLog.getStudyType();
        this.study = studentLog.getStudy();
    }

    public void changeStudyType(StudyType studyType) {
        this.studyType = studyType;
    }
}
