import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ParseHTML
{
    private static ArrayList<Station> listStationFull = new ArrayList<>();
    private static ArrayList<LineMetro> listlineMetroFull = new ArrayList<>();
    private static ArrayList<Connections> listConnectionsFull = new ArrayList<>();

    public static void parseFile()
    {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.moscowmap.ru/metro.html#lines").maxBodySize(0).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Element elements = doc.getElementsByAttributeValue("id", "metrodata").get(0);

        //Линии метро
        createLineMetro(elements);

        //Станции метро
        createStation(elements);

        //Переходы
        creatConnections(elements);


    }

    private static void createLineMetro(Element elements)
    {
        Element table  = elements.select("div").get(1);
        //Выбрать getElementsByAttributeValueContaining так как первый класс измененное имя чтоб была активна первая линия при обновлении страницы
        Elements records = table.getElementsByAttributeValueContaining("class","js-metro-line t-metrostation-list-header t-icon-metroln ln-");

        records.forEach((record) -> {
            //Вопрос, вот тут наверное не нужно делать проверку, нашлось ли значение номер линии или имя
            //думаю если структуру html поменяют, то в любом случаи будет что попало, фатальной ошибки не будет
            //Проверки всегда нужно делать, но как принято в такой ситуации поступать?
                listlineMetroFull.add(new LineMetro(record.attr("data-line"), record.text()));
            });


    }

    private static void createStation(Element elements)
    {
        Element table  = elements.select("div").get(1);
        //выбираем список станций на линии
        Elements listStation =table.getElementsByAttributeValue("class", "js-metro-stations t-metrostation-list-table");

        listStation.forEach((record) -> {
            String stationLine =record.attr("data-line");

            //Обходим все названия станций на линии
            Elements dataStation = record.getElementsByAttributeValue("class", "name");
            dataStation.forEach((stationElement) -> {
                        String stationName =stationElement.select("span").get(0).text();;
                        listStationFull.add(new Station(stationLine, stationName));
                    });
        });
    }

    private static void creatConnections(Element elements)
    {
        Element table  = elements.select("div").get(1);
        //чтоб получить номер линии, обход по этому классу
        Elements listStation =table.getElementsByAttributeValue("class", "js-metro-stations t-metrostation-list-table");

        //Обходим все станции
        listStation.forEach((record) -> {
            String numberLineStation =record.attr("data-line");
            Elements dataStation = record.select("a");

            //Обход данные по станциям
            dataStation.forEach((stationElement) -> {
                String stationName =stationElement.child(1).text();
                //Создадим объект переходов
                Connections connections = new Connections();
                //добавим текущую станцию, у которой ищем переходы
                List<Station> currentStation = listStationFull.stream()
                        .filter(u -> u.getLineNumber().equals(numberLineStation) && u.getStationName().equals(stationName)).collect(Collectors.toList());

                //Получаем по индексу, так как станция будет одна
                connections.addStation(currentStation.get(0));

                //Обходим только переходы внутри линии
                Elements dataConnections = stationElement.getElementsByAttributeValueContaining("class", "t-icon-metroln ln-");
                dataConnections.forEach((conElement)-> {
                    String lineNumberConnection = conElement.attr("class").substring(conElement.attr("class").lastIndexOf(" ln-") + 4);
                    //Получим имя станции в кавычках
                    int startIndex = conElement.attr("title").lastIndexOf('«') + 1;
                    int endIndex = conElement.attr("title").lastIndexOf('»');
                    String nameConnectionStation = conElement.attr("title").substring(startIndex, endIndex);
                    List<Station> foundStation = listStationFull.stream()
                            .filter(u -> u.getLineNumber().equals(lineNumberConnection) && u.getStationName().equals(nameConnectionStation)).collect(Collectors.toList());
                    //Получаем по индексу, так как станция будет одна
                    connections.addStation(foundStation.get(0));
                });
                //Если список станций у массива объекта connections больше 1 значит есть переходы
                if (connections.getConnections().size() > 1)
                {
                    listConnectionsFull.add(connections);
                }
                //Получим записи о переходах текущей станции в других станциях, чтоб не задублировать переходы
                //Например получаем переходы по линии 1 с линией 2, полсе обрабатываем линию 2, там тоже есть переход с линией 1
                //эта запись нам не нужна так как получили её ранее
                Elements deleteInfoCurrentStationOfConnection = table.getElementsByAttributeValue("class", "t-icon-metroln ln-" + numberLineStation);
                deleteInfoCurrentStationOfConnection.forEach((element)-> {
                    element.remove();
                });
            });

        });
    }

    public static ArrayList<Station> getListStationFull() {
        return listStationFull;
    }

    public static ArrayList<LineMetro> getListlineMetroFull() {
        return listlineMetroFull;
    }

    public static ArrayList<Connections> getListConnectionsFull() {
        return listConnectionsFull;
    }

}
