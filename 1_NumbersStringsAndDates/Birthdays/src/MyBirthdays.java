import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.DataFormatException;

public class MyBirthdays
{
    public static void main(String[] args)
    {
        DateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy");
        //В видео было сказано, задание со звездочкой вывести дня на русском, если сокращенно, вместо ЕЕЕЕ просто Е
        DateFormat formatDayOfWeek = new SimpleDateFormat("EEEE");
        Calendar birthday = Calendar.getInstance();
        //Дата дня рождения
        //Почему .set если передать месяц числом 11-ноябрь, тогда возвращается 12-декабрь?
        birthday.set(1988, Calendar.NOVEMBER, 23);
        //Текущий год
        Calendar thisYear = Calendar.getInstance();
        //Сколько лет (итераций) для цикла
        int age = thisYear.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);

        for (int i = 0; i <= age; i++)
        {
            //Сравнение дат, если в этом году не наступил день рождения, не выводить.
            if (thisYear.getTime().before(birthday.getTime()))
            {
                break;
            }
            System.out.println(i + " - " + formatDate.format(birthday.getTime()) + " - " + formatDayOfWeek.format(birthday.getTime()));
            birthday.add(Calendar.YEAR, 1);
        }

    }
}
