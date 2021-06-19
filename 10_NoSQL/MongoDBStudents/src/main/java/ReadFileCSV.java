import au.com.bytecode.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFileCSV
{
    public static List<String[]> readFile(String path)
    {
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<String[]> listCSV = new ArrayList<>();
        String[] line;
        while (true) {
            try {
                line = reader.readNext();
                if (line == null) {
                    break;
                }
                //Проверка данных, если не три элемента массива, данные не верные
                if (line.length == 3) {
                    listCSV.add(line);
                }

            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listCSV;
    }
}
