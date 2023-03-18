package SNP.management.domain.DTO;

import SNP.management.domain.enumlist.BasicTestType;
import lombok.Data;

import java.util.List;

@Data
public class BasicTestDTO {

    private Long studentId;
    private int questionNum;
    private int score;
    private BasicTestType basicTestType;


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
