package SNP.management.entity.student;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.Valid;

@Embeddable
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Grade {

    private String gradeName;
    @Column(nullable = true)
    private int gradeLv;

    public Grade(String gradeName, int gradeLv) {
        this.gradeName = gradeName;
        this.gradeLv = gradeLv;
    }
}
