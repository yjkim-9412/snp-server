package SNP.management.web.controller;

import SNP.management.domain.DTO.basictest.BasicTestDTO;
import SNP.management.domain.enumlist.BasicTestType;
import SNP.management.domain.service.basictest.BasicTestSaveHandler;
import SNP.management.web.form.BasicTestForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/basicTest")
public class BasicTestController {

    private final BasicTestSaveHandler basicTestSaveHandler;

    @PostMapping("/save/{studentId}")
    public BasicTestForm saveBasicTest(@PathVariable Long studentId, BasicTestForm basicTestForm) {
        Map<BasicTestType, List<BasicTestDTO>> basicTestDTOMap = BasicTestForm.createBasicTestDTO(basicTestForm);
        basicTestSaveHandler.saveBasicTest(basicTestDTOMap);

        return null;
    }
}
