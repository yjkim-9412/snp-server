package SNP.management.domain.repository.schedule;

import SNP.management.domain.DTO.TodayScheduleDTO;
import SNP.management.domain.entity.student.Schedule;
import SNP.management.domain.entity.study.Study;
import SNP.management.domain.entity.study.StudyType;
import SNP.management.domain.enumlist.DayOfWeek;

import java.util.List;

public interface ScheduleRepository {

    public List<Schedule> findClassesByStudentId(Long id);
    public void saveSchedule(Schedule schedule);
    public List<Schedule> findAllLast();
    public List<TodayScheduleDTO> findAllByDay(DayOfWeek dayOfWeek);


}
