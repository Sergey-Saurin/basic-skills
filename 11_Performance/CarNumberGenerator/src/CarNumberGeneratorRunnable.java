import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CarNumberGeneratorRunnable implements java.lang.Runnable
{

    private int region;
    private long start;

    public CarNumberGeneratorRunnable(int region, long start) {
        this.region = region;
        this.start = start;
    }

    @Override
    public void run()
    {
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
