package SNP.management.web.form;

import SNP.management.domain.DTO.basictest.BasicTestDTO;
import SNP.management.domain.DTO.basictest.IntelligenceDTO;
import SNP.management.domain.DTO.basictest.ReadBasicDTO;
import SNP.management.domain.DTO.basictest.StudyHabitsDTO;
import SNP.management.domain.enumlist.BasicTestType;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class BasicTestForm {
    private List<StudyHabitsDTO> studyHabitsList = new ArrayList<>();
    private List<ReadBasicDTO> readBasicList = new ArrayList<>();
    private List<IntelligenceDTO> intelligenceList = new ArrayList<>();

    public static Map<BasicTestType, List<BasicTestDTO>>createBasicTestDTO(BasicTestForm basicTestForm) {
        Map<BasicTestType, List<BasicTestDTO>> basicTestDTOMap = new HashMap<>();
        basicTestDTOMap.put(BasicTestType.STUDY_HABITS, new ArrayList<>(basicTestForm.getStudyHabitsList()));
        basicTestDTOMap.put(BasicTestType.READ_BASIC, new ArrayList<>(basicTestForm.getReadBasicList()));
        basicTestDTOMap.put(BasicTestType.INTELLIGENCE, new ArrayList<>(basicTestForm.getIntelligenceList()));

        return basicTestDTOMap;
    }
}
