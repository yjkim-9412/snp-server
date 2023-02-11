package SNP.management.web.controller;

import SNP.management.domain.service.schedule.ScheduleService;
import SNP.management.web.form.student.*;
import SNP.management.web.resolver.BindingResolver;
import SNP.management.domain.DTO.StudentDTO;
import SNP.management.domain.service.student.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;
    private final ScheduleService scheduleService;
    private final BindingResolver bindingResolver;


    @PostMapping("/saveForm")
    public Object saveStudent(@RequestBody @Validated StudentSaveForm studentSaveForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return bindingResolver.bindingAPI(bindingResult);
        }
        StudentDTO saved = studentService.save(new StudentDTO().FormToSaveDTO(studentSaveForm));


        return saved.getId();
    }

    @GetMapping("/list")
    public List<StudentDTO> getStudentList() {

        return studentService.findByAllAndStudy();
    }
    @PostMapping("/update")
    public Object updateStudent(@RequestBody @Validated StudentUpdateForm studentUpdateForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return bindingResolver.bindingAPI(bindingResult);
        }
        studentService.save(new StudentDTO().FormToUpdateDTO(studentUpdateForm));

        return "/student/form";
    }

    @GetMapping("/info/{id}")
    public StudentDTO getStudent(@PathVariable Long id) {

        return studentService.findById(id);
    }


}
