package SNP.management.repository.student;

import SNP.management.domain.ClassTypeDTO;
import SNP.management.entity.student.Classes;
import SNP.management.entity.student.Student;

import java.util.List;

public interface ClassesRepository {

    public List<Classes> findClassesByStudentId(Long id);

}
