package SNP.management.domain.entity.student;

import SNP.management.domain.calendar.DaysOfWeek;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter(AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "CLASSES")
public class Classes {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private DaysOfWeek dayOfWeek;
    private String time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    public Classes saveClass(int day, String time, Student student) {
        Classes classes = new Classes();
        classes.setDayOfWeek(DaysOfWeek.values()[day]);
        classes.setTime(time);
        classes.setStudent(student);
        return classes;
    }

    public Classes changeTime(String time) {
        this.time = time;
        return this;
    }

    public Classes changeDayOfWeek(int day) {
        this.dayOfWeek = DaysOfWeek.values()[day];
        return this;
    }
}
