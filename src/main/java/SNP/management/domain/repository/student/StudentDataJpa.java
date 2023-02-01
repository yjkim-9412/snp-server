package SNP.management.domain.repository.student;

import SNP.management.domain.entity.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentDataJpa extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student As s join fetch s.study WHERE s.id = :id")
    Optional<Student> findByIdAndStudy(@Param("id") Long id);
}
