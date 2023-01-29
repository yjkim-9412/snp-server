package SNP.management.domain.repository.textbook;

import SNP.management.domain.entity.textbook.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionDataJpa extends JpaRepository<Question, Long> {
    public List<Question> findByTextBookId(Long id);
}
