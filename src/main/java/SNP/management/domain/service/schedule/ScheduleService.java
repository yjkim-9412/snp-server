package SNP.management.domain.service.schedule;

import SNP.management.domain.DTO.TodayScheduleDTO;
import SNP.management.domain.DTO.ScheduleDTO;

import SNP.management.domain.entity.student.Schedule;
import SNP.management.domain.entity.student.Student;
import SNP.management.domain.enumlist.DayOfWeek;
import SNP.management.domain.repository.schedule.ScheduleDataJpa;
import SNP.management.domain.repository.schedule.ScheduleRepository;
import SNP.management.domain.repository.student.StudentRepository;
import SNP.management.domain.service.student.StudentServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
public class ScheduleService {

    private final StudentServiceImp studentService;
    private final StudentRepository studentRepository;
    private final ScheduleRepository scheduleRepository;
    private final ScheduleDataJpa scheduleDataJpa;

    public ScheduleDTO getSchedule(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(NullPointerException::new);
        return new ScheduleDTO().listToDTO(scheduleRepository.findClassesByStudentId(student.getId()));
    }
    public void createScheduleFor(Student student, ScheduleDTO scheduleDTO) {
        for (Map.Entry<Integer, String> e : scheduleDTO.getScheduleMap().entrySet()) {
            Schedule schedule = new Schedule(e.getKey(), e.getValue(), student);
            scheduleRepository.saveSchedule(schedule);
        }
    }

    public void addSchedule(ScheduleDTO scheduleDTO, Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        Student student = studentOptional.orElseThrow(NullPointerException::new);

        //해당학생 시간표 조회
        List<Schedule> scheduleList = scheduleRepository.findClassesByStudentId(student.getId());


        if (scheduleList.isEmpty()){// 시간표가 없을때
            log.info("scheduleList = {}", false);
            createScheduleFor(student, scheduleDTO);
        } else {// 시간표가 있을때 //기존 시간표와 파라미터 시간표 매치,검증
            log.info("scheduleList = {}", true);
            checkDuplicateAndSave(student, scheduleDTO, scheduleList);
        }
    }


    /**
     * 기존시간표와 파라미터 시간표 비교후 업데이트
     */
    public void checkDuplicateAndSave(Student student,ScheduleDTO scheduleDTO, List<Schedule> scheduleList) {
        Map<Integer, String> scheduleMap = scheduleDTO.getScheduleMap();// 파라미터 시간표
        List<Schedule> deleteList = new ArrayList<>();//제거 리스트 객체 생성
        List<Schedule> addList = new ArrayList<>();//추가 리스트 객체 생성

        // 파라미터 시간표
        scheduleList.forEach(schedule -> {// 해당 학생 기존 시간표 List 루프
            int day = schedule.getDayOfWeek().getDayInt();// 해당 학생 기존 시간표 요일
            if (scheduleMap.containsKey(day)) {//요일 일치 할시에 시간 업데이트
                schedule.changeSchedule(day, scheduleMap.get(day));
                scheduleMap.remove(day);
            }else {// 파라미터 스케줄과 일치하지 않으면 delete 목록에 추가
                deleteList.add(schedule);
            }
        });
        // 기존 data 에 없던 요일 시간표 entity 생성
        scheduleMap.forEach((key, value) -> addList.add(new Schedule(key,value,student)));
        scheduleDataJpa.saveAll(addList);//새로운 시간표 추가
        scheduleDataJpa.deleteAll(deleteList);// 파라미터와 일치 하지 않는 기존 시간표 제거.
    }
    /**학생 수업코스 조회 후 저장*/
    public List<TodayScheduleDTO> findAllByDay(int day) {
        return scheduleRepository.findAllByDay(DayOfWeek.values()[day]);
    }

    public ScheduleDTO findByStudentId(Long id){
       return new ScheduleDTO().listToDTO(scheduleDataJpa.findByStudentId(id));
    }
}
