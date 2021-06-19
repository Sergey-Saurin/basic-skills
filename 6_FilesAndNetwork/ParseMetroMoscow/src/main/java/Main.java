import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main
{
    private static String pathFile = "files/metroMoscow.json";
    public static void main(String[] args)
    {
        ParseHTML.parseFile();

        CreateJSONFile.createFile();

        parseJSONFile();

    }

    private static void parseJSONFile()
    {
        try
        {
            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(readFile());

            //Выведем количестов станций на каждой линии
            JSONObject stations = (JSONObject) jsonData.get("stations");
            stations.forEach((o, o2) -> {
                System.out.println("Номер линии: " + o.toString() + " количество станций: " + ((JSONArray) o2).size());

            });
            //Выведем количество переходов в метро
            JSONArray connections = (JSONArray) jsonData.get("connections");
            System.out.println("\nКоличество переходов в метро: " + connections.size());
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private static String readFile()
    {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(pathFile));
            lines.forEach(line -> builder.append(line));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }

}
