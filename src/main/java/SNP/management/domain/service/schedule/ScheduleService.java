package SNP.management.domain.service.schedule;

import SNP.management.domain.DTO.StudentDTO;
import SNP.management.domain.DTO.TodayScheduleDTO;
import SNP.management.domain.DTO.ScheduleDTO;

import SNP.management.domain.entity.student.Schedule;
import SNP.management.domain.entity.student.Student;
import SNP.management.domain.enumlist.DayOfWeek;
import SNP.management.domain.repository.schedule.ScheduleDataJpa;
import SNP.management.domain.repository.schedule.ScheduleRepository;
import SNP.management.domain.repository.student.StudentDataJpa;
import SNP.management.domain.repository.student.StudentRepository;
import SNP.management.domain.service.student.StudentServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
/**
 * 학생 날짜 수업조회는 RecordRepository 에 있습니다.
 * 해당 ScheduleServiceImp 는 학생 수업 스케줄 추가
 */
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleDataJpa scheduleDataJpa;

    private final StudentDataJpa studentDataJpa;

    public ScheduleDTO getSchedule(Long id) {
        Student student = studentDataJpa.findById(id).orElseThrow(IllegalArgumentException::new);
        return new ScheduleDTO().listToDTO(scheduleRepository.findClassesByStudentId(student.getId()));
    }
    public void createScheduleList(Student student, ScheduleDTO scheduleDTO) {
        List<Schedule> scheduleList = new ArrayList<>();
        for (Map.Entry<Integer, String> e : scheduleDTO.getScheduleMap().entrySet()) {
            scheduleList.add(Schedule.createSchedule(e.getKey(), e.getValue(), student));
        }
        scheduleDataJpa.saveAll(scheduleList);
    }

    public void saveSchedule(ScheduleDTO scheduleDTO, Long id) {
        Student student = studentDataJpa.findById(id).orElseThrow(IllegalArgumentException::new);

        //해당학생 시간표 조회
        List<Schedule> scheduleList = scheduleRepository.findClassesByStudentId(student.getId());

        if (scheduleList.isEmpty()){// 시간표가 없을때
            log.info("scheduleList = {}", false);
            createScheduleList(student, scheduleDTO);
            return;
        }
            log.info("scheduleList = {}", true);
            checkDuplicateAndUpdate(student, scheduleDTO, scheduleList);
    }


    /**
     * 기존시간표와 파라미터 시간표 비교후 업데이트
     */
    public void checkDuplicateAndUpdate(Student student, ScheduleDTO scheduleDTO, List<Schedule> scheduleList) {
        Map<Integer, String> scheduleMap = scheduleDTO.getScheduleMap();// 파라미터 시간표

        // 파라미터중복 되지 않는 시간표 filter.
        List<Schedule> deleteList = DuplicateHandler(scheduleList, scheduleMap);

        //기존에 없던 새로운 시간표 추가
        List<Schedule> addList = createAddList(student, scheduleMap);

        scheduleDataJpa.saveAll(addList);
        scheduleDataJpa.deleteAll(deleteList);
    }

    private List<Schedule> createAddList(Student student, Map<Integer, String> scheduleMap) {

        List<Schedule> addList = new ArrayList<>();

        scheduleMap.forEach((key, value) -> addList.add(Schedule.createSchedule(key,value, student)));
        return addList;
    }
    /**
     * @param scheduleList 기존 시간표
     * @param scheduleMap 파라미터 시간표
     */
    private List<Schedule> DuplicateHandler(List<Schedule> scheduleList, Map<Integer, String> scheduleMap) {
        List<Schedule> deleteList = new ArrayList<>();//제거 리스트 객체 생성

        scheduleList.forEach(schedule -> {// 해당 학생 기존 시간표 List 루프

            int day = schedule.getDayOfWeek().getDayInt();// 해당 학생 기존 시간표 요일

            if (scheduleMap.containsKey(day)) {//요일 일치 할시에 시간 업데이트
                schedule.changeSchedule(day, scheduleMap.get(day));
                scheduleMap.remove(day);
            }else {// 파라미터 스케줄과 일치하지 않으면 delete 목록에 추가
                deleteList.add(schedule);
            }
        });

        return deleteList;
    }

    public List<TodayScheduleDTO> findAllByDay(int day) {
        return scheduleRepository.findAllByDay(DayOfWeek.values()[day]);
    }



    public ScheduleDTO findByStudentId(Long id){
       return new ScheduleDTO().listToDTO(scheduleDataJpa.findByStudentId(id));
    }

    public Boolean hasTodaySchedule(Long id, Integer today) {
        return scheduleDataJpa.findByStudentIdAndDayOfWeek(id, DayOfWeek.values()[today]).isPresent();
    }
}
