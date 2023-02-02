package SNP.management.domain.repository.student;

import SNP.management.domain.entity.student.QuestionLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionLogDataJpa extends JpaRepository<QuestionLog, Long> {
}
