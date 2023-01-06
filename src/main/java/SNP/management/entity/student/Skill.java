package SNP.management.entity.student;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter @RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class Skill {

    @Column
    private int speed;
    @Column
    private int readLv;
    @Column
    private int intLv;

    public Skill(int speed, int readLv, int intLv) {
        this.speed = speed;
        this.readLv = readLv;
        this.intLv = intLv;
    }
}
