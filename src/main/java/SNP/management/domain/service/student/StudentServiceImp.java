package SNP.management.domain.service.student;

import SNP.management.domain.DTO.StudentDTO;
import SNP.management.domain.entity.student.Student;
import SNP.management.domain.repository.StudyRepository;
import SNP.management.domain.repository.student.StudentDataJpa;
import SNP.management.domain.repository.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImp{

    private final StudentRepository studentRepository;
    private final StudentDataJpa studentDataJpa;
    private final StudyRepository studyRepository;
    private final StudentLogService studentLogService;

    public StudentDTO findById(Long id) {
    return new StudentDTO(getStudentById(id));
    }

    //학생 저장 업데이트
    public StudentDTO save(StudentDTO studentDTO) {

        // 존재 학생 없을시 새로 생성
        if (studentDTO.getId() == null) {

            //학생 이메일 중복검사
            duplicate(studentDTO);

            //엔티티 객체 생성
            Student student = Student.createStudent(studentDTO);

            // 학생 스터디 저장
            saveStudyTypeToStudent(studentDTO, student);

            //학생저장후 DTO setId
            studentDTO.setId(studentRepository.save(student));

            //로그 저장
            studentLogService.saveFirstLog(student);
        }else {//기존 학생 유무 검증
            update(studentDTO);
        }
        return studentDTO;

    }

    public void update(StudentDTO studentDTO) {
        Student findByStudent = getStudentById(studentDTO.getId());

        //로그 업데이트
        studentLogService.StudentLogUpdater(studentDTO, findByStudent);

        //학생 업데이트
        findByStudent.changeStudent(studentDTO);

    }

    public List<StudentDTO> findByAll() {
        return studentRepository.findByAll();
    }

    public List<StudentDTO> findByAllAndStudy() {
        return studentRepository.findByAllAndStudy();
    }

    /**
     * 학생 이메일 중복 검사
     * @throws DuplicateKeyException 중복시 예외 발생
     */
    public void duplicate(StudentDTO studentDTO) {
        if (studentRepository.findByEmail(studentDTO.getEmail()).isPresent()) {
                throw new DuplicateKeyException("학생 중복");
        }
    }

    /**
     * null 아닐시 해당수업 코스 저장 1단계 저장
     * @param studentDTO getStudyType StudyType 객체 조회
     */
    private void saveStudyTypeToStudent(StudentDTO studentDTO, Student student) {
        if (studentDTO.getStudyType() != null && studentDTO.getStudyType() != student.getStudyType()) {
            student.changeStudy(studyRepository.getFirstStudy(studentDTO.getStudyType()));
        }
    }

    /**
     * Optional 처리 후 Student return
     * @param id 학생Id
     * @return Student
     */
    private Student getStudentById(Long id) {
        return  studentDataJpa.findById(id).orElseThrow(IllegalArgumentException::new);
    }


}
