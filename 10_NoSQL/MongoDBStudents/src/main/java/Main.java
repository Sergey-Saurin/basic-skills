
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.BsonDocument;
import org.bson.Document;

import java.util.function.Consumer;

public class Main
{
    public static String path = "data/mongo.csv";
    public static void main(String[] args)
    {

        MongoClient mongoClient = new MongoClient( "127.0.0.1" , 27017 );

        MongoDatabase database = mongoClient.getDatabase("local");
        MongoCollection<Document> collection = database.getCollection("Students");

        SaveDataToBase.recording(database, path, collection);

        //Один документ, это один студент, поэтому количество просто
        System.out.println("Количество студентов: " + collection.countDocuments());

        //Количество студентов старше 40 лет
        System.out.println("Количество студентов старше 40 лет: " + collection.countDocuments(Filters.gt("Age", 40)));

        //имя самого молодого студента.
        BsonDocument query2 = BsonDocument.parse("{Age: 1}");
        String nameStudent = collection.find().sort(query2).first().get("Name").toString();
        System.out.println("Самый молодой студент: " + nameStudent);

        //список курсов самого старого студента.
        BsonDocument query3 = BsonDocument.parse("{Age: -1}");
        Document document = collection.find().sort(query3).first();
        System.out.println("Курсы самого старого студента: " + document.get("Name") + " - " + document.get("Courses"));
    }

}
