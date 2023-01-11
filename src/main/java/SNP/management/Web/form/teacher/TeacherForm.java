package SNP.management.Web.form.teacher;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static SNP.management.Web.resolver.ValidationConst.*;

@Data
public class TeacherForm {
    @NotNull(message = NOT_NULL_ID)
    private Long id;

    @NotBlank(message = NOT_BLANK_NAME)
    private String name;
    private String phone;
    @Email(message = EMAIL_EMAIL)
    private String email;
    @NotBlank(message = NOT_BLANK_PW)
    @Max(value = 20)
    private String pw;

}
