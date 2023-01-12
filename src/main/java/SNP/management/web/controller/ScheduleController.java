package SNP.management.web.controller;

import SNP.management.web.resolver.BindingResolver;
import SNP.management.domain.DTO.ScheduleDTO;
import SNP.management.domain.repository.student.StudentRepositoryImp;
import SNP.management.domain.service.student.StudentServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final StudentRepositoryImp studentRepository;
    private final StudentServiceImp studentService;
    private final BindingResolver bindingResolver;

    @PostMapping("/schedule")
    public Object schedule(@RequestBody @Validated ScheduleDTO scheduleDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {return bindingResolver.bindingAPI(bindingResult);}
        log.info("id = {}", scheduleDTO.getId());

        for (Map.Entry<Integer, String> result : scheduleDTO.getScheduleMap().entrySet()) {
            log.info("result.getKey() = {}", result.getKey());
            log.info("result.getValue() = {}", result.getValue());
        }
        return null;
    }
}
