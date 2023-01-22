package SNP.management.domain.service.student;

import SNP.management.domain.DTO.StudentDTO;
import SNP.management.domain.entity.student.Student;
import SNP.management.domain.entity.Teacher;
import SNP.management.domain.repository.RecordRepository;
import SNP.management.domain.repository.student.StudentRepositoryImp;
import SNP.management.domain.repository.teacher.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
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
    private final RecordRepository recordRepository;

    @Override
    public StudentDTO findById(Long id) {
        return new StudentDTO(studentRepository.findById(id).orElseThrow(NullPointerException::new));
    }

    //학생 저장 업데이트
    @Override
    public StudentDTO save(StudentDTO studentDTO) {


        // 존재 학생 없을시 새로 생성
        if (studentDTO.getId() == null) {

            //학생 이메일 중복검사
            duplicate(studentDTO);

            Student student = new Student(studentDTO);
            // 담임 검사
            checkTeacher(studentDTO, student);
            // 학생 체크
            checkStudyType(studentDTO, student);
            //학생저장후 DTO setId
            studentDTO.setId(studentRepository.save(student));

        }else {//기존 학생 유무 검증
            update(studentDTO);
        }
        return studentDTO;

    }

    @Override
    public void update(StudentDTO studentDTO) {
        Student findByStudent = studentRepository.findById(studentDTO.getId()).orElseThrow(NullPointerException::new);

        checkTeacher(studentDTO, findByStudent);

        checkStudyType(studentDTO, findByStudent);

        //학생 업데이트
        studentDTO.setId(studentRepository.save(findByStudent));
    }

    /**
     * 학생 이메일 중복 검사
     * @throws DuplicateKeyException 중복시 예외 발생
     */
    @Override
    public void duplicate(StudentDTO studentDTO) {
        if (studentRepository.findByEmail(studentDTO.getEmail()).isPresent()) {
                throw new DuplicateKeyException("학생 중복");
        }
    }

    /**
     * null 아닐시 해당수업 코스 저장 1단계 저장
     * @param studentDTO getStudyType StudyType 객체 조회
     */
    private Student checkStudyType(StudentDTO studentDTO, Student student) {
        if (studentDTO.getStudyType() != null) {
            student.setStudy(recordRepository.getFirstStudy(studentDTO.getStudyType()));
        }
        return student;
    }

    /**
     * save 학생의 담당 선생님 조회 검증
     */
    private void checkTeacher(StudentDTO studentDTO, Student getStudent) {
        if (studentDTO.getTeacherId() != null) {
            Optional<Teacher> teacher = teacherRepository.findById(studentDTO.getTeacherId());
            teacher.ifPresentOrElse(getStudent::connectTeacher,() ->log.info("Teacher is null"));
        }
    }
}
