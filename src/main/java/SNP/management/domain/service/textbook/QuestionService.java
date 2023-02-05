package SNP.management.domain.service.textbook;

import SNP.management.domain.DTO.QuestionDTO;
import SNP.management.domain.entity.student.QuestionLog;
import SNP.management.domain.entity.student.StudentLog;
import SNP.management.domain.entity.textbook.Question;
import SNP.management.domain.entity.textbook.TextBook;
import SNP.management.domain.repository.textbook.QuestionDataJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionDataJpa questionDataJpa;

    public void saveAll(List<QuestionDTO> questionDTOList, TextBook textBook) {
        List<Question> questions = new ArrayList<>();
        for (QuestionDTO questionDTO : questionDTOList) {
            questions.add(Question.createQuestion(questionDTO,textBook));
        }
        questionDataJpa.saveAll(questions);
    }
    
    public List<QuestionDTO> findAllByTextBookId(Long textBookId) {
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        List<Question> questionList = questionDataJpa.findByTextBookId(textBookId);
        for (Question question : questionList) {
            questionDTOList.add(QuestionDTO.createQuestionDTO(question));
        }
        return questionDTOList;
    }





}
