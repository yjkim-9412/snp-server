package SNP.management.domain.DTO.basictest;

import SNP.management.domain.enumlist.basictest.BasicTestType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReadBasicDTO extends BasicTestDTO{

    private int speed;
    private int intelligibility;


    public ReadBasicDTO(Long studentId, int questionNum, int score, BasicTestType basicTestType, int speed, int intelligibility) {
        super(studentId, questionNum, score, basicTestType);
        this.speed = speed;
        this.intelligibility = intelligibility;
    }

    
}
