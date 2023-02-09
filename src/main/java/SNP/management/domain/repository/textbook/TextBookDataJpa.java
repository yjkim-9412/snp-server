package SNP.management.domain.repository.textbook;

import SNP.management.domain.DTO.TextBookDTO;
import SNP.management.domain.entity.textbook.TextBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TextBookDataJpa extends JpaRepository<TextBook, Long> {
    @Query("SELECT t FROM TextBook t JOIN FETCH t.questionList")
    Optional<TextBook> findByIdWithQuestion(Long id);

    Optional<TextBook> findByCode(String code);

    @Query("SELECT t FROM TextBook t where t.name LIKE %:name%")
    List<TextBook> findAllByNameLike(@Param("name") String name);

}
