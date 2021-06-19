import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import java.util.Date;

import static java.lang.System.out;

public class RedisStorage {

    // Объект для работы с Redis
    private RedissonClient redisson;

    // Объект для работы с ключами
    private RKeys rKeys;

    // Объект для работы с Sorted Set'ом
    private RScoredSortedSet<String> usersList;

    private final static String KEY = "USERS";

    private double getTs() {
        return new Date().getTime();
    }

    public RScoredSortedSet<String> getUsersList() {
        return usersList;
    }

    void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        try {
            redisson = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            out.println("Не удалось подключиться к Redis");
            out.println(Exc.getMessage());
        }
        rKeys = redisson.getKeys();
        usersList = redisson.getScoredSortedSet(KEY);
        rKeys.delete(KEY);
    }


    void addUsers(int user_id)
    {
        //ZADD USERS
        usersList.add(getTs(), String.valueOf(user_id));
    }

    int calculateUsersNumber()
    {
        //ZCOUNT USERS
        return usersList.count(Double.NEGATIVE_INFINITY, true, Double.POSITIVE_INFINITY, true);
    }
}