package SNP.management.web.form.teacher;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

import static SNP.management.web.resolver.ValidationConst.*;

@Data
public class TeacherSaveForm {

    @NotBlank(message = NOT_BLANK_NAME)
    private String name;
    @NotBlank(message = NOT_BLANK_NAME)
    private String phone;
    @Email(message = EMAIL_EMAIL)
    private String email;
    @NotBlank(message = NOT_BLANK_PW)
    @Max(value = 20)
    private String pw;
}
