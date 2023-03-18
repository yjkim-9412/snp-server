package SNP.management.domain.enumlist;

public enum BasicTestType {
    STUDY_HABITS("studyHabitsServiceImpl"),
    READ_BASIC("readBasicServiceImpl"),
    INTELLIGENCE("intelligenceImpl");

    private final String string;

    BasicTestType(String string) {
        this.string = string;
    }

    public String string() {
        return string;
    }

    public static BasicTestType findByString(String beanName) {
        for (BasicTestType basicTestType : BasicTestType.values()) {
            if (basicTestType.string.equals(beanName)) {
                return basicTestType;
            }
        }
        throw new IllegalArgumentException("해당 " + beanName + "을 찾을 수 없음");
    }

}
