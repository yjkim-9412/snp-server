package SNP.management.domain.enumlist;

import SNP.management.domain.resolver.BasicTestConst;

public enum BasicTestType {
    STUDY_HABITS(BasicTestConst.STUDY_HABITS),
    READ_BASIC(BasicTestConst.READ_BASIC),
    INTELLIGENCE(BasicTestConst.INTELLIGENCE);

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
