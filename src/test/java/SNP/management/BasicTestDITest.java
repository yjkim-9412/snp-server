package SNP.management;

import SNP.management.domain.DTO.basictest.BasicTestDTO;
import SNP.management.domain.DTO.basictest.StudyHabitsDTO;
import SNP.management.domain.enumlist.BasicTestType;
import SNP.management.domain.enumlist.StudyHabitType;

import SNP.management.web.form.BasicTestForm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;


public class BasicTestDITest {

    private final BasicTestForm basicTestForm = new BasicTestForm();
    private final List<StudyHabitsDTO> studyHabitsDTOList = new ArrayList<>();

    @BeforeEach
    void before() {
        studyHabitsDTOList.add(new StudyHabitsDTO(10L, 4,3, BasicTestType.STUDY_HABITS, StudyHabitType.CREATIVITY));
        basicTestForm.setStudyHabitsList(studyHabitsDTOList);
    }

    @Test
    @DisplayName("BasicDTO 캐스팅 테스트")
    void getStudyHabitsDTOList() {
        //given

        //when
        Map<BasicTestType, List<BasicTestDTO>> basicTestDTO = BasicTestForm.createBasicTestDTO(basicTestForm);
        List<BasicTestDTO> basicTestDTOS = basicTestDTO.get(BasicTestType.STUDY_HABITS);
        //then
        for (BasicTestDTO testDTO : basicTestDTOS) {
            StudyHabitsDTO studyHabitsDTO = (StudyHabitsDTO) testDTO;
            assertThat(studyHabitsDTO)
                    .hasFieldOrPropertyWithValue("studentId", 10L)
                    .hasFieldOrPropertyWithValue("questionNum", 4)
                    .hasFieldOrPropertyWithValue("score", 3)
                    .hasFieldOrPropertyWithValue("basicTestType",BasicTestType.STUDY_HABITS)
                    .hasFieldOrPropertyWithValue("studyHabitType",StudyHabitType.CREATIVITY);

        }

    }
    @Test
    @DisplayName("BasicDTO 캐스팅 테스트 예외")
    void getStudyHabitsDTOListFail() {
        //given

        //when
        Map<BasicTestType, List<BasicTestDTO>> basicTestDTO = BasicTestForm.createBasicTestDTO(basicTestForm);
        List<BasicTestDTO> basicTestDTOS = basicTestDTO.get(BasicTestType.STUDY_HABITS);
        //then
        for (BasicTestDTO testDTO : basicTestDTOS) {
            StudyHabitsDTO studyHabitsDTO = (StudyHabitsDTO) testDTO;
            assertThatThrownBy(() -> assertThat(studyHabitsDTO.getStudentId()).isEqualTo(0L));
        }

    }

}
