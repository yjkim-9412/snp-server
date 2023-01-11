package SNP.management.Web.controller.student;

import SNP.management.Web.form.student.StudentSaveForm;
import SNP.management.Web.form.student.StudentUpdateForm;
import SNP.management.Web.resolver.BindingResolver;
import SNP.management.domain.DTO.StudentDTO;
import SNP.management.domain.repository.student.StudentRepositoryImp;
import SNP.management.domain.service.student.StudentServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentRepositoryImp studentRepository;
    private final StudentServiceImp studentService;
    private final BindingResolver bindingResolver;


    @PostMapping("/students/saveForm")
    public Object saveStudent(@RequestBody @Validated StudentSaveForm studentSaveForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {return bindingResolver.bindingAPI(bindingResult);}
        studentService.save(new StudentDTO().FormToStudentDTO(studentSaveForm));

        return "/students";
    }

    @PostMapping("/student/update")
    public Object updateStudent(@RequestBody @Validated StudentUpdateForm studentUpdateForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {return bindingResolver.bindingAPI(bindingResult);}
        studentService.save(new StudentDTO().FormToStudentDTO(studentUpdateForm));

        return "/student/form";
    }


}
