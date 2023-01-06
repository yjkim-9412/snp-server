package SNP.management.calendar;

public enum DaysOfWeek {
    SUNDAY(0),
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6);

    private final int day;
    DaysOfWeek(int day) {
        this.day = day;
    }
    public int getDayInt() {
        return day;
    }
}
