package SNP.management.domain.entity.study;



public enum StudyType {
    A_CLASS("A"),
    B_CLASS("B"),
    C_CLASS("C"),
    D_CLASS("D"),
    A_CLASS_H("AH"),
    B_CLASS_H("BH");

    private final String type;

    StudyType(String type) {
        this.type = type;
    }

    public String type() {
        return type;
    }






}
