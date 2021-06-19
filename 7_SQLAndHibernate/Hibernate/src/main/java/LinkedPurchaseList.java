import javax.persistence.*;

@Entity
@Table(name = "LinkedPurchaselist")
public class LinkedPurchaseList
{
    @EmbeddedId
    private LinkedPurchaseListPK lPK;

    @Column(name = "course_id", insertable = false, updatable = false)
    private Integer courseId;

    @Column(name = "student_id", insertable = false, updatable = false)
    private Integer studentId;
    ////////////////////////////////////////////////////////////////
    public LinkedPurchaseList(LinkedPurchaseListPK lPK, Integer courseId, Integer studentId) {
        this.lPK = lPK;
        this.courseId = courseId;
        this.studentId = studentId;
    }

    public LinkedPurchaseListPK getLinkedPurchaseListPK() {
        return lPK;
    }

    public void setLPK(LinkedPurchaseListPK lPK) {
        this.lPK = lPK;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }


}