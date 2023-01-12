package SNP.management.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAPIController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResultForm ErrorIllegalArgument(IllegalArgumentException e) {
        log.error("[exceptionHandler]", e);
        return new ErrorResultForm("BAD", e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResultForm ErrorException(Exception e) {
        log.error("[exceptionHandler]", e);
        return new ErrorResultForm("EX", "서버 오류");
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResultForm ErrorNotReadableException(HttpMessageNotReadableException e) {
        log.error("[exceptionHandler]", e);
        return new ErrorResultForm("TYPE_MISMATCH", e.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler
    public ErrorResultForm ErrorLoginException(LoginException e) {
        log.error("loginFail", e);
        return new ErrorResultForm("LOGIN_FAIL", "아이디 또는 비밀번호가 틀렸습니다.");
    }
}
