package SNP.management.domain.exceptionlist;

public class LoginException extends NullPointerException{
    public LoginException() {
        super();
    }

    public LoginException(String s) {
        super(s);
    }
}
