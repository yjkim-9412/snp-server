package SNP.management.web.controller;

import SNP.management.domain.DTO.StudyDTO;
import SNP.management.domain.repository.StudyDataJpa;
import SNP.management.domain.service.StudyService;
import SNP.management.domain.service.schedule.ScheduleService;
import SNP.management.domain.service.student.StudentLogService;
import SNP.management.web.form.TodayStudyForm;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lesson")
@RequiredArgsConstructor
public class StudentLogController {

    private final StudentLogService studentLogService;
    private final ScheduleService scheduleService;
    private final StudyService studyService;
    private final StudyDataJpa studyDataJpa;

    @GetMapping("/{id}")
    public Object getCurrentDayOfStudy(@PathVariable Long id, @RequestParam("daySelect") Integer day) {
        StudyDTO studyByDay = studyService.getTodayStudy(id, day);
        List<String> studyDetailList = studyDataJpa.findAllByStudyType(studyByDay.getStudyType());

        return new TodayStudyForm(studyDetailList, studyByDay);
    }
}
