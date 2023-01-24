package SNP.management.domain.repository;

import SNP.management.domain.entity.study.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyDataJpa extends JpaRepository<Study, Long> {
}
