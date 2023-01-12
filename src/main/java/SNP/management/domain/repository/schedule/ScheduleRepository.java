package SNP.management.domain.repository.schedule;

import SNP.management.domain.entity.student.Classes;

import java.util.List;

public interface ScheduleRepository {

    public List<Classes> findClassesByStudentId(Long id);
    public void saveSchedule(Classes classes);

}
