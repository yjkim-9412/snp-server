package SNP.management.domain.service.schedule;

import SNP.management.domain.DTO.TodayScheduleDTO;
import SNP.management.domain.DTO.ScheduleDTO;

import SNP.management.domain.entity.student.Schedule;
import SNP.management.domain.entity.student.Student;
import SNP.management.domain.enumlist.DayOfWeek;
import SNP.management.domain.repository.schedule.ScheduleRepositoryImp;
import SNP.management.domain.repository.student.StudentRepositoryImp;
import SNP.management.domain.service.student.StudentServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
/**
 * 학생 날짜 수업조회는 RecordRepository 에 있습니다.
 * 해당 ScheduleServiceImp 는 학생 수업 스케줄 추가
 */
public class ScheduleServiceImp implements ScheduleService{

    private final StudentServiceImp studentService;
    private final StudentRepositoryImp studentRepository;
    private final ScheduleRepositoryImp scheduleRepository;

    @Override
    public ScheduleDTO getSchedule(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(NullPointerException::new);
        return new ScheduleDTO().listToDTO(scheduleRepository.findClassesByStudentId(student.getId()));
    }
    @Override
    public void createScheduleFor(Student student, ScheduleDTO scheduleDTO) {
        for (Map.Entry<Integer, String> e : scheduleDTO.getScheduleMap().entrySet()) {
            Schedule schedule = new Schedule().saveClass(e.getKey(), e.getValue(), student);
            scheduleRepository.saveSchedule(schedule);
        }
    }

    @Override
    public void addSchedule(ScheduleDTO scheduleDTO) {
        Optional<Student> studentOptional = studentRepository.findById(scheduleDTO.getId());
        Student student = studentOptional.orElseThrow(NullPointerException::new);

        //해당학생 시간표 조회
        List<Schedule> scheduleByStudentId = scheduleRepository.findClassesByStudentId(student.getId());


        if (scheduleByStudentId.isEmpty()){// 시간표가 없을때
            log.info("classesByStudentId = {}", true);
            createScheduleFor(student, scheduleDTO);
        } else {// 시간표가 있을때 //기존 시간표와 파라미터 시간표 매치,검증
            checkDuplicateAndSave(student, scheduleDTO, scheduleByStudentId);
        }
    }


    /**
     * 기존시간표와 파라미터 시간표 비교후 업데이트
     */
    @Override
    public void checkDuplicateAndSave(Student student, ScheduleDTO scheduleDTO, List<Schedule> scheduleByStudentId) {
        for (Schedule schedule : scheduleByStudentId) {// 해당 학생 기존 시간표
            for (Map.Entry<Integer, String> es : scheduleDTO.getScheduleMap().entrySet()){ // 파라미터 시간표

                if (schedule.getDayOfWeek().getDayInt() != es.getKey()
                        && !schedule.getTime().equals(es.getValue())) { //서로 일치하는 시간표가 없을때

                    scheduleRepository.saveSchedule(schedule);

                } else if (schedule.getDayOfWeek().getDayInt() == es.getKey()
                        && !schedule.getTime().equals(es.getValue())) { // 요일만 일치할때

                    Schedule changeTime = schedule.changeTime(es.getValue());
                    scheduleRepository.saveSchedule(changeTime);

                } else if (schedule.getDayOfWeek().getDayInt() != es.getKey()
                        && schedule.getTime().equals(es.getValue())) {// 시간만 일치할때.

                    Schedule changeDayOfWeek = schedule.changeDayOfWeek(es.getKey());
                    scheduleRepository.saveSchedule(changeDayOfWeek);
                }

            }
        }
    }
    /**학생 수업코스 조회 후 저장*/
    @Override
    public List<TodayScheduleDTO> findAllByDay(int day) {
        return scheduleRepository.findAllByDay(DayOfWeek.values()[day]);
    }
}
