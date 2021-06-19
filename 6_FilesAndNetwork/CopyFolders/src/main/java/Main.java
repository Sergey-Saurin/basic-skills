import java.nio.file.*;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        while (true) {
            System.out.println("Выберите папку для копирования");
            String directoryCopy = new Scanner(System.in).nextLine().trim();
            Path copiedFolder = Paths.get(directoryCopy);

            System.out.println("Укажите каталог приемник для сохранения");
            String directoryPast = new Scanner(System.in).nextLine().trim();
            Path receiverFolder = Paths.get(directoryPast);

            FileVisitors.CopyDirectory(copiedFolder, receiverFolder);

        }
    }
}
