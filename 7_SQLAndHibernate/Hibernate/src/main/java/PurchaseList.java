import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "purchaselist")
public class PurchaseList
{

    @EmbeddedId
    private PurchaselistPK purchaselistPK;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="student_name",referencedColumnName="name",insertable = false,updatable = false)
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="course_name",referencedColumnName="name",insertable = false,updatable = false)
    private Course course;

    /*@Column(name = "student_name", insertable = false, updatable = false)
    private String studentName;

    @Column(name = "course_name", insertable = false, updatable = false)
    private String courseName;*/

    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    //////////////////////////////////////////////////////////////////////////////////////////////
    public PurchaselistPK getPurchaselistPK() {
        return purchaselistPK;
    }

    public void setPurchaselistPK(PurchaselistPK purchaselistPK) {
        this.purchaselistPK = purchaselistPK;
    }

    /*public String getStudentName() {
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
    }*/
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }


}
