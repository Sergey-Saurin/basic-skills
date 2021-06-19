package ParseFile;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class XMLHandler extends DefaultHandler {

    private Voter voter;
    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");

    protected static Map<String, String> voters = new HashMap<>();

    private Map<String, Integer> voterCounts;

    public XMLHandler() {
        voterCounts = new HashMap<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        try {
            if (qName.equals("voter")) {
                voters.put(attributes.getValue("name"), attributes.getValue("birthDay"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("voter")) {
            voter = null;
        }
    }

    public void printDuplicatedVoters() {
        for (String voter : voterCounts.keySet()) {
            int count = voterCounts.get(voter);
            if (count > 1) {
                System.out.println(voter + " - " + count);
            }
        }
    }
}