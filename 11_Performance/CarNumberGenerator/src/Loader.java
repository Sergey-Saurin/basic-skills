import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class Loader
{
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        /**Второй вариант**/
        /*int countCore = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(countCore);
        for (int regionCode = 1; regionCode < 100; regionCode++)
        {
            CarNumberGeneratorRunnable runnable = new CarNumberGeneratorRunnable(regionCode, start);
            executor.submit(runnable);
        }
        executor.shutdown();*/

        /**третий вариант не работает, оставил для примера как я ошибаюсь**/
        ForkJoinPool pool = new ForkJoinPool();
        List<Callable<CustomRecursiveAction>> taskList = new ArrayList<>();
        for (int regionCode = 1; regionCode < 100; regionCode++)
        {
            taskList.add(new MyCallable(regionCode, start));
        }
        pool.invokeAll(taskList);
    }
}
