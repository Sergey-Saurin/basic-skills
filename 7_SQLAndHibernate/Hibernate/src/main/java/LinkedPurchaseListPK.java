import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LinkedPurchaseListPK implements Serializable {

    @Column(name = "course_id")
    private Integer courseId;

    @Column(name = "student_id")
    private Integer studentId;
//////////////////////////////////////////////////////////////////////////////////////////

    public LinkedPurchaseListPK(Integer courseId, Integer studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        LinkedPurchaseListPK pk = (LinkedPurchaseListPK) o;
        return Objects.equals(studentId, pk.studentId) && Objects.equals(courseId, pk.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }
}