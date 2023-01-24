package SNP.management.domain.repository.student;

import SNP.management.domain.DTO.StudentDTO;
import SNP.management.domain.entity.student.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    public Long save(Student student);

    public Optional<Student> findById(Long id);

    public List<Student> findAllByName(String name);

    public Optional<Student> findByEmail(String email);
    public void delete(Student student);

    public List<StudentDTO> findByAll();

}
