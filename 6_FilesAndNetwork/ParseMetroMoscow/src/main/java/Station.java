public class Station
{

    private String line;
    private String station;

    public Station(String line, String station) {
        this.line = line;
        this.station = station;
    }
    public String getLineNumber() {
        return line;
    }

    public String getStationName() {
        return station;
    }

}
