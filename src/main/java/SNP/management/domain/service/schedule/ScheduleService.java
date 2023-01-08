package SNP.management.domain.service.schedule;

import SNP.management.Web.schedule.ScheduleDTO;
import SNP.management.domain.entity.student.Classes;
import SNP.management.domain.entity.student.Student;

import java.util.List;

public interface ScheduleService {

    public void createScheduleFor(Student student, ScheduleDTO scheduleDTO);

    public void addSchedule(Student student, ScheduleDTO scheduleDTO);

    public void checkDuplicateAndSave(Student student, ScheduleDTO scheduleDTO, List<Classes> classesByStudentId);
}
