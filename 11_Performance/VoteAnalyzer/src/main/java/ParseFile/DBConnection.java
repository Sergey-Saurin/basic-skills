package ParseFile;

import java.sql.*;
import java.util.concurrent.ThreadPoolExecutor;

public class DBConnection
{
    private static String dbName = "DatabaseVoter";
    private static String dbUser = "root";
    private static String dbPass = "test";

    private static StringBuilder insertQuery = new StringBuilder();

    public static Connection getConnection()
    {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + dbName + "?user=" + dbUser + "&password=" + dbPass + "&useSSL=false&serverTimezone=UTC");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void multiInsert(ThreadPoolExecutor executor, long start)
    {
        WriteDataInDBRunnable runnable = new WriteDataInDBRunnable(insertQuery.toString(), start);
        executor.submit(runnable);

        insertQuery = new StringBuilder();
    }

    public static void countVoter(String name, String birthDay, ThreadPoolExecutor executor, long start){
        birthDay = birthDay.replace('.', '-');
        boolean isStart = insertQuery.length() == 0;
        insertQuery.append(isStart ? "" : ",").append("('").append(name).append("', '").append(birthDay).append("', 1)");
        if (insertQuery.length() > 2000000) {
            multiInsert(executor, start);
        }
    }

    public static void createTableAndConnection() {

        try(Connection connection = DBConnection.getConnection();)
        {
            connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
            connection.createStatement().execute("CREATE TABLE voter_count(" +
                    "id INT NOT NULL AUTO_INCREMENT, " +
                    "name TINYTEXT NOT NULL, " +
                    "birthDate DATE NOT NULL, " +
                    "`count` INT NOT NULL, " +
                    "PRIMARY KEY(id), " +
                    "UNIQUE KEY name_date(name(50), birthDate))");

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
