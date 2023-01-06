package SNP.management.controller;

import SNP.management.domain.ScheduleDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class StudentController {


    @PostMapping("/schedule")
    public String schedule(@RequestBody ScheduleDTO scheduleDTO) {

        log.info("id = {}", scheduleDTO.getId());

        for (Map.Entry<Integer, String> result : scheduleDTO.getScheduleMap().entrySet()) {
            log.info("result.getKey() = {}", result.getKey());
            log.info("result.getValue() = {}", result.getValue());
        }
        return null;
    }

}
