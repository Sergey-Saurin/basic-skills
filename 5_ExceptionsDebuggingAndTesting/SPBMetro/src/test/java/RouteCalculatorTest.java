import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class RouteCalculatorTest extends TestCase
{

    List<Station> route;
    StationIndex stationIndex;

    @Override
    protected void setUp() throws Exception
    {
        route = new ArrayList<>();
        stationIndex = new StationIndex();

        Line line1 = new Line(1, "first line");
        Line line2 = new Line(2, "second line");
        Line line3 = new Line(3, "third line");

        Station station1 = new Station("Девяткино", line1);
        Station station2 = new Station("Пушкинская", line1);
        Station station3 = new Station("Нарвская", line1);
        Station station4 = new Station("Удельная", line2);
        Station station5 = new Station("Фрунзенская", line2);
        Station station6 = new Station("Приморская", line3);
        Station station7 = new Station("Ломоносовская", line3);

        line1.addStation(station1);
        line1.addStation(station2);
        line1.addStation(station3);
        line2.addStation(station4);
        line2.addStation(station5);
        line3.addStation(station6);
        line3.addStation(station7);

        route.add(station1);
        route.add(station2);
        route.add(station3);
        route.add(station4);
        route.add(station5);
        route.add(station6);
        route.add(station7);

        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);

        stationIndex.addStation(station1);
        stationIndex.addStation(station2);
        stationIndex.addStation(station3);
        stationIndex.addStation(station4);
        stationIndex.addStation(station5);
        stationIndex.addStation(station6);
        stationIndex.addStation(station7);

        //Создадим связи станций(перехады)
        stationIndex.addConnection(route.subList(2, 4));
        stationIndex.addConnection(route.subList(4, 6));


    }

    public void testCalculateDuration()
    {
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 17;
        assertEquals(expected, actual);
    }

    public void testGetShortestRouteGetRouteOnTheLine()
    {
        RouteCalculator calculator = new RouteCalculator(stationIndex);

        int actual   = calculator.getShortestRoute(route.get(0), route.get(2)).size();
        int expected = 3;
        assertEquals(expected, actual);

    }
    public void testGetShortestRouteGetRouteWithOneConnection()
    {
        RouteCalculator calculator = new RouteCalculator(stationIndex);

        //Индексы 0 и 4 чтоб был один переход
        //Тоже проверка количеством записей, так как если нет связи вернется "actual == 0"
        int actual   = calculator.getShortestRoute(route.get(0), route.get(4)).size();
        int expected = 5;
        assertEquals(expected, actual);

    }

    public void testGetShortestRouteGetRouteWithTwoConnections()
    {
        RouteCalculator calculator = new RouteCalculator(stationIndex);

        //Индексы 0 и 6 чтоб было два перехода
        //Тоже проверка количеством записей, так как если нет двух связей вернется "actual == 0"
        int actual   = calculator.getShortestRoute(route.get(0), route.get(6)).size();
        int expected = 7;
        assertEquals(expected, actual);

    }
}
