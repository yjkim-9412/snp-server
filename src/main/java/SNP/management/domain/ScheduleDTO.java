package SNP.management.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ScheduleDTO {

    private Long id;

    private Map<Integer, String> scheduleMap = new HashMap<>();

}
