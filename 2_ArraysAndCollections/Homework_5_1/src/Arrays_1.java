import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

public class Arrays_1 {


    final static int QUANTITY_PATIENTS = 30;
    final static float MIN_TEMPERATURE = 32.0F;
    final static float MAX_TEMPERATURE = 40.0F;
    final static float MIN_HEALTHY_PATIENT_TEMPERATURE = 36.2F;
    final static float MAX_HEALTHY_PATIENT_TEMPERATURE = 36.9F;

    public static void main(String[] args)
    {

        System.out.println("Введите номер задания:" +
                "\n\t1-обратный массив" +
                "\n\t2-посчитать сумму заработка всех друзей" +
                "\n\t3-двумерный массив");

        Scanner scanner = new Scanner(System.in);
        int jobNumber  = scanner.nextInt();
        if(jobNumber == 1) {
            //Задание 1
            reverseArray();
        }else if (jobNumber == 2){
            //Задание 2
            hospital();
        }else if (jobNumber == 3){
            //Задание со звездочкой
            twoDimensionalArray();
        }

    }
    static void reverseArray()
    {
        String text = "Каждый охотник желает знать, где сидит фазан";
        String[] arrayString = text.split(",?\\s+");
        int arrayStringLength = arrayString.length;
        for (int i = arrayStringLength - 1; i >= 0; i--)
        {
            System.out.println(arrayString[i]);
        }
    }

    static void hospital()
    {
        //Создадим массив
        Float[] patiens = fillPatientTemperature();

        //Переменная для подсчета здоровых пациентов
        int healthyPatients = 0;

        /**Вывести строкой температуры. Искал какой-нибудь метод что можно было обойтись без цикла
        не нашел и из массива выгрузить все значения в строку. Такие методы как понял будут в дальнейших
        уроках в других классах, не массив, верно понял?*/

        //Среднее значение температур
        double mean = 0.0;
        /**Переменная для преобразования float в double для вывода в формат два знака после запятой
        С типом float этого не получилось, float вообще можно привести в нужный формат?
        Помню задания про max min значения float, так пока до конца не осознал как их применять.*/
        double dFormat = 0.0;
        //Заполним значения массива

        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < QUANTITY_PATIENTS; i++)
        {
            dFormat = (double) patiens[i];
            //Собираем строку для вывода температур
            stringBuilder.append(decimalFormat.format(dFormat)).append(" ");
            //Среднее значение температур, позже разделим на количество пациентов
            mean += dFormat;
            //условия для счетчика здоровых пациентов
            if (dFormat >= MIN_HEALTHY_PATIENT_TEMPERATURE && dFormat <= MAX_HEALTHY_PATIENT_TEMPERATURE)
            {
                healthyPatients++;
            }
        }

        System.out.println("Температуры пациентов: " + stringBuilder.toString() +
                            "\nСредняя температура: " + decimalFormat.format(mean / QUANTITY_PATIENTS) +
                            "\nКоличество здоровых: " + healthyPatients);

    }

    static Float[] fillPatientTemperature()
    {
        Float[] patiens = new Float[QUANTITY_PATIENTS];
        for (int i = 0; i < QUANTITY_PATIENTS; i++)
        {   //Формула рассчета температур в заданном диапазоне
            patiens[i] = (float) Math.random() * (MAX_TEMPERATURE - MIN_TEMPERATURE) + MIN_TEMPERATURE;
        }
        return patiens;
    }

    static void twoDimensionalArray()
    {
        String[][] cross= new String[7][7];
      /*  {
        {"X", " ", " ", " ", " ", " ", "X"},
        {" ", "X", " "," ", " ", "X", " "},
        {" ", " ", "X", " ", "X", " ", " "},
        {" ", " ", " ", "X", " ", " ", " "},
        {" ", " ", "X", " ", "X", " ", " "},
        {" ", "X", " "," ", " ", "X", " "},
        {"X", " ", " ", " ", " ", " ", "X"},
        };*/

        //Заполниь массив
        for (int i = 0; i < cross.length; i++)
        {
            for (int l = 0; l < cross[i].length; l++)
            {
                //i == l - диагональ слева на право
                /**l == (cross[i].length -1 -i) - диагональ справа на лево
                не знаю на сколько важно, используется cross[i].length, [i]-чтоб не было ошибки если второй массив больше первого
                 необходимо вычесть 1 так как length начинается с 1, нам нужен индекс с нуля, получилась i-- во втором условии
                 */
                if (i == l || (l == (cross[i].length -1 -i)))
                {
                    cross[i][l] = "X";
                }else cross[i][l] = " ";

            }
        }

        //Первый вариант как учили в уроке
        for (int i = 0; i < cross.length; i++)
        {
            StringBuilder stringBuilder = new StringBuilder();

            for (int l = 0; l < cross[i].length; l++)
            {
                stringBuilder.append(cross[i][l]);
            }
            System.out.println(stringBuilder.toString());
        }
        System.out.println();//Перенос строки
        //Второй вариант, по-моему так лучше
        for (int i = 0; i < cross.length; i++)
        {
            String line = Arrays.toString(cross[i]).replaceAll("[^X ]","");
            System.out.println(line);
        }

    }
}

















