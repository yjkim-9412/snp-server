package SNP.management.domain.repository.schedule;

import SNP.management.domain.DTO.ScheduleDTO;
import SNP.management.domain.DTO.TodayScheduleDTO;
import SNP.management.domain.entity.student.Schedule;
import SNP.management.domain.enumlist.DayOfWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ScheduleDataJpa extends JpaRepository<Schedule, Long> {

    @Query("select new SNP.management.domain.DTO.TodayScheduleDTO(s.id, sc.time, s.name, s.parentPhone, st.detail) " +
            "from Schedule sc " +
            "join sc.student s "+
            "on sc.dayOfWeek= :dayOfWeek left join s.study st ")
    List<TodayScheduleDTO> findTodayScheduleDTO(@Param("dayOfWeek") DayOfWeek dayOfWeek);

    List<Schedule> findByStudentId(Long id);

    Optional<Schedule> findByStudentIdAndDayOfWeek(Long id, DayOfWeek dayOfWeek);

}
