package SNP.management.domain.service.basictest;

import SNP.management.domain.DTO.basictest.BasicTestDTO;
import SNP.management.domain.enumlist.BasicTestType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class BasicTestSaveHandler {

    private final Map<BasicTestType, BasicTestService> basicTestServiceMap;

    public void saveBasicTest(Map<BasicTestType, List<BasicTestDTO>> basicTestMap) {
        basicTestMap.forEach((basicTestType, basicTestDTOList) -> {
            BasicTestService basicTestService = basicTestServiceMap.get(basicTestType);
            basicTestService.saveStudentAnswer(basicTestDTOList);
        });

    }


}
