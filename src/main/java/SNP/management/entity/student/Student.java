package SNP.management.entity.student;


import SNP.management.entity.Teacher;
import SNP.management.entity.student.Address;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@RequiredArgsConstructor
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
}
