package SNP.management.domain.DTO;

import SNP.management.Web.form.teacher.TeacherLoginForm;
import SNP.management.domain.entity.Teacher;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
public class TeacherDTO {
    private Long id;

    private String name;
    private String phone;
    private String email;
    private String pw;

    public TeacherDTO(String name, String phone, String email, String pw) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.pw = pw;
    }
    public TeacherDTO teacherToDTO(Teacher teacher) {
        this.name = teacher.getName();
        this.phone = teacher.getPhone();
        this.email = teacher.getEmail();
        this.pw = teacher.getPw();
        return this;
    }
    public TeacherDTO(Long id,String name, String phone, String email, String pw) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.pw = pw;
    }

    public TeacherDTO login(TeacherLoginForm loginForm) {
        this.email = loginForm.getEmail();
        this.pw = loginForm.getPw();
        return this;
    }


}
