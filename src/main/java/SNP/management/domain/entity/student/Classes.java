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
@Table(name = "CLASSES")
public class Classes {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;
    private String time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    public Classes saveClass(int day, String time, Student student) {
        Classes classes = new Classes();
        classes.setDayOfWeek(DayOfWeek.values()[day]);
        classes.setTime(time);
        classes.setStudent(student);
        return classes;
    }


    public Classes changeTime(String time) {
        this.time = time;
        return this;
    }

    public Classes changeDayOfWeek(int day) {
        this.dayOfWeek = DayOfWeek.values()[day];
        return this;
    }
}
