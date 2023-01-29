package SNP.management.domain.enumlist;



public enum StudyType {
    A_CLASS("A"),
    B_CLASS("B"),
    C_CLASS("C"),
    D_CLASS("D"),
    A_CLASS_H("수료A"),
    B_CLASS_H("수료B");

    private final String string;

    StudyType(String string) {
        this.string = string;
    }

    public String string() {
        return string;
    }



}
