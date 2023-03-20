package SNP.management.domain.entity;

import SNP.management.domain.DTO.TeacherDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "TEACHER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Teacher extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String pw;



    public void update(String name, String phone, String email, String pw) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.pw = pw;
    }

    public static Teacher createTeacher(TeacherDTO teacherDTO) {
        return new Teacher(teacherDTO.getName(), teacherDTO.getPhone(), teacherDTO.getEmail(), teacherDTO.getPw());
    }

    private Teacher(String name, String phone, String email, String pw) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.pw = pw;
    }
}
