package SNP.management.Web.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResultForm {

    private String code;
    private String message;
}
