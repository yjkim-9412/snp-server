package SNP.management.web.form.student;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ScheduleForm {
    private Map<Integer, String> schedule = new HashMap<>();


    public void setMap(Integer day, String time) {
        schedule.put(day, time);
    }

}
