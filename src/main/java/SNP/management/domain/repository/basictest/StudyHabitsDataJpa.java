package SNP.management.domain.repository.basictest;

import SNP.management.domain.entity.basictest.StudyHabits;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyHabitsDataJpa extends JpaRepository<StudyHabits,Long> {
}
