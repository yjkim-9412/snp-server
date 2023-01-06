package SNP.management.service.schedule;

import SNP.management.domain.ScheduleDTO;
import SNP.management.entity.student.Classes;
import SNP.management.entity.student.Student;

import java.util.List;
import java.util.Map;

public interface ScheduleService {

    public void createScheduleFor(Student student, ScheduleDTO scheduleDTO);

    public void addSchedule(Student student, ScheduleDTO scheduleDTO);

    public void checkDuplicateAndSave(Student student, ScheduleDTO scheduleDTO, List<Classes> classesByStudentId);
}
