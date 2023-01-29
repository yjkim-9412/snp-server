package SNP.management.domain.enumlist;

public enum TextBookType {
    PERUSAL("정독","R"),
    ESSAY("논술", "D"),
    NIE("NIE", "N"),
    BASIC("기초평가", "B");

    private final String string;
    private final String code;

    TextBookType(String string, String code) {
        this.code = code;
        this.string = string;
    }
    public String string() {
        return string;
    }
    public String code() {
        return code;
    }


}
