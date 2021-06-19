
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main
{
    public static void main(String[] args) throws InterruptedException {
        String srcFolder = "C:\\Users\\Sergey\\Desktop\\src\\";
        String dstFolder = "C:\\Users\\Sergey\\Desktop\\dst\\";
        int newWidth = 300;

        File srcDir = new File(srcFolder);
        long start = System.currentTimeMillis();

        File[] allFiles = srcDir.listFiles();

        int countCore = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(countCore);
        //ExecutorService executor = Executors.newCachedThreadPool();

        for (File file : allFiles)
        {
            StreamsRunnable streamRun = new StreamsRunnable(file, newWidth, dstFolder, start);
            executor.submit(streamRun);
        }
        executor.shutdown();

    }
}
