package SNP.management.domain.DTO;

import SNP.management.domain.entity.student.Classes;
import SNP.management.domain.entity.student.Student;
import SNP.management.domain.enumlist.DayOfWeek;
import SNP.management.web.form.student.ScheduleForm;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class ScheduleDTO {

    private Long id;
    private Map<Integer, String> scheduleMap = new HashMap<>();

    public ScheduleDTO FormToDTO(Long id, ScheduleForm scheduleForm) {
        this.id = id;
        scheduleMap.putAll(scheduleForm.getSchedule());
        return this;
    }

    public ScheduleDTO listToDTO(List<Classes> classesList) {
        for (Classes classes : classesList) {
            scheduleMap.put(classes.getDayOfWeek().getDayInt(), classes.getTime());
        }
        return this;
    }
}
