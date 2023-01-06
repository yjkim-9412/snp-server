package SNP.management.domain;

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
    public TeacherDTO(Long id,String name, String phone, String email, String pw) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.pw = pw;
    }
}
