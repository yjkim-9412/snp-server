package SNP.management.domain.DTO;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class ScheduleDTO {

    private Long id;

    private Map<Integer, String> scheduleMap = new ConcurrentHashMap<>();

}
