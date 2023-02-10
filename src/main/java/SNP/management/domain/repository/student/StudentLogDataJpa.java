package SNP.management.domain.repository.student;

import SNP.management.domain.DTO.LogDTO;
import SNP.management.domain.entity.student.StudentLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentLogDataJpa extends JpaRepository<StudentLog,Long> {

    List<StudentLog> findAllByStudentIdOrderByCreateDateDesc(@Param("id") Long id);
    @Query("SELECT sl FROM StudentLog sl JOIN Student s ON sl.student.id = s.id JOIN Study st ON sl.study.id = st.id " +
            "JOIN TextBook t ON sl.textBook.id = t.id WHERE sl.student.id = :id ORDER BY sl.createDate DESC ")
    List<StudentLog> findFetchAllByStudentIdOrderByCreateDateDesc(@Param("id") Long id);

}
