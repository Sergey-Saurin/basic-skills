package BankDischarge;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;

public class CSVReaderBankDischarge
{
    private static List<String[]> allLines = new ArrayList<>();

    public static List readBankStatement() {
        //Считываем файл
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader("files/movementList.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        String[] lineCSV = null;
        while (true)
        {
            //Попытка считать строку csv
            try {
                if ((lineCSV = reader.readNext()) == null)
                    break;
            } catch (Throwable e) {
                e.printStackTrace();
            }

            if (lineCSV != null)
            {
                //Проверим длину строки, если меньше 8 значений не добавляем
                //хотя в жизни наверное нужно прерывать выполнение и кому-то сообщить что файл не корректный, но зависит от задачи
                //Или писать в логи какие строки пропущены
                if (lineCSV.length == 8) {
                    //В файлу числа с двумя знаками после запятой, содержат ту самую запятую, чтоб преобразовать в double
                    //меняем запятую на точку
                    //Буду очень признателен если расскажите почему инода используют точку, а иногда запятую в числа с сотыми
                    lineCSV[6] = lineCSV[6].replace(",", ".");
                    lineCSV[7] = lineCSV[7].replace(",", ".");
                    //Попытка преобразовать число в double, Если число не получается преобразовать в double тогда строка не добавится,
                    //Первая строка из файла с названиями колонок удалится в любом случаи, она там не нужна
                    //хотя в жизни наверное нужно прерывать выполнение и кому-то сообщить что файл не корректный, но зависит от задачи, или писать в логи какие строки пропущены
                    if (isNumericDouble(lineCSV[6]) && isNumericDouble(lineCSV[7])) {
                        allLines.add(lineCSV);
                    }
                }
            }
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allLines;
    }

    private static boolean isNumericDouble(String str)
    {
        try
        {
            double d = parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}
