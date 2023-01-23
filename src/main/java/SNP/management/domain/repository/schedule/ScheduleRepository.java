package SNP.management.domain.repository.schedule;

import SNP.management.domain.DTO.RecordDTO;
import SNP.management.domain.entity.student.Classes;
import SNP.management.domain.entity.study.Study;
import SNP.management.domain.entity.study.StudyType;

import java.util.List;

public interface ScheduleRepository {

    public List<Classes> findClassesByStudentId(Long id);
    public void saveSchedule(Classes classes);
    public List<Classes> findAllLast();
    Study getFirstStudy(StudyType studyType);
    public List<RecordDTO> findAllByDay(int day);


}
