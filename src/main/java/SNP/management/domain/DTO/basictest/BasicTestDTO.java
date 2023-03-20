package SNP.management.domain.DTO.basictest;

import SNP.management.domain.enumlist.BasicTestType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public abstract class BasicTestDTO {

    private Long studentId;
    private int questionNum;
    private int score;
    private BasicTestType basicTestType;


    public BasicTestDTO(Long studentId, int questionNum, int score, BasicTestType basicTestType) {
        this.studentId = studentId;
        this.questionNum = questionNum;
        this.score = score;
        this.basicTestType = basicTestType;
    }

    public static boolean isSameStudentId(List<BasicTestDTO> basicTestDTOList) {
        return basicTestDTOList.get(0).getStudentId() != null && basicTestDTOList.stream()
                .map(BasicTestDTO::getStudentId)
                .distinct()
                .count() == 1;
    }

    public static boolean isSameBasicTestType(List<BasicTestDTO> basicTestDTOList){
        return basicTestDTOList.get(0).getBasicTestType() != null && basicTestDTOList.stream()
                .map(BasicTestDTO::getBasicTestType)
                .distinct()
                .count() == 1;
    }
}
