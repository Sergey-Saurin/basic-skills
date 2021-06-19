package ParseFile;

import java.sql.Connection;
import java.sql.SQLException;

public class WriteDataInDBRunnable implements Runnable
{
    private String textRequest;
    private long start;


    public WriteDataInDBRunnable(String textRequest, long start) {
        this.textRequest = textRequest;
        this.start = start;

    }

    @Override
    public void run() {
        try(Connection connection = DBConnection.getConnection();)
        {
            String sql = "INSERT INTO voter_count(name, birthDate, `count`) " +
                    "VALUES " + textRequest +
                    " ON DUPLICATE KEY UPDATE `count`=`count` + 1";
            connection.createStatement().execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Duration: " + (System.currentTimeMillis() - start) + " ms");
    }
}
