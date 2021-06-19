import java.util.*;

public class StoringChangePhoneBook
{
    private TreeMap<String, String> elementCaller;
    private boolean needAddAnEntry = true;
    public StoringChangePhoneBook()
    {
        elementCaller = new TreeMap<>();
        elementCaller.put("359446", "sergey");
        elementCaller.put("72222222222", "sergey");
        elementCaller.put("73333333333", "anton");
    }

    public String addRecording(String nameEntered, String phoneEntered, boolean isNumber)
    {

        String enteredData = new Scanner(System.in).nextLine();
        //Если пользователь прервал операцию создания новой записи
        if(enteredData.compareToIgnoreCase("cancel") == 0)
        {
            needAddAnEntry = false;
            return "Ввод отменен";
        }
        //isNumber - введен номер. Переменная нужна для отличия операция ввели номер, он не найден и нужно добавить имя абонента
        //или наоборот, ввели имя и нужно добавить номер.
        if (isNumber) {
            //Нужно проверить ввели имя или опять номер
            nameEntered = enteredData.replaceAll("[^a-zA-Z ]","");
            if (nameEntered.compareTo("") != 0) {
                elementCaller.put(phoneEntered, nameEntered);
                needAddAnEntry = false;
                return"Запись добавлена";
            } else return "Некорректное имя абонента, повторите попытку. Для отмены введите \"cancel\"";
        } else {
            //Нужно проверить ввели номер или опять имя
            phoneEntered = enteredData.replaceAll("[^\\d]","");
            if (phoneEntered.compareTo("") != 0) {
                elementCaller.put(phoneEntered, nameEntered);
                needAddAnEntry = false;
                return "Запись добавлена";
            } else return "Некорректный формат телефона, проверьте номер телефона. Для отмены введите \"cancel\"";
        }
    }


    public <K, V extends Comparable<? super V>> Map<K, V> phoneBookSortByValue() {
        Map<K, V> map = (Map<K, V>) elementCaller;
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }


    public String searchBySubscriber(String searchValue, boolean isNumber)
    {
        if (isNumber){

            if (elementCaller.containsKey(searchValue)) {
                return "" + elementCaller.get(searchValue) + " " + searchValue;
            } else {
                return "Номер не найден. Введите имя абонета для записи";
            }
        }else
        {
            StringBuilder stringBuilder = new StringBuilder();
            boolean haveName = false;
            for (Map.Entry<String, String> keyNumber : elementCaller.entrySet())
            {
                if (searchValue.compareToIgnoreCase(keyNumber.getValue()) == 0)
                {
                    stringBuilder.append(keyNumber.getValue()).append(" ").append(keyNumber.getKey()).append("\n");
                    haveName = true;
                }
            }
            if (haveName) {
                return stringBuilder.toString();
            } else {
                return "Абонент не найден. Введите номер абонета для записи";
            }
        }
    }

    public boolean getNeedAddAnEntry(){
        return needAddAnEntry;
    }
    public void setNeedAddAnEntry(){
        needAddAnEntry = true;
    }
}

