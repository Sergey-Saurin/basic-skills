import org.redisson.api.RScoredSortedSet;

public class RunnablePaid extends Thread implements java.lang.Runnable
{
    private RScoredSortedSet<String> userList;

    public RunnablePaid(RedisStorage redis)
    {
        userList = redis.getUsersList();
    }

    @Override
    public void run()
    {
        //Случайный пользователь
        int randomPaid = 1 + (int) (Math.random() * 20);
        userList.add(0, String.valueOf(randomPaid));
        System.out.println(randomPaid + "////////////////////////////////////////////////////////////////////////////////////");
    }
}
