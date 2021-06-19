import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.RecursiveAction;
import java.util.regex.Pattern;


public class RecursiveGenerator extends RecursiveAction {
    private SiteMapGenerator siteMap;

    public RecursiveGenerator(SiteMapGenerator siteMap) {
        this.siteMap = siteMap;
    }

    @Override
    protected void compute() {
        try {
            Thread.sleep(500);
            Document document =  Jsoup.connect(siteMap.getUrl()).ignoreContentType(true).timeout(10000).get();

            Elements elements = document.select("body").select("a");
            for (Element el : elements) {
                String childUrl = el.absUrl("href");
                if (checkCorrectUrl(childUrl)) {
                    siteMap.addChild(new SiteMapGenerator(childUrl));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (SiteMapGenerator child : siteMap.getChildren()) {
            RecursiveGenerator task = new RecursiveGenerator(child);
            task.fork();
            task.join();
        }
    }

    private boolean checkCorrectUrl(String url) {
        Pattern pattern = Pattern.compile("^" + siteMap.getUrl());
        Pattern patternNotImage = Pattern.compile("([^\\s]+(\\.(?i)(jpg|png|gif|bmp|pdf))$)");
        Pattern patternNotHT = Pattern.compile("#([\\w\\-]+)?$");

        return pattern.matcher(url).lookingAt()
                && !patternNotImage.matcher(url).find()
                && !patternNotHT.matcher(url).find();
    }
}