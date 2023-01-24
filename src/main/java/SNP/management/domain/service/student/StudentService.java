package SNP.management.domain.service.student;

import SNP.management.domain.DTO.ScheduleDTO;
import SNP.management.domain.DTO.StudentDTO;
import SNP.management.domain.entity.student.Student;

import java.util.List;

public interface StudentService {

    public StudentDTO save(StudentDTO studentDTO);

    public StudentDTO findById(Long id);

    public void update(StudentDTO studentDTO);

    public void duplicate(StudentDTO studentDTO);

    public List<StudentDTO> findByAll();

}
