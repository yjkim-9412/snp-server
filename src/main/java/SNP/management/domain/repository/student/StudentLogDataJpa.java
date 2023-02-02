package SNP.management.domain.repository.student;

import SNP.management.domain.entity.student.StudentLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StudentLogDataJpa extends JpaRepository<StudentLog,Long> {


}
