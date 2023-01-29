package SNP.management.domain.repository;

import SNP.management.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryDataJpa extends JpaRepository<Category, Long> {

    public Optional<Category> findByName(String id);
}
