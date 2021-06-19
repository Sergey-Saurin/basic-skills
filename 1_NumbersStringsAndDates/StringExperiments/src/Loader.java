import java.text.DecimalFormat;
import java.util.Scanner;

public class Loader {
    public static void main(String[] args) {
        System.out.println("Введите номер задания:" +
                "\n\t1-посчитать сумму заработка всех друзей." +
                "\n\t2-разбить текст по словам и вывести в консоль." +
                "\n\t3-вывести ФИО отдельно в нужном формате с регулярными выражениями." +
                "\n\t4-проверка соответствия номера формату мобильных номеров России." +
                "\n\t5-задание со звездочкой.");
        Scanner scanner = new Scanner(System.in);
        int jobNumber = scanner.nextInt();
        if (jobNumber == 1) {
            //Задание 1
            calculateTheAmountOfEarnings();
        } else if (jobNumber == 2) {
            //Задание 2
            breakTextIntoWords();
        } else if (jobNumber == 3) {
            //Задание 3
            displayNameFormat();
        } else if (jobNumber == 4) {
            //Задание 4
            correspondenceToTheFormatOfMobileNumbers();
        } else if (jobNumber == 5) {
            //Задание со звездочкой
            hideConfidentialData();
        }
    }

    //Задание 1
    //==============================================================================================
    static void calculateTheAmountOfEarnings() {
        //Указал одну сумму с копейками, так ближе к реальности
        String text = "Вася заработал 5000.61 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        double totalEarnings = 0.0;
        //Не знаю можно ли было использовать массив, но если нет то пришлось бы городить
        // строки кода как в предыдущем задании с расчетом заработка
        //Оставляем символ "точка", так как заработок может быть с копейками
        String[] textNumber = text.replaceAll("[^\\d. ]", "").replaceAll(" +", " ").trim().split("\\ ");
        for (int i = 0; i < textNumber.length; i++) {
            totalEarnings += Double.parseDouble(textNumber[i]);
        }
        DecimalFormat formatD = new DecimalFormat("##.00");
        System.out.println("Общая сумма заработка: " + formatD.format(totalEarnings) + " руб.");
    }
    //Задание 2
    //==============================================================================================
    static void breakTextIntoWords() {
        String text = "Although she assiduously avoided the limelight during her lifetime, over the last decade Maier has become one of the biggest names in photography. A Flickr showcase of her archive attracted vast numbers of visitors, and the physical exhibitions that followed in Europe and North America were mobbed. In 2013, she became the subject of an Oscar-nominated documentary, which retold Maloof’s efforts to locate traces of the nanny-with-a-secret-passion-for-photography. With a sad and somehow American inevitability, Maier even became the subject of a bitter posthumous legal battle over who owned the rights to show and profit from her work. The artist herself, who died nearly penniless after selling everything she had, was, of course, not around to protest – or benefit";
        //метод replaceAll(" +", " ") необходим, так как в тексте встречаются числа, при удалении чисел между словами будет по несколько пробелов
        //в задании сказано только про слова поэтому числа не стал оставлять, если было бы нужно оставить числа, то добавил "\\d"
        String[] words = text.replaceAll("[^a-zA-Z ]", "").replaceAll(" +", " ").trim().split("\\ ");
        for (int i = 0; i < words.length; i++) {
            System.out.println(words[i]);
        }


    }
    //Задание 3
    //==============================================================================================
    static void displayNameFormat() {
        System.out.println("Введите последовательно ФИО:");
        Scanner scanner = new Scanner(System.in);
        String fullName  = scanner.nextLine();
        String[] fullNameArray = fullName.replaceAll("[^a-zA-Zа-яёА-ЯЁ ]", "").replaceAll(" +", " ").trim().split("\\ ");
        int fullNameArrayLength = fullNameArray.length;

        //Проверка на наличие первого индекса в массиве не нужна, так как ввод через консоль в любом случаи будет одно значение
        //Отладку ещё не изучали, не смог понять какое значение в fullNameArray[0], если ничего не вводить в консоль, а просто нажать Enter
        //проверил пустую строку и null, не они, думаю просить рассказать как это проверить рано, позже изучим
        System.out.println("Фамилия: " + fullNameArray[0] +
                "\nИмя: " + (fullNameArrayLength > 1 ? fullNameArray[1] : "not indicated") +
                "\nОтчество: " + (fullNameArrayLength > 2 ? fullNameArray[2] : "not indicated"));

    }

    static void correspondenceToTheFormatOfMobileNumbers()
    {
        System.out.println("Введите номер телефона:");
        Scanner scanner = new Scanner(System.in);
        String numberMobile = "";
        //Сразу обработаем введенную строку чтоб код был компактнее,
        //Обрабатывать полученные данные из консоли можно в любом случаи, так как вернться тип строка nextLine()
        String enteredDataOnlyNumbers  = scanner.nextLine().replaceAll("[^\\d]","").trim();
        int enteredDataOnlyNumbersLength = enteredDataOnlyNumbers.length();
        String firstSymbol = enteredDataOnlyNumbers.substring(0,1);

        /**StringBuilder builder = new StringBuilder(); создаем внутри условий, в случаи если неверный формат
        *зря не создавать класс, тем самым не грузить систему.
        *Создание класса сильно грузит систему, в этом случаи правильно в условия создание класса внес? */

        //Если 10 чифр, значит в любом случаи правильный формат, только добавить первую цифру 7, поэтому первый условием
        if(enteredDataOnlyNumbersLength == 10)
        {
            StringBuilder builder = new StringBuilder();
            numberMobile = builder.append("7").append(enteredDataOnlyNumbers).toString();
        // Во втором условии номер пройдет только 11 символов и первый символ 7 или 8
        }else if(enteredDataOnlyNumbersLength == 11 && (firstSymbol.compareTo("7") == 0 || firstSymbol.compareTo("8") == 0))
        {
            StringBuilder builder = new StringBuilder();
            //Вопрос: То что я несколько методов помещаю в одну строку это нормально? Такое практикуется в коде?
            numberMobile = (firstSymbol.compareTo("8") == 0 ? builder.append("7").append(enteredDataOnlyNumbers.substring(1)).toString() : enteredDataOnlyNumbers);
        //Другой строки с корректным номером не существует
        }else{
            numberMobile = "Неверный формат номера";
        }
        System.out.println(numberMobile);
    }

    //Задание со звездочкой
    //==============================================================================================
    static void hideConfidentialData()
    {
        System.out.println("Введите номер кредитной карты, укажите часть текста символами \"<...>\" который необходимо скрыть: ");
        while(true)
        {
            Scanner scanner = new Scanner(System.in);
            String text = scanner.nextLine();
            int value1 = text.indexOf("<");
            int value2 = text.indexOf(">");
            //Проверку на пробелы между символами делать не стал, по заданию вроде всё сделано
            if ((value1 == -1 || value2 == -1) || ((value1 + 1) == value2))
            {
                System.out.println("Запрещено оставлять весь текст открытым, проверьте текст между символами \"<...>\":");
                continue;
            } else {
                String safe = searchAndReplaceDiamonds(text, "***");
                System.out.println(safe);
                break;
            }
        }
    }

    static String searchAndReplaceDiamonds(String text, String placeholder)
    {
        String safe = text.replaceAll("\\<.*\\ .*>", placeholder);

        return safe;
    }

}










