
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {


        System.out.println("Введите путь до папки:");
        String directory = new Scanner(System.in).nextLine().trim();

        try {
            //Первый вариант, но тут не нашел как вывести два знака после запятой, этот метод отрезает символы после запятой
            System.out.println("Размер папки: " + FileUtils.byteCountToDisplaySize(FileUtils.sizeOfDirectory(new File(directory))));

            //Второй вариант более громоздкий
            long sizeByte = Files.walk(Paths.get(directory))
                        .filter(Files::isRegularFile)
                        .mapToLong(p -> p.toFile().length()).sum();

            //Получим степпень в которую нужно возвести 1024
            int pow = (int) (Math.log(sizeByte) / Math.log(1024));
            //Получаем размер в сокрашенном формате, размер получить не проблема, а
            // Kb Mb Gb не придумал как только условием и сделал Math.min чтоб степень не была больше 3 т.е. не больше Gb
            //преобразуем максимум в Gb
            double size = sizeByte / Math.pow(1024, Math.min(pow,3));
            String prefix = "";
            switch (pow){
                case 1 :
                    prefix = "Kb";
                    break;
                case 2 :
                    prefix = "Mb";
                    break;
                case 3 :
                    prefix = "Gb";
                    break;}

            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            System.out.println("Размер папки: " + decimalFormat.format(size) + " " + prefix);
        } catch (Exception e) {
            System.out.println("Не правильный путь к файлу");;
        }
    }
}
