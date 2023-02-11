package SNP.management.domain.service.schedule;

import SNP.management.domain.DTO.ScheduleDTO;
import SNP.management.domain.DTO.TodayScheduleDTO;
import SNP.management.domain.enumlist.DayOfWeek;
import SNP.management.domain.repository.schedule.ScheduleDataJpa;
import SNP.management.domain.repository.schedule.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class RequestScheduleService {
    private final ScheduleDataJpa scheduleDataJpa;
    private final ScheduleRepository scheduleRepository;

    public Boolean hasTodaySchedule(Long id, Integer today) {
        return scheduleDataJpa.findByStudentIdAndDayOfWeek(id, DayOfWeek.values()[today]).isPresent();
    }

    public ScheduleDTO findByStudentId(Long id){
        return new ScheduleDTO().listToDTO(scheduleDataJpa.findByStudentId(id));
    }

    public List<TodayScheduleDTO> findAllByDay(int day) {
        return scheduleRepository.findAllByDay(DayOfWeek.values()[day]);
    }
}
