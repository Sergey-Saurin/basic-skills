package ParseFile;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static ParseFile.XMLHandler.voters;

public class LoaderVote
{
    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private static SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    private static HashMap<Integer, WorkTime> voteStationWorkTimes = new HashMap<>();
    private static HashMap<Voter, Integer> voterCounts = new HashMap<>();

    public static void main(String[] args) throws Exception
    {
        DBConnection.createTableAndConnection();

        String fileName = "res/data-1572M.xml";
        long start = System.currentTimeMillis();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(new File(fileName), handler);

        //Запись в два потока, второй создает текст SQL запроса, пока первый пишет в БД затем наоборот
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

        voters.forEach((name, birthDay) -> {
            try {
                DBConnection.countVoter(name, birthDay, executor, start);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        //заполним оставщиеся, которые не прошли условие "if (insertQuery.length() > ..." в методе countVoter
        DBConnection.multiInsert(executor, start);
        executor.shutdown();
       // System.out.println("Duration: " + (System.currentTimeMillis() - start) + " ms");
    }

}