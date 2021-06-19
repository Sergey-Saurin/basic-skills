import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class CreateJSONFile
{
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static String pathFile = "files/metroMoscow.json";
    public static void createFile()
    {
        JsonObject resultJson = new JsonObject();
        JsonObject stationJson = new JsonObject();

        //Добавим станции в файл, обходим все линии и получаем для них названия станций
        ParseHTML.getListlineMetroFull().forEach(numberLineMetro -> {

            JsonElement jsonStationElement = gson.toJsonTree(ParseHTML.getListStationFull().stream()
                    .filter(s -> s.getLineNumber().equals(numberLineMetro.getNumber())).map(sn -> sn.getStationName()).toArray());
            stationJson.add(numberLineMetro.getNumber(), jsonStationElement);

        });
        resultJson.add("stations", stationJson);

        //Обходим и добавляем все переходы
        JsonElement jsonConnectionsElement = gson.toJsonTree(ParseHTML.getListConnectionsFull().stream().map(c -> c.getConnections()).toArray());
        resultJson.add("connections", jsonConnectionsElement);

        //Добавим линии в файл
        JsonElement jsonLine = gson.toJsonTree(ParseHTML.getListlineMetroFull());
        resultJson.add("lines", jsonLine);

        String dataJson = gson.toJson(resultJson);

        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathFile)))) {
            bw.write(dataJson);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
