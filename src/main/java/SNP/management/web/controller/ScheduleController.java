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

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final BindingResolver bindingResolver;

    @PostMapping("/schedule/{id}")
    public Object postSchedule(@PathVariable Long id, @RequestBody @Validated HashMap<Integer, String> scheduleMap, BindingResult bindingResult) {
        ScheduleDTO scheduleDTO = new ScheduleDTO().FormToDTO(scheduleMap);
        if (bindingResult.hasErrors()) {return bindingResolver.bindingAPI(bindingResult);}
        else if (scheduleMap.size() > 3 || scheduleMap.isEmpty()) {
            Map<String, String> mapErrors = new ConcurrentHashMap<>();
            mapErrors.put("size", String.valueOf(scheduleMap.size()));
            return mapErrors;
        }

        scheduleService.addSchedule(scheduleDTO, id);
        return null;
    }

    @GetMapping("/schedule/{id}")
    public Map<Integer, String> getSchedule(@PathVariable Long id) {

        return scheduleService.findByStudentId(id).getScheduleMap();
    }

}
