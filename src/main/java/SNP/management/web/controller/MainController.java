package SNP.management.web.controller;

import SNP.management.domain.DTO.TodayScheduleDTO;
import SNP.management.domain.service.schedule.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MainController {

    private final ScheduleService scheduleService;

    @GetMapping("/main/{dayOfWeek}")
    public List<TodayScheduleDTO> getMain(@PathVariable int dayOfWeek) {


        return scheduleService.findAllByDay(dayOfWeek);
    }

}
