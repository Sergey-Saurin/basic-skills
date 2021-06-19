import java.util.*;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBook
{
    public static void main(String[] args)
    {   boolean isNumber;
        String informUser = "";
        StoringChangePhoneBook phoneBookObject = new StoringChangePhoneBook();
        while (true)
        {
            System.out.println(informUser);
            String enteredData = new Scanner(System.in).nextLine();
            //Распечатать записи и вернуться в начало цикла
            if (enteredData.compareToIgnoreCase("list") == 0)
            {
                displayWeightList(phoneBookObject);
                informUser = "";
                continue;
            }
            String phoneEntered = enteredData.replaceAll("[^\\d]", "");
            String nameEntered = enteredData.replaceAll("[^a-zA-Z ]", "");

            if (phoneEntered.compareTo("") != 0) {
                isNumber = true;
                informUser = phoneBookObject.searchBySubscriber(phoneEntered, isNumber);
            }else if (nameEntered.compareTo("") != 0) {
                isNumber = false;
                informUser = phoneBookObject.searchBySubscriber(nameEntered, isNumber);
            }else {
                informUser = "Некорректная команда";
                continue;
            }

            //Цикл если не найдена запись, добавляет новую
            while(phoneBookObject.getNeedAddAnEntry())
            {
                System.out.println(informUser);
                informUser = phoneBookObject.addRecording(nameEntered, phoneEntered, isNumber);
            }
            phoneBookObject.setNeedAddAnEntry();//Вышли из цикла, при следующем вводе не найдено совпадение, попасть в цикл для добавления записи
        }
    }

    public static void displayWeightList(StoringChangePhoneBook phoneBookObject)
    {
        //TreeMap сортировать по значению, чтоб вывести по алфавиту список
        Map<String, String> result = phoneBookObject.phoneBookSortByValue();
        for(String key : result.keySet())
        {
            System.out.println(result.get(key) + " " + key);
        }
    }
}
