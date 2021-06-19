import java.util.ArrayList;
import java.util.List;

public class Connections
{
    private List<Station> connections;

    public Connections() {
        connections = new ArrayList<>();
    }

    public List<Station> getConnections() {
        return connections;
    }

    public void addStation(Station station)
    {
        connections.add(station);
    }
}
