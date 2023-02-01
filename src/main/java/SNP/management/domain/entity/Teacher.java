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


    private void setTeacher(TeacherDTO teacherDTO) {
        this.name = teacherDTO.getName();
        this.phone = teacherDTO.getPhone();
        this.email = teacherDTO.getEmail();
        this.pw = teacherDTO.getPw();
    }
    public void update(TeacherDTO teacherDTO) {
        this.name = teacherDTO.getName();
        this.phone = teacherDTO.getPhone();
        this.email = teacherDTO.getEmail();
        this.pw = teacherDTO.getPw();
    }

    public static Teacher createTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        teacher.setTeacher(teacherDTO);
        return teacher;
    }

    public Teacher login(TeacherDTO teacherDTO) {
        this.email = teacherDTO.getEmail();
        this.pw = teacherDTO.getPw();
        return this;
    }


}
