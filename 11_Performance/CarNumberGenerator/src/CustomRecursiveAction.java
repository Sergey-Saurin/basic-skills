import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class CustomRecursiveAction extends RecursiveAction {

    private int region;
    private long start;
    private static final int THRESHOLD = Runtime.getRuntime().availableProcessors();

    public CustomRecursiveAction(int region, long start) {
        this.region = region;
        this.start = start;
    }

    @Override
    protected void compute() {
        createSubtasks();
    }

    private List<CustomRecursiveAction> createSubtasks() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("res/numbers " + region + ".txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

        for(int number = 1; number < 1000; number++)
        {
            StringBuilder strBuilder = new StringBuilder();
            for (char firstLetter : letters)
            {   for (char secondLetter : letters)
                {   for (char thirdLetter : letters)
                    {
                        strBuilder.append(firstLetter);
                        strBuilder.append(padNumber(number, 3));
                        strBuilder.append(secondLetter);
                        strBuilder.append(thirdLetter);
                        strBuilder.append(padNumber(region, 2));
                        strBuilder.append("\n");
                    }
                }
            }
            writer.write(strBuilder.toString());

        }

        writer.flush();
        writer.close();

        System.out.println((System.currentTimeMillis() - start) + " ms");
        return null;
    }

    private static void creatFile()
    {

    }

    private static String padNumber(int number, int numberLength)
    {
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();
        StringBuilder strBuilder = new StringBuilder();
        for(int i = 0; i < padSize; i++) {
            strBuilder.append("0");
        }
        strBuilder.append(numberStr);
        return strBuilder.toString();
    }
}