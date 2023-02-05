package SNP.management.domain.repository;

import SNP.management.domain.DTO.CategoryDTO;
import SNP.management.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryDataJpa extends JpaRepository<Category, Integer> {

     Optional<Category> findByName(String name);

    @Query("SELECT new SNP.management.domain.DTO.CategoryDTO(c.id, c.name) FROM Category c")
     List<CategoryDTO> findAllDTO();
}
