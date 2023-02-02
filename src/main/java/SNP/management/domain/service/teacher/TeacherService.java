package SNP.management.domain.service.teacher;

import SNP.management.domain.exceptionlist.LoginException;
import SNP.management.domain.DTO.TeacherDTO;
import SNP.management.domain.entity.Teacher;
import SNP.management.domain.repository.teacher.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherDTO findByLogin(TeacherDTO teacherDTO) {
        Teacher teacher = teacherRepository.findByLogin(teacherDTO).orElseThrow(LoginException::new);
        teacherDTO.teacherToDTO(teacher);
        return teacherDTO;
    }


}
