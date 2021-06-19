import java.util.Scanner;

public class MainHumanitarianAid
{
    public static void main(String[] args)
    {
        final int containersPerTruck    = 12; //контейнеров в грузовик
        final int boxesInContainer      = 27; //ящиков в контейнер

        int numberContainer = 0; // номер контейнера
        int numberBoxes = 0; //номер ящика

        System.out.println("Введите количество ящиков");
        Scanner scanner = new Scanner(System.in);
        int boxesCount  = scanner.nextInt();

        //Всего необходимо контейнеров
        int containerCount  = (boxesCount / boxesInContainer) + (boxesCount % boxesInContainer > 0 ? 1 : 0);
        //Всего необходимо грузовиков
        int truckCount      = (containerCount / containersPerTruck) + (containerCount % containersPerTruck > 0 ? 1 : 0);

        /**первым выбран цикл for так как в верху иерархии грузовики, можно присвоить значение numberTruck = 1
         * в циклах с контейнерами и ящиками цикл while, так как нужно прерывать цикл, если занят весь объем
         * а значения numberContainer и numberBoxes менять нельзя
         */
        for (int numberTruck = 1; numberTruck <= truckCount; numberTruck++)
        {
            System.out.println("Грузовик " + numberTruck + ":");
            while (numberContainer < containerCount)
            {
                numberContainer++;
                System.out.println("\t" + "Контейнер " + numberContainer + ":");

                while (numberBoxes < boxesCount)
                {
                    numberBoxes++;
                    System.out.println("\t\t" + "Ящик " + numberBoxes + ":");
                    if (numberBoxes % boxesInContainer == 0) //Значит число кратно boxesInContainer, прерываем, так как больше не влезет
                    {
                        break;
                    }
                }
                if (numberContainer % containersPerTruck == 0) //Значит число кратно containersPerTruck, прерываем, так как больше не влезет
                {
                    break;
                }
            }
        }

        System.out.println("Необходимо: " + "\n" + "Грузовиков - " + truckCount + " шт.\n" + "Контейнеров - " + containerCount + " шт.");

    }
}
