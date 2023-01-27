package SNP.management.domain.DTO;

import SNP.management.domain.entity.student.Schedule;
import SNP.management.web.form.student.ScheduleForm;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class ScheduleDTO {
    @NotEmpty
    private Map<Integer, String> scheduleMap = new ConcurrentHashMap<>();

    public ScheduleDTO TestToDTO(ScheduleForm scheduleForm) {
        scheduleMap.putAll(scheduleForm.getSchedule());
        return this;
    }
    public ScheduleDTO FormToDTO(Map<Integer, String> scheduleMap) {
        this.scheduleMap.putAll(scheduleMap);
        return this;
    }

    public ScheduleDTO listToDTO(List<Schedule> scheduleList) {
        for (Schedule schedule : scheduleList) {
            scheduleMap.put(schedule.getDayOfWeek().getDayInt(), schedule.getTime());
        }
        return this;
    }
}
