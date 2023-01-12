package SNP.management.domain.service.student;

import SNP.management.domain.DTO.StudentDTO;
import SNP.management.domain.entity.student.Student;
import SNP.management.domain.entity.Teacher;
import SNP.management.domain.repository.student.StudentRepositoryImp;
import SNP.management.domain.repository.teacher.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImp implements StudentService {


    private final StudentRepositoryImp studentRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public StudentDTO findById(Long id) {
        return new StudentDTO(studentRepository.findById(id).orElseThrow(NullPointerException::new));
    }

    //학생 저장 업데이트
    @Override
    public StudentDTO save(StudentDTO studentDTO) {

        //기존 학생 유무 검증
        if (studentDTO.getId() == null) {
            // 존재 학생 없을시 새로 생성
            Student student = new Student(studentDTO);
            //담당 선생님 조회
            checkTeacher(studentDTO, student);

            //학생저장후 DTO setId
            studentDTO.setId(studentRepository.save(student));
        }else {
            Student findByStudent = studentRepository.findById(studentDTO.getId()).orElseThrow(NullPointerException::new);
            //학생 담당 선생님 조회 검증
            checkTeacher(studentDTO, findByStudent);
            //학생 업데이트
            studentDTO.setId(studentRepository.save(findByStudent));
        }
        return studentDTO;

    }



    private void checkTeacher(StudentDTO studentDTO, Student getStudent) {
        Optional<Teacher> teacher = teacherRepository.findById(studentDTO.getTeacherId());
        teacher.ifPresentOrElse(getStudent::connectTeacher,() ->log.info("Teacher is null"));
    }



}
