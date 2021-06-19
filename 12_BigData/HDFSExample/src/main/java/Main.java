import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.*;
import java.net.URI;
import java.util.List;

public class Main
{
    private static String symbols = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String container = "0361efcdd878";
    private static String pathHadoop = "hdfs://" + container + ":8020/";

    //docker run -ti -p 4040:4040 -p 8020:8020 -p 8032:8032 -p 8088:8088 -p 9000:9000 -p 10020:10020
    // -p 19888:19888 -p 50010:50010 -p 50020:50020 -p 50070:50070 -p 50075:50075 -p 50090:50090 harisekhon/hadoop

    public static void main(String[] args) throws Exception
    {
        Configuration configuration = new Configuration();
        configuration.set("dfs.client.use.datanode.hostname", "true");
        System.setProperty("HADOOP_USER_NAME", "root");

        FileSystem hdfs = FileSystem.get(
                new URI("hdfs://" + container + ":8020"), configuration
        );

        Path file = new Path("hdfs://" + container + ":8020/folder/file2.txt");

        if (hdfs.exists(file)) {
            hdfs.delete(file, true);
        }

        OutputStream os = hdfs.create(file);
        BufferedWriter br = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8")
        );

        BufferedReader reader = new BufferedReader(new FileReader("res/text.txt"));
        String line;

        while ((line = reader.readLine()) != null) {
            br.write(line);
        }


        br.flush();
        br.close();
        hdfs.close();

        /**Предыдущее задание закомментировано начало*/
        /*String fileName = "testFile23.txt";
        String folderName = "folder";

        Configuration configuration = new Configuration();
        configuration.set("dfs.client.use.datanode.hostname", "true");

        System.setProperty("HADOOP_USER_NAME", "root");


        //FileSystem hdfs = FileSystem.get(new URI("hdfs://" + container + ":8020"), configuration);

        Path filePath = new Path("hdfs://" + container + ":8020/" + folderName + "/" + fileName);

        FileAccess fileAccess = new FileAccess(pathHadoop);

        //Удалить файл
        fileAccess.delete(filePath);

        //Создать файл
        fileAccess.create(filePath);

        //Добавить текст
        fileAccess.appendContent(filePath, getRandomWord());

        //Прочитать файл
        String contentFile = fileAccess.read(filePath);
        System.out.println(contentFile);

        //Список файлов в директории
        Path dirPath = new Path("hdfs://" + container + ":8020/" + folderName);
        List<String> list = fileAccess.list(dirPath);
        for (String fName : list)
        {
            System.out.println(fName);
        }
        //Завершить соединение
        fileAccess.closeHdfs();*/
        /**Предыдущее задание конец начало*/
    }

    private static String getRandomWord()
    {
        StringBuilder builder = new StringBuilder();
        int length = 2 + (int) Math.round(10 * Math.random());
        int symbolsCount = symbols.length();
        for(int i = 0; i < length; i++) {
            builder.append(symbols.charAt((int) (symbolsCount * Math.random())));
        }
        return builder.toString();
    }
}
