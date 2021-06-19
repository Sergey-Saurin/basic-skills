import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Main
{
    private static StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
    private static Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
    private static SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

    public static void main(String[] args)
    {
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PurchaseList> query = builder.createQuery(PurchaseList.class);
        Root<PurchaseList> root = query.from(PurchaseList.class);
        query.select(root);
        List<PurchaseList> purchaseList = session.createQuery(query).getResultList();

        for (PurchaseList purchase : purchaseList)
        {
            System.out.println(purchase.getCourse().getName() + " " + purchase.getStudent().getName());
        }


        /*String hqlPurchase = "FROM " + PurchaseList.class.getSimpleName();
        List<PurchaseList> purchaseList = session.createQuery(hqlPurchase).getResultList();

        session.beginTransaction();
        for(PurchaseList purchase : purchaseList) {
            //Получим ID объект "курс"
            CriteriaBuilder builder = session.getCriteriaBuilder();
            //Создаем запрос к классу Course
            CriteriaQuery<Course> queryCourse = builder.createQuery(Course.class);
            Root<Course> rootCourse = queryCourse.from(Course.class);
            //Обходим результат запроса с условием "name = """, возвращается одна запись
            queryCourse.select(rootCourse).where(builder.equal(rootCourse.get("name"), purchase.getCourseName()));
            Integer courseId = session.createQuery(queryCourse).getSingleResult().getId();

            //Получим ID объект "студент" те же действия
            CriteriaQuery<Student> queryStudent = builder.createQuery(Student.class);
            Root<Student> rootStudent = queryStudent.from(Student.class);
            queryStudent.select(rootStudent).where(builder.equal(rootStudent.get("name"), purchase.getStudentName()));
            Integer studentId = session.createQuery(queryStudent).getSingleResult().getId();

            //Записем строку в таблицу
            LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList(
                    new LinkedPurchaseListPK(courseId, studentId),
                    courseId,
                    studentId);

            session.save(linkedPurchaseList);
        }
        session.getTransaction().commit();*/

        session.close();

    }
}
