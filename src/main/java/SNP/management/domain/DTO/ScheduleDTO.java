package SNP.management.domain.DTO;

import SNP.management.domain.entity.student.Schedule;
import SNP.management.web.form.student.ScheduleForm;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ScheduleDTO {

    private Long id;
    private Map<Integer, String> scheduleMap = new HashMap<>();

    public ScheduleDTO FormToDTO(Long id, ScheduleForm scheduleForm) {
        this.id = id;
        scheduleMap.putAll(scheduleForm.getSchedule());
        return this;
    }

    public ScheduleDTO listToDTO(List<Schedule> scheduleList) {
        for (Schedule schedule : scheduleList) {
            scheduleMap.put(schedule.getDayOfWeek().getDayInt(), schedule.getTime());
        }
        return this;
    }
}
