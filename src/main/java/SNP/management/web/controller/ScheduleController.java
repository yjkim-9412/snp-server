package SNP.management.web.controller;

import SNP.management.domain.service.schedule.ScheduleService;
import SNP.management.web.resolver.BindingResolver;
import SNP.management.domain.DTO.ScheduleDTO;
import SNP.management.domain.repository.student.StudentRepositoryImp;
import SNP.management.domain.service.student.StudentServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final BindingResolver bindingResolver;

    @PostMapping("/schedule")
    public Object postSchedule(@RequestBody @Validated ScheduleDTO scheduleDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {return bindingResolver.bindingAPI(bindingResult);}

        scheduleService.addSchedule(scheduleDTO);
        return null;
    }

    @GetMapping("/schedule/{id}")
    public ScheduleDTO getSchedule(@PathVariable Long id) {

        return scheduleService.findByStudentId(id);
    }

}
