package SNP.management.entity;

import SNP.management.domain.ScheduleDTO;
import SNP.management.domain.TeacherDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "TEACHER")
@NoArgsConstructor
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
