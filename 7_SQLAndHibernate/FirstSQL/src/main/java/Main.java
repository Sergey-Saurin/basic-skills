import java.sql.*;

public class Main
{
    public static void main(String[] args)
    {
        String url = "jdbc:mysql://localhost:3306/skillbox_v2?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8";

        String user = "root";
        String pass = "test";

        try {
            Connection connection = DriverManager.getConnection(url,user,pass);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("" +
                    "SELECT pl.course_name, " +
                    "COUNT(pl.course_name) / (SELECT MAX(MONTH(CountMonth.subscription_date)) " +
                            "FROM PurchaseList CountMonth " +
                            "WHERE pl.course_name = CountMonth.course_name) AS CountMonthSales " +
                    "FROM PurchaseList pl " +
                    //В таблице только 2018 год, но в задании сказано что нужен только 2018, хоть других годов нет
                    //сделал проверку всй равно
                    "WHERE pl.subscription_date BETWEEN \"2018-01-01\" AND \"2018-12-31\" " +
                    "GROUP BY pl.course_name " +
                    "ORDER BY pl.course_name");
            while (resultSet.next())
            {
                String coursesName = resultSet.getString("course_name");

                Double averageSales = resultSet.getDouble("CountMonthSales");
                System.out.println(coursesName + " - в среднем куплено курсов: " + String.format("%.2f",averageSales));
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }
}
