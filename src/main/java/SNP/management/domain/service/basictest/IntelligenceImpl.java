package SNP.management.domain.service.basictest;

import SNP.management.domain.DTO.BasicTestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class IntelligenceImpl implements BasicTestService {
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
