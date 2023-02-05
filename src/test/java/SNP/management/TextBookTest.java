package SNP.management;


import SNP.management.domain.DTO.QuestionDTO;
import SNP.management.domain.DTO.TextBookDTO;
import SNP.management.domain.entity.Category;
import SNP.management.domain.entity.textbook.Question;
import SNP.management.domain.entity.textbook.TextBook;
import SNP.management.domain.enumlist.QuestionType;
import SNP.management.domain.enumlist.TextBookType;
import SNP.management.domain.repository.CategoryDataJpa;
import SNP.management.domain.repository.textbook.QuestionDataJpa;
import SNP.management.domain.repository.textbook.TextBookDataJpa;
import SNP.management.domain.service.textbook.TextBookService;
import SNP.management.web.resolver.SessionConst;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class TextBookTest {
    @PersistenceContext
    EntityManager em;
    @Autowired
     CategoryDataJpa categoryDataJpa;
    @Autowired
    TextBookDataJpa textBookDataJpa;
    @Autowired
    QuestionDataJpa questionDataJpa;
    @Autowired
     TextBookService textBookService;
    @Autowired
    HttpSession session;
    @BeforeEach
    void beforeEach() {
        session.setAttribute(SessionConst.LOGIN_TEACHER,"xxt1205@gmail.com");
    }
    @Test
    void saveTextBookAndFindById() {
        //given
        Integer categoryId = 6;

        TextBookDTO textBookDTO = new TextBookDTO(TextBookType.BASIC, "윌리를 찾아서", 1300,categoryId);

        TextBook textBook = textBookService.save(textBookDTO);
        Long textBookId = textBook.getId();
        em.flush();
        em.clear();

        //when
        TextBook foundTextBook = textBookDataJpa.findById(textBookId).orElseThrow(NullPointerException::new);
        foundTextBook.createCode();

        //then
        assertThat(textBookId).isEqualTo(foundTextBook.getId());

    }
    @Test
    void saveTextBookAndQuestion() {
        //given
        Integer categoryId = 9;

        TextBookDTO textBookDTO = new TextBookDTO(TextBookType.BASIC, "과학의 발상", 1000,categoryId);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        QuestionType[] questionType = {QuestionType.CREATIVITY,QuestionType.CRITICISM,QuestionType.REASONING,
                QuestionType.ANALYTICAL, QuestionType.CRITICISM, QuestionType.LOGICAL, QuestionType.CREATIVITY,
                QuestionType.UNDERSTANDING, QuestionType.CREATIVITY, QuestionType.LOGICAL,};


        //when
        TextBookDTO textBookDTOResult = textBookService.saveWithQuestion(textBookDTO, questionDTOList);
        List<Question> resultList = questionDataJpa.findByTextBookId(textBookDTOResult.getId());

        //then
        assertThat(resultList.size()).isEqualTo(questionDTOList.size());
    }

    @Test
    void updateTextBook() {
        //given
        Integer categoryId = 6;
        TextBookDTO textBookDTO = new TextBookDTO(TextBookType.BASIC, "윌리를 찾아서", 1300,categoryId);
        TextBook textBook = textBookService.save(textBookDTO);
        Long textBookId = textBook.getId();
        em.flush();

        //updateParameter
        Integer categoryIdUpdate = 10;
        TextBookDTO textBookDTOUpdate = new TextBookDTO(TextBookType.ESSAY, "제이슨을 찾아서", 900, categoryIdUpdate);

        //when
        TextBook foundTextBook = textBookDataJpa.findById(textBookId).orElseThrow(IllegalArgumentException::new);
        Category category = categoryDataJpa.findById(textBookDTOUpdate.getCategoryId()).orElseThrow(IllegalArgumentException::new);

        foundTextBook.ChangeTextBook(textBookDTOUpdate, category);
        em.flush();

        //then
        assertThat(foundTextBook).isEqualTo(textBookDataJpa.findById(textBookId).get());

    }

    @Test
    void updateTextBookService() {
        //given
        Integer categoryId = 6;
        TextBookDTO textBookDTO = new TextBookDTO(TextBookType.BASIC, "윌리를 찾아서", 1300,categoryId);
        TextBook textBook = textBookService.save(textBookDTO);
        Long textBookId = textBook.getId();
        em.flush();
        em.clear();

        //updateParameter
        Integer categoryIdUpdate = 10;
        TextBookDTO textBookDTOUpdate = new TextBookDTO(TextBookType.ESSAY, "제이슨을 찾아서", 900, categoryIdUpdate);
        textBookDTOUpdate.setId(textBookId);
        //when
//        textBookService.update(textBookDTOUpdate);
        //then
        em.flush();
    }

    @Test
    void deleteTextBook() {
        //given
        Integer categoryId = 6;
        TextBookDTO textBookDTO = new TextBookDTO(TextBookType.BASIC, "윌리를 찾아서", 1300,categoryId);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        TextBookDTO textBookDTOResult = textBookService.saveWithQuestion(textBookDTO, questionDTOList);
        Long id = textBookDTOResult.getId();
        em.flush();
        em.clear();

        //when
        TextBook textBook = textBookDataJpa.findByIdWithQuestion(id).orElseThrow(IllegalArgumentException::new);

        // then
        textBookDataJpa.delete(textBook);
        em.flush();
    }
}
