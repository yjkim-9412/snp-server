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


@Service
@RequiredArgsConstructor
@Transactional
public class TextBookService {

    private final TextBookRepository textBookRepository;
    private final TextBookDataJpa textBookDataJpa;
    private final CategoryDataJpa categoryDataJpa;
    private final QuestionService questionService;

    public Long saveWithQuestion(TextBookDTO textBookDTO, List<QuestionDTO> QuestionDTOList) {

        TextBook textBook = save(textBookDTO);

        questionService.saveAll(QuestionDTOList, textBook); // 교재에 해당하는 문제 저장

        return textBook.getId();
    }

    /**
     * 카테고리 조회 및 추가후 TextBook 저장
     * @param textBookDTO 카테고리명 textBookDTO.getCategoryName()
     * @return TextBook
     */
    public TextBook save(TextBookDTO textBookDTO) {
        Category category = categoryDataJpa.findById(textBookDTO.getCategoryId())
                .orElseThrow(IllegalArgumentException::new);

        TextBook textBook = TextBook.createTextBook(textBookDTO, category);

        textBookDataJpa.save(textBook);

        return textBook;
    }

    public void update(TextBookDTO textBookDTO) {
        TextBook textBookFindById = textBookDataJpa.findById(textBookDTO.getId())
                .orElseThrow(IllegalArgumentException::new);

        textBookFindById.ChangeTextBook(textBookDTO,
                categoryDataJpa.findById(textBookDTO.getCategoryId()).orElseThrow(IllegalArgumentException::new));
    }



}
