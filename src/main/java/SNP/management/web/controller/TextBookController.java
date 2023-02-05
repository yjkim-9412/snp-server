package SNP.management.web.controller;

import SNP.management.domain.DTO.CategoryDTO;
import SNP.management.domain.DTO.TextBookDTO;
import SNP.management.domain.repository.CategoryDataJpa;
import SNP.management.domain.repository.textbook.QuestionDataJpa;
import SNP.management.domain.repository.textbook.TextBookRepository;
import SNP.management.domain.service.textbook.QuestionService;
import SNP.management.domain.service.textbook.TextBookService;
import SNP.management.web.form.TextBookForm;
import SNP.management.web.resolver.BindingResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/textbooks")
@RequiredArgsConstructor
public class TextBookController {
    private final TextBookRepository textBookRepository;
    private final TextBookService textBookService;
    private final BindingResolver bindingResolver;
    private final QuestionService questionService;
    private final QuestionDataJpa questionDataJpa;

    private final CategoryDataJpa categoryDataJpa;
    @GetMapping("/")
    public List<TextBookDTO> getAll() {
        return textBookRepository.findAllDTOType();
    }

    @GetMapping("/categories")
    public List<CategoryDTO> getAllCategories() {
        return categoryDataJpa.findAllDTO();
    }

    @PostMapping("/save")
    public Object save(@RequestBody @Validated TextBookForm textBookForm,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResolver.bindingAPI(bindingResult);
        }
        TextBookDTO textBookResult = textBookService.saveWithQuestion(textBookForm.getTextBookForm(), textBookForm.getQuestionArray());
        return textBookResult.getCode();
    }
    @GetMapping("/info/{code}")
    public Object info(@PathVariable("code") String code){
        TextBookDTO textBookDTO = textBookService.findByCodeDTO(code);
        return new TextBookForm(textBookDTO,questionService.findAllByTextBookId(textBookDTO.getId()));
    }
}
