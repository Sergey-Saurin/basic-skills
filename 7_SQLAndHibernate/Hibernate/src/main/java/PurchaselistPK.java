import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PurchaselistPK implements Serializable
{
    @Column(name = "student_name",columnDefinition = "varchar(100)")
    private String studentName;

    @Column(name = "course_name",columnDefinition = "varchar(100)")
    private String courseName;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        PurchaselistPK pk = (PurchaselistPK) o;
        return Objects.equals(studentName, pk.studentName) && Objects.equals(courseName, pk.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentName, courseName);
    }

}
