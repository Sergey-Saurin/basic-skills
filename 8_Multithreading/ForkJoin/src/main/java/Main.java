
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ForkJoinPool;

public class Main
{
    private static String url = "https://skillbox.ru/";
    private static long start = System.currentTimeMillis();
    public static void main(String[] args) throws IOException {
        SiteMapGenerator siteMap = new SiteMapGenerator(url);
        new ForkJoinPool().invoke(new RecursiveGenerator(siteMap));

        String result = createSiteMap(siteMap, 0);
        FileOutputStream fileOutputStream = new FileOutputStream("data/site_map.txt");
        fileOutputStream.write(result.getBytes());
        fileOutputStream.close();
    }

    public static String createSiteMap(SiteMapGenerator siteMap, int level) {
        String tabs = String.join("", Collections.nCopies(level, "\t"));
        StringBuilder result = new StringBuilder(tabs + siteMap.getUrl());
        siteMap.getChildren().forEach(child -> {
            result.append("\n").append(createSiteMap(child, level + 1));
        });
        System.out.println("Duration: " + (System.currentTimeMillis() - start));
        return result.toString();
    }
}
