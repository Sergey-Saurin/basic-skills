import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;

public class SaveDataToBase
{
    public static void recording(MongoDatabase database, String path, MongoCollection<Document> collection)
    {
        Gson gson = new Gson();
        collection.drop();

        List<String[]> list = ReadFileCSV.readFile(path);
        for (String[] line : list) {

            //Все курсы
            String[] coursesArray = line[2].split(",");
            String courses = gson.toJson(coursesArray);

            //Новый документ
            Document firstDocument = new Document()
                    .append("Name", line[0])
                    .append("Age", parseStringToInt(line[1]))
                    .append("Courses", courses);

            collection.insertOne(firstDocument);

        }
    }
    private static  int parseStringToInt(String number)
    {
        try{
            return Integer.parseInt(number);
        }catch (Exception e)
        {
            return 0;
        }
    }
}
