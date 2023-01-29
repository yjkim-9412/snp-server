package SNP.management.domain.repository.textbook;

import SNP.management.domain.entity.textbook.TextBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextBookDataJpa extends JpaRepository<TextBook, Long> {
}
