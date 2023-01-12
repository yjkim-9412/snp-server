package SNP.management.domain.service.teacher;

import SNP.management.web.exception.LoginException;
import SNP.management.domain.DTO.TeacherDTO;
import SNP.management.domain.entity.Teacher;
import SNP.management.domain.repository.teacher.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherDTO findByLogin(TeacherDTO teacherDTO) {

        Optional<Teacher> findTeacher = teacherRepository.findByLogin(teacherDTO);
        Teacher teacher = findTeacher.orElseThrow(LoginException::new);
        teacherDTO.teacherToDTO(teacher);
        return teacherDTO;
    }


}
