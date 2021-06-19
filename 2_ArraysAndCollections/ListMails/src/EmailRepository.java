import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailRepository
{
    static TreeSet<String> data = new TreeSet<>();

    public static String addEmail(String command, String caseTitle)
    {
        boolean correctEmail = checkEmail(caseTitle);
        if (correctEmail) {
            data.add(caseTitle);
            return "Добавлен элемент: " + caseTitle;
        } return "Некорректна введен почтовый ящик, пример: hello@skillbox.ru";
    }

    static boolean checkEmail(String caseTitle)
    {
        //Загуглил разрешенные символы в адресе почтового ящика, чтоб ничего не забыть
        //и вот решение такое попалось, про паттерны "подиагонали" почитал, более точно просмотрю бонус урок позже.
        Pattern pattern;
        Matcher matcher;

        String mailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(mailPattern);
        matcher = pattern.matcher(caseTitle);

        return matcher.matches();
    }

    public static Set<String> getData()
    {
        return data;
    }
}
