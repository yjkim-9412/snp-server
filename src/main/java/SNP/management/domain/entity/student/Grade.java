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
    @Column(name ="grade")
    private GradeType gradeType;
    @Column(nullable = true)
    private Integer gradeLv;


    private Grade (StudentDTO studentDTO) {
        this.gradeType = studentDTO.getGrade();
        this.gradeLv = studentDTO.getGradeLv();
    }

    public static Grade createGrade(StudentDTO studentDTO) {
        return new Grade(studentDTO);
    }
}
