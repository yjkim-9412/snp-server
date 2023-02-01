package SNP.management.domain.repository;

import SNP.management.domain.entity.study.Study;
import SNP.management.domain.enumlist.StudyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudyDataJpa extends JpaRepository<Study, Long> {

    Optional<Study> findByDetailAndStudyTypeAndNumberOfDaysLessThanEqual(String detail, StudyType studyType, Integer count);

    Optional<Study> findByStep(Integer step);

    Optional<Study> findByStudyTypeAndStep(StudyType studyType, Integer step);
}
