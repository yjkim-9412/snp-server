package SNP.management.Web.controller.student;

import SNP.management.Web.schedule.ScheduleDTO;
import SNP.management.Web.student.StudentDTO;
import SNP.management.domain.repository.student.StudentRepositoryImp;
import SNP.management.domain.service.student.StudentServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentRepositoryImp studentRepository;
    private final StudentServiceImp studentService;


    @PostMapping("/schedule")
    public String schedule(@RequestBody ScheduleDTO scheduleDTO) {

        log.info("id = {}", scheduleDTO.getId());

        for (Map.Entry<Integer, String> result : scheduleDTO.getScheduleMap().entrySet()) {
            log.info("result.getKey() = {}", result.getKey());
            log.info("result.getValue() = {}", result.getValue());
        }
        return null;
    }

    @PostMapping("/student/save")
    public String saveStudent(@RequestBody StudentDTO studentDTO) {

        studentService.save(studentDTO);

        return "/student/form";
    }

}
