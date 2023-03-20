package SNP.management.domain.DTO.basictest;

import SNP.management.domain.enumlist.basictest.BasicTestType;
import SNP.management.domain.enumlist.basictest.IntelligenceType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class IntelligenceDTO extends BasicTestDTO{

    private IntelligenceType intelligenceType;

    public IntelligenceDTO(Long studentId, int questionNum, int score, BasicTestType basicTestType, IntelligenceType intelligenceType) {
        super(studentId, questionNum, score, basicTestType);
        this.intelligenceType = intelligenceType;
    }
}
