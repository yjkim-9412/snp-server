package SNP.management.domain.service.student;

import SNP.management.domain.repository.student.StudentLogDataJpa;
import SNP.management.domain.repository.student.StudentLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentLogService {

    private final StudentLogRepository studentLogRepository;
    private final StudentLogDataJpa studentLogDataJpa;


}
