
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SubscriptionPK implements Serializable
{
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="student_id")
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="course_id")
    private Course course;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        SubscriptionPK pk = (SubscriptionPK) o;
        return Objects.equals(student, pk.student) && Objects.equals(course, pk.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, course);
    }

}
