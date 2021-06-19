import org.redisson.client.protocol.ScoredEntry;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static java.lang.System.out;

@SpringBootApplication
public class RedisTest {

    private static final int SLEEP = 1000;
    private static final SimpleDateFormat DF = new SimpleDateFormat("HH:mm:ss");

    public static void main(String[] args) throws InterruptedException {

        RedisStorage redis = new RedisStorage();
        redis.init();

        //Заполним список
        for(int i = 1; i < 21; i++)
        {
            redis.addUsers(i);
        }

        long randomDate = System.currentTimeMillis() + 1000;

        while(true) {
            //String user = redis.getUsersList().first(); методом First() не получилось так как нужно проверять
            //оплаты, если время 0 значит оплатили услугу
            Collection<ScoredEntry<String>> userFirst = redis.getUsersList().entryRange(0, 0);
            Thread.sleep(1);
            userFirst.forEach(user ->{

                    log(Integer.parseInt(user.getValue()), "Show the user on the main page:");
                    //Если 0.0 значит услуга оплачена, подписчик первый в очереди, вызываем sleep
                    if(user.getScore() == 0.0)
                    {
                        try {
                            Thread.sleep(SLEEP);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    redis.addUsers(Integer.parseInt(user.getValue()));});

            //randomDate случайное время оплаты услуги
            if(randomDate < System.currentTimeMillis()) {
                ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
                RunnablePaid streamRun = new RunnablePaid(redis);
                executor.submit(streamRun);
                //executor.shutdown();
                randomDate = (long) (System.currentTimeMillis() + (Math.random() * 3000));
            }

        }
    }

    private static void log(int users, String text) {
        String log = String.format("[%s] " + text + " %d", DF.format(new Date()), users);
        out.println(log);
    }
}