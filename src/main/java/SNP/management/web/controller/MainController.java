package SNP.management.web.controller;

import SNP.management.domain.service.schedule.ScheduleServiceImp;
import SNP.management.domain.service.student.StudentServiceImp;
import SNP.management.web.resolver.BindingResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainController {

    private final StudentServiceImp studentService;
    private final ScheduleServiceImp scheduleService;
    private final BindingResolver bindingResolver;


    @GetMapping("/")
    public Object getMain() {

        return "main";
    }

}
