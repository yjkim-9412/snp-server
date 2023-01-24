package SNP.management.domain.entity.student;

import SNP.management.domain.enumlist.DayOfWeek;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 학생 시간표 ENTITY
 */
@Entity
@Getter @Setter(AccessLevel.PRIVATE)
@Table(name = "SCHEDULE")
public class Schedule {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;
    private String time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    public Schedule saveClass(int day, String time, Student student) {
        Schedule schedule = new Schedule();
        schedule.setDayOfWeek(DayOfWeek.values()[day]);
        schedule.setTime(time);
        schedule.setStudent(student);
        return schedule;
    }


    public Schedule changeTime(String time) {
        this.time = time;
        return this;
    }

    public Schedule changeDayOfWeek(int day) {
        this.dayOfWeek = DayOfWeek.values()[day];
        return this;
    }
}
