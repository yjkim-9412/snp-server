package SNP.management.web.exception;

public class LoginException extends NullPointerException{
    public LoginException() {
        super();
    }

    public LoginException(String s) {
        super(s);
    }
}