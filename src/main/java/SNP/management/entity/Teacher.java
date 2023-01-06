package SNP.management.entity;

import SNP.management.domain.TeacherDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@RequiredArgsConstructor
@Table(name = "TEACHER")
public class Teacher {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
    private String email;
    private String pw;


    public Teacher save(TeacherDTO teacherDTO) {
        this.name = teacherDTO.getName();
        this.phone = teacherDTO.getPhone();
        this.email = teacherDTO.getEmail();
        this.pw = teacherDTO.getPw();;
        return this;
    }
}
