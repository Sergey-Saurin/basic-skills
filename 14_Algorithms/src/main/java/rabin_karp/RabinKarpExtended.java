package rabin_karp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class RabinKarpExtended
{
    private String text;
    private TreeMap<Integer, Integer> number2position;

    public final static int d = 256;

    public RabinKarpExtended(String text) throws RuntimeException
    {

        boolean exception = checkLength(text);
        if (exception)
        {
            throw new RuntimeException("invalid length");
        }else{
            this.text = text;
            createIndex();
        }

    }

    public List<Integer> search(String query) {
        ArrayList<Integer> indices = new ArrayList<>();

        int queryLength = query.length();
        int textLength = text.length();
        int i, j;
        int p = 0; // хеш-значение для шаблона
        int t = 0; // хеш-значение для txt
        int h = 1;
        int q = 9;

        for (i = 0; i < queryLength - 1; i++)

            h = (h * d) % q;
        for (i = 0; i < queryLength; i++) {
            p = (d * p + query.charAt(i)) % q;
            t = (d * t + text.charAt(i)) % q;
        }
        for (i = 0; i <= textLength - queryLength; i++) {
            if (p == t) {
                for (j = 0; j < queryLength; j++) {
                    if (text.charAt(i + j) != query.charAt(j))
                        break;
                }
                if (j == queryLength)
                    //System.out.println("Pattern found at index " + i);
                    for (int g = i; g < queryLength + i; g++) {
                        indices.add(g);
                    }
            }

            if (i < textLength - queryLength) {
                t = (d * (t - text.charAt(i) * h) + text.charAt(i + queryLength)) % q;
                if (t < 0)
                    t = (t + q);
            }
        }
        return indices;
    }


    private void createIndex()
    {

    }

    private boolean checkLength(String text)
    {
        char[] result = text.toCharArray();
        TreeMap<Character, Character> arraySymbol = new TreeMap<>();
        for (int i = 0; i < result.length; i++)
        {
            char symbol = result[i];
            arraySymbol.put(symbol, symbol);
            if (arraySymbol.size() > 9)
            {
                return true;
            }
        }

        return false;

    }

}