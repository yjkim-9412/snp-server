package SNP.management.domain.repository.textbook;

import SNP.management.domain.entity.textbook.TextBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TextBookDataJpa extends JpaRepository<TextBook, Long> {
    @Query("SELECT t FROM TextBook t JOIN FETCH t.questionList")
    public Optional<TextBook> findByIdWithQuestion(Long id);


}
