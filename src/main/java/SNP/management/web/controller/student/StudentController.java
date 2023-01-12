package SNP.management.web.controller.student;

import SNP.management.domain.DTO.ScheduleDTO;
import SNP.management.domain.service.schedule.ScheduleServiceImp;
import SNP.management.web.form.student.*;
import SNP.management.web.resolver.BindingResolver;
import SNP.management.domain.DTO.StudentDTO;
import SNP.management.domain.service.student.StudentServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentServiceImp studentService;
    private final ScheduleServiceImp scheduleService;
    private final BindingResolver bindingResolver;


    @PostMapping("/saveForm")
    public Object saveStudent(@RequestBody @Validated StudentSaveForm studentSaveForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return bindingResolver.bindingAPI(bindingResult);
        }
        studentService.save(new StudentDTO().FormToUpdateDTO(studentSaveForm));


        return "/students";
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
    public Object getInfo (@PathVariable Long id){
        return new StudentForm(studentService.findById(id), scheduleService.getSchedule(id));
    }
    @PostMapping("/info/{id}/schedule")
    public void saveClasses (@PathVariable Long id, @RequestBody @Validated ScheduleForm scheduleForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            bindingResolver.bindingAPI(bindingResult);
        }
        scheduleService.addSchedule(new ScheduleDTO().FormToDTO(id, scheduleForm));
    }



    @PostMapping("/log/{id}/save")
    public void saveLog(@PathVariable Long id, @RequestBody @Validated SaveLogForm saveLogForm ,BindingResult bindingResult) {

    }


}
