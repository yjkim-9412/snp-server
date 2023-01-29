package SNP.management.domain.enumlist;

public enum GradeType {
    ELEMENTARY("초등"), MIDDLE("중등"), HIGH("고등");

    private final String string;

    GradeType(String string) {
        this.string = string;
    }

    public String string() {
        return string;
    }
}


