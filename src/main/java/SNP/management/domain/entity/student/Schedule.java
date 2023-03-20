package SNP.management.domain.entity.student;

import SNP.management.domain.entity.BaseEntity;
import SNP.management.domain.enumlist.DayOfWeek;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * 학생 시간표 ENTITY
 */
@Entity
@Getter @Setter(AccessLevel.PRIVATE)
@Table(name = "SCHEDULE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;
    private String time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    private void setSchedule (int day, String time, Student student) {
        this.dayOfWeek = DayOfWeek.values()[day];
        this.time = time;
        this.student = student;
    }

    public static Schedule createSchedule(int day, String time, Student student) {
        Schedule schedule = new Schedule();
        schedule.setSchedule(day, time, student);
        return schedule;
    }


    public void changeSchedule(Integer day, String time) {
        this.dayOfWeek = DayOfWeek.values()[day];
        this.time = time;
    }
}
