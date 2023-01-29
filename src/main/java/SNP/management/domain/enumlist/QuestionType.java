package SNP.management.domain.enumlist;

public enum QuestionType {
    UNDERSTANDING("이해력"),
    LOGICAL("논리력"),
    CRITICISM("비판력"),
    ANALYTICAL("분석력"),
    CREATIVITY("창의력"),
    REASONING("추리력");



    private final String value;

    QuestionType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static QuestionType getStringType(String value) {
        for (QuestionType questionType : QuestionType.values()) {
            if (questionType.value.equals(value)) {
                return questionType;
            }
        }
        throw new IllegalArgumentException("typeMissMatch: " + value);
    }
}
