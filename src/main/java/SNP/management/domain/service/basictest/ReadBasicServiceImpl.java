package SNP.management.domain.service.basictest;

import SNP.management.domain.DTO.basictest.BasicTestDTO;
import SNP.management.domain.repository.basictest.StudyHabitsDataJpa;
import SNP.management.domain.repository.student.StudentDataJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class ReadBasicServiceImpl implements BasicTestService{

    private final StudyHabitsDataJpa studyHabitsDataJpa;
    private final StudentDataJpa studentDataJpa;
    @Override
    public void saveStudentAnswer(List<BasicTestDTO> basicTestDTO) {

    }

    @Override
    public List<BasicTestDTO> getStudentTestInfo(Long studentId) {
        return null;
    }

    @Override
    public void updateStudentAnswer(BasicTestDTO basicTestDTO) {

    }

    @Override
    public void deleteAllStudentAnswers(List<BasicTestDTO> basicTestDTO) {

    }

    @Override
    public void deleteStudentAnswer(BasicTestDTO basicTestDTO) {

    }
}
