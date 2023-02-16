package SNP.management.domain.repository.student;

import SNP.management.domain.entity.student.QuestionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionLogDataJpa extends JpaRepository<QuestionLog, Long> {



}
