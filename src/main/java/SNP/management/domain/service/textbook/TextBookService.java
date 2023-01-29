package SNP.management.domain.service.textbook;

import SNP.management.domain.DTO.QuestionDTO;
import SNP.management.domain.DTO.TextBookDTO;
import SNP.management.domain.entity.Category;
import SNP.management.domain.entity.textbook.TextBook;
import SNP.management.domain.repository.CategoryDataJpa;
import SNP.management.domain.repository.textbook.TextBookDataJpa;
import SNP.management.domain.repository.textbook.TextBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class TextBookService {

    private final TextBookRepository textBookRepository;
    private final TextBookDataJpa textBookDataJpa;
    private final CategoryDataJpa categoryDataJpa;
    private final QuestionService questionService;

    public Long saveTextBookAndQuestion(TextBookDTO textBookDTO, List<QuestionDTO> QuestionDTOList) {
        Category category = categoryDataJpa.findByName(textBookDTO.getCategoryName()).orElseThrow(NullPointerException::new);

        TextBook textBook = TextBook.createTextBook(textBookDTO, category);

        textBookDataJpa.save(textBook);// 저장후 id값 사용위해 객체 생성

        textBook.createCode();// index 를 위한 교재 코드 생성

        questionService.saveAll(QuestionDTOList, textBook); // 교재에 해당하는 문제 저장
        return textBook.getId();
    }

    public Long saveTextBook(TextBookDTO textBookDTO) {
        Category category = categoryDataJpa.findByName(textBookDTO.getCategoryName()).orElseThrow(NullPointerException::new);

        TextBook textBook = TextBook.createTextBook(textBookDTO, category);

        textBookDataJpa.save(textBook);

        return textBook.getId();
    }



}
