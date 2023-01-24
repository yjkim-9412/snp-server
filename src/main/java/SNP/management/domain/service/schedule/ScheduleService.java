package SNP.management.domain.service.schedule;

import SNP.management.domain.DTO.TodayScheduleDTO;
import SNP.management.domain.DTO.ScheduleDTO;
import SNP.management.domain.entity.student.Schedule;
import SNP.management.domain.entity.student.Student;

import java.util.List;

public interface ScheduleService {

    /**
     * 테스트시 사용 그 이외에는 단독 사용 금지
     */
    public void createScheduleFor(Student student, ScheduleDTO scheduleDTO);

    public void addSchedule(ScheduleDTO scheduleDTO);


    public void checkDuplicateAndSave(Student student, ScheduleDTO scheduleDTO, List<Schedule> scheduleByStudentId);

    public ScheduleDTO getSchedule(Long id);

    public List<TodayScheduleDTO> findAllByDay(int day);
}
