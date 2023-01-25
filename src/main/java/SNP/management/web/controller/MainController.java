package SNP.management.web.controller;

import SNP.management.domain.DTO.TodayScheduleDTO;
import SNP.management.domain.service.schedule.ScheduleServiceImp;
import SNP.management.domain.service.student.StudentServiceImp;
import SNP.management.web.resolver.BindingResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MainController {

    private final StudentServiceImp studentService;
    private final ScheduleServiceImp scheduleService;
    private final BindingResolver bindingResolver;

    @GetMapping("/main/{dayOfWeek}")
    public List<TodayScheduleDTO> getMain(@PathVariable int dayOfWeek) {


        return scheduleService.findAllByDay(dayOfWeek);
    }

}
