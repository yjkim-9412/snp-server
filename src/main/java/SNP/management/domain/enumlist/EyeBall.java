package SNP.management.domain.enumlist;

public enum EyeBall {
    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8),
    I(9),
    J(10);


    private final int count;

    EyeBall(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
