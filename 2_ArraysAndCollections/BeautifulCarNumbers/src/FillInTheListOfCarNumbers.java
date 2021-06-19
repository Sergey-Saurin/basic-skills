import java.util.ArrayList;
import java.util.TreeSet;

public class FillInTheListOfCarNumbers
{
    public static ArrayList<String> createList()
    {
        ArrayList<String> listNumbersCar = new ArrayList<>();
        //Не понял немного какие номера именно "красивые" имелись в виду в задании, и зачем использовать кирилицу и латиницу
        //видимо для удобства поиска, но у меня никак не получается чуть больше 2 000 000 номеров как сказано в видео
        //получилось 3 582 000, видимо что-то не так понял
        //не стал вносить в список номера с разными цифрами, и одинаковыми буквами
        //Только разные комбинации букв с одинаковыми цифрами для всех регионов
        String[] arrayLettersEng = {"A","B","C","E","H","M","O","P","T","Y"};
        String[] arrayLettersRus = {"А","В","Е","М","Н","О","Р","С","Т","У"};

        // i - регионы
        for (int i = 1; i <= 199; i++){
            //zero для подстановки лидирующего нуля в номер региона до 10
            String zero = "";
            if (i < 10) {
                zero = "0";
            }
            //q,w,e - бууквы номера, комбинации букв должны быть все возможные из списка,
            // т.е. например номер А111АА199 на месте всех букв А, может быть любая буква, поэтому три цикла только для букв
            //Английские буквы
            for (int q = 0; q < arrayLettersEng.length; q++) {
                for (int w = 0; w < arrayLettersEng.length; w++) {
                    for (int e = 0; e < arrayLettersEng.length; e++) {
                        //l - числа номера одинаковые
                        for (int l = 111; l <= 999; l += 111) {
                            listNumbersCar.add(arrayLettersEng[q] + l + arrayLettersEng[w] + arrayLettersEng[e] + zero + i);
                        }
                    }
                }
            }
            //Русские буквы
            for (int q = 0; q < arrayLettersRus.length; q++) {
                for (int w = 0; w < arrayLettersRus.length; w++) {
                    for (int e = 0; e < arrayLettersRus.length; e++) {
                        //l - числа номера одинаковые
                        for (int l = 111; l <= 999; l += 111) {
                            listNumbersCar.add(arrayLettersRus[q] + l + arrayLettersRus[w] + arrayLettersRus[e] + zero + i);
                        }
                    }
                }
            }
        }
        return listNumbersCar;
    }
}
