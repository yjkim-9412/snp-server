package SNP.management.domain.service.basictest;

import SNP.management.domain.DTO.basictest.BasicTestDTO;

import java.util.List;

public interface BasicTestService {

    final static String NO_STUDENT = "해당 학생이 없음";
    final static String NOT_SAME_TYPE = "잘못된 형식의 BasicTestType";
    final static String NOT_SAME_STUDENT= "학생이 동일하지 않음";


    void saveStudentAnswer(List<BasicTestDTO> basicTestDTO);

    List<BasicTestDTO> getStudentTestInfo(Long studentId);

    void updateStudentAnswer(BasicTestDTO basicTestDTO);

    void deleteAllStudentAnswers(List<BasicTestDTO> basicTestDTO);

    void deleteStudentAnswer(BasicTestDTO basicTestDTO);
}
