package SNP.management.domain.exceptionlist;

public class ScheduleException extends IllegalArgumentException{
    public static String NONE_SCHEDULE = "student has not schedule";

    public ScheduleException() {
        super();
    }

    public ScheduleException(String s) {
        super(s);
    }

    public ScheduleException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScheduleException(Throwable cause) {
        super(cause);
    }
}
