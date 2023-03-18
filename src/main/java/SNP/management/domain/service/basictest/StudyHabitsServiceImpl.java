package SNP.management.domain.service.basictest;

import SNP.management.domain.DTO.BasicTestDTO;
import SNP.management.domain.entity.basictest.BasicTestAvg;
import SNP.management.domain.entity.basictest.StudyHabits;
import SNP.management.domain.entity.student.Student;
import SNP.management.domain.repository.basictest.StudyHabitsDataJpa;
import SNP.management.domain.repository.student.StudentDataJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Slf4j
public class StudyHabitsServiceImpl implements BasicTestService {

    private final StudyHabitsDataJpa studyHabitsDataJpa;
    private final StudentDataJpa studentDataJpa;


    @Override
    public void saveStudentAnswer(List<BasicTestDTO> basicTestDTOList) {
        if (BasicTestDTO.isSameStudentId(basicTestDTOList)) {
            Student student = studentDataJpa.findById(basicTestDTOList.get(0).getStudentId()).orElseThrow(() -> new IllegalArgumentException(NO_STUDENT));
            if (BasicTestDTO.isSameBasicTestType(basicTestDTOList)) {

            }else {
                throw new IllegalArgumentException(NOT_SAME_TYPE);
            }
        }else {
            throw new IllegalArgumentException(NOT_SAME_STUDENT);
        }
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
