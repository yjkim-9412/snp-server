package SNP.management.domain.repository.student;

import SNP.management.domain.entity.student.Classes;

import java.util.List;

public interface ClassesRepository {

    public List<Classes> findClassesByStudentId(Long id);

}
