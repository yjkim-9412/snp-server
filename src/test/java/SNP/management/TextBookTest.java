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
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    void saveTextBook() {
        //given
        String categoryName = "SF명작";

        TextBookDTO textBookDTO = new TextBookDTO(TextBookType.BASIC, "윌리를 찾아서", 1300,categoryName);
        //when
        Long id = textBookService.saveTextBook(textBookDTO);
        TextBook textBook = textBookDataJpa.findById(id).orElseThrow(NullPointerException::new);
        textBook.createCode();
        //then
        assertThat(id).isEqualTo(textBook.getId());

    }

    @Test
    void saveTextBookAndQuestion() {
        //given
        String categoryName = "SF명작";
        TextBookDTO textBookDTO = new TextBookDTO(TextBookType.BASIC, "윌리를 찾아서", 1300,categoryName);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        boolean testBoolean = true;
        for (int i = 0; i < 10; i++) {
            questionDTOList.add(new QuestionDTO(QuestionType.ANALYTICAL,i, testBoolean));
            testBoolean = !testBoolean;
        }
        //when
        Long id = textBookService.saveTextBookAndQuestion(textBookDTO, questionDTOList);
        List<Question> resultList = questionDataJpa.findByTextBookId(id);
        //then
        assertThat(resultList.size()).isEqualTo(questionDTOList.size());
    }
}
