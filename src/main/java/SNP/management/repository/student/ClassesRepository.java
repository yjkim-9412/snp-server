package SNP.management.repository.student;

import SNP.management.entity.student.Classes;

import java.util.List;
import java.util.Optional;

public interface ClassesRepository {

    public List<Classes> findClassesByStudentId(Long id);
}
