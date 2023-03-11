package SNP.management.domain.service.textbook;

import SNP.management.domain.DTO.QuestionDTO;
import SNP.management.domain.DTO.TextBookDTO;
import SNP.management.domain.entity.Category;
import SNP.management.domain.entity.textbook.Question;
import SNP.management.domain.entity.textbook.TextBook;
import SNP.management.domain.repository.CategoryDataJpa;
import SNP.management.domain.repository.textbook.QuestionDataJpa;
import SNP.management.domain.repository.textbook.TextBookDataJpa;
import SNP.management.domain.repository.textbook.TextBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional @Slf4j
public class TextBookService {

    private final TextBookDataJpa textBookDataJpa;
    private final TextBookRepository textBookRepository;
    private final CategoryDataJpa categoryDataJpa;
    private final QuestionService questionService;
    private final QuestionDataJpa questionDataJpa;


    public TextBookDTO saveWithQuestion(TextBookDTO textBookDTO, List<QuestionDTO> questionDTOList) {
        if (textBookDTO.hasCode()) {
            log.info("code = {}", textBookDTO.getCode());
            update(textBookDTO, questionDTOList);
            return textBookDTO;
        }
        TextBook textBook = save(textBookDTO);
        textBook.createCode();
        questionService.saveAll(questionDTOList, textBook); // 교재에 해당하는 문제 저장
        textBook.createQuestionCount(questionDTOList.size());
        textBookDTO.changeTextBookDTO(textBook);
        return textBookDTO;
    }

    /**
     * 카테고리 조회 및 추가후 TextBook 저장
     *
     * @param textBookDTO 카테고리명 textBookDTO.getCategoryName()
     * @return TextBook
     */
    public TextBook save(TextBookDTO textBookDTO) {
        Category category = categoryDataJpa.findByName(textBookDTO.getCategoryName()).orElseThrow(IllegalArgumentException::new);

        TextBook textBook = TextBook.createTextBook(textBookDTO, category);

        return textBookDataJpa.save(textBook);
    }

    public void update(TextBookDTO textBookDTO, List<QuestionDTO> questionDTOList) {
        TextBook textBookFindById = textBookDataJpa.findById(textBookDTO.getId())
                .orElseThrow(IllegalArgumentException::new);

        textBookFindById.ChangeTextBook(textBookDTO,
                categoryDataJpa.findById(textBookDTO.getCategoryId()).orElseThrow(IllegalArgumentException::new));

        updateTextbookQuestion(questionDTOList, textBookFindById);
    }

    public void deleteTextBookAndQuestion(String code) {
        TextBook textBook = textBookDataJpa.findByCode(code).orElseThrow(IllegalArgumentException::new);
        questionDataJpa.deleteByTextBookId(textBook.getId());
        textBookDataJpa.delete(textBook);
    }

    private void updateTextbookQuestion(List<QuestionDTO> questionDTOList, TextBook textBookFindById) {
        List<Question> questionList = questionDataJpa.findByTextBookIdOrderByNumberAsc(textBookFindById.getId());

        if (questionList.size() > 0) {
            List<Question> questionDeleteList = QuestionUpdateReturnDeleteList(questionDTOList, textBookFindById, questionList);
            deleteTextBookQuestion(questionDeleteList);
        }
        saveNewTextBookQuestion(questionDTOList, textBookFindById);
    }

    private List<Question> QuestionUpdateReturnDeleteList(List<QuestionDTO> questionDTOList, TextBook textBookFindById, List<Question> questionList) {
        List<Question> questionDeleteList = new ArrayList<>();
        for (Question question : questionList) {
            boolean delete = updateAndIsNotMatchQuestion(questionDTOList, textBookFindById, questionDeleteList, question);
            if (delete){
                questionDeleteList.add(question);
            }
        }
        return questionDeleteList;
    }

    private boolean updateAndIsNotMatchQuestion(List<QuestionDTO> questionDTOList, TextBook textBookFindById, List<Question> questionDeleteList, Question question) {
        boolean delete = true;
        for (int i = 0; i < questionDTOList.size(); i++) {
            Integer numberDTO = questionDTOList.get(i).getNumber();
            if (numberDTO != null && numberDTO == question.getNumber()) {
                question.changeQuestion(questionDTOList.get(i), textBookFindById);
                delete =false;
                questionDTOList.remove(i);
                break;
            }
        }
        return delete;
    }

    private void deleteTextBookQuestion(List<Question> questionDeleteList) {
        if (questionDeleteList.size() > 0) {
            questionDataJpa.deleteAll(questionDeleteList);
        }
    }

    private void saveNewTextBookQuestion(List<QuestionDTO> questionDTOList, TextBook textBookFindById) {
        List<Question> newQuestionList = new ArrayList<>();
        if (questionDTOList.size() > 0) {
            for (QuestionDTO questionDTO : questionDTOList) {
                newQuestionList.add(Question.createQuestion(questionDTO, textBookFindById));
            }
            questionDataJpa.saveAll(newQuestionList);
        }
    }

    public TextBookDTO findByCodeDTO(String code) {
        TextBook textBook = textBookDataJpa.findByCode(code).orElseThrow(IllegalArgumentException::new);
        return TextBookDTO.createDTO(textBook);
    }

    public List<TextBookDTO> findByName(String name) {
        List<TextBookDTO> textBookDTOList = new ArrayList<>();
        List<TextBook> textBookList = textBookDataJpa.findAllByNameLike(name);
        for (TextBook textBook : textBookList) {
            textBookDTOList.add(TextBookDTO.createDTO(textBook));
        }
        return textBookDTOList;
    }

}
