package SNP.management.domain.entity.student;

import SNP.management.domain.DTO.StudentDTO;
import SNP.management.domain.enumlist.GradeType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Grade {

    @Enumerated(EnumType.STRING)
    private GradeType grade;
    @Column(nullable = true)
    private int gradeLv;

    public Grade(GradeType grade, int gradeLv) {
        this.grade = grade;
        this.gradeLv = gradeLv;

    }

    public Grade setGrade(StudentDTO studentDTO) {
        this.grade= studentDTO.getGrade();
        this.gradeLv = studentDTO.getGradeLv();
        return this;
    }
}
