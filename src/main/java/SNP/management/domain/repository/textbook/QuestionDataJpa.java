package SNP.management.domain.repository.textbook;

import SNP.management.domain.entity.textbook.Question;
import SNP.management.domain.entity.textbook.TextBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionDataJpa extends JpaRepository<Question, Long> {
    public List<Question> findByTextBookId(Long id);

    @Modifying(flushAutomatically = true,clearAutomatically = true)
    @Query("DELETE FROM Question q where q.textBook IS NULL")
    public void deleteAllIsNull();

    @Modifying(flushAutomatically = true,clearAutomatically = true)
    @Query("DELETE FROM Question q WHERE q.textBook = :id")
    public void deleteByTextBook(@Param("id") Long id);
}
