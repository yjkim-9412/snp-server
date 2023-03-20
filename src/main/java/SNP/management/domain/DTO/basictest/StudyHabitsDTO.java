package SNP.management.domain.DTO.basictest;

import SNP.management.domain.enumlist.BasicTestType;
import SNP.management.domain.enumlist.StudyHabitType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StudyHabitsDTO extends BasicTestDTO {

    private StudyHabitType studyHabitType;


    public StudyHabitsDTO(Long studentId, int questionNum, int score, BasicTestType basicTestType, StudyHabitType studyHabitType) {
        super(studentId,questionNum,score,basicTestType);
        this.studyHabitType = studyHabitType;
    }
}
