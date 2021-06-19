import java.util.*;
import java.util.Arrays;

public class carNumberSearch
{
    public static void main(String[] args)
    {
        long start;
        long searchTime;

        //Вот тут вопрос, в видео было сказано что бинарный поиск будет работать сразу если правильно создать ArrayList
        //с сортировкой по алфавиту последовательно в циклах, я так и сделал, но Array.binarySearch() попробовал практически все
        //Что-то я не так делал
        //пришлось делать Collections.sort(listNumbersCarArray)
        //Вопрос, как можно отсортировать в циклах ArrayList чтоб сразу заработал binarySearch()? без Collections.sort()
        ArrayList<String> listNumbersCarArray = FillInTheListOfCarNumbers.createList();
        Collections.sort(listNumbersCarArray);

        HashSet<String> listNumbersCarHashSet = new HashSet<>();
        listNumbersCarHashSet.addAll(listNumbersCarArray);

        TreeSet<String> listNumbersCarTreeSet = new TreeSet<>();
        listNumbersCarTreeSet.addAll(listNumbersCarArray);

        while (true)
        {
            System.out.println("Введите номер автомобиля для поиска");
            String numberCarToSearch = new Scanner(System.in).nextLine().trim();

            start = System.nanoTime();
            boolean numberCarFound1 = listNumbersCarArray.contains(numberCarToSearch);
            searchTime = System.nanoTime() - start;
            System.out.println("Поиск перебором:  номер " + (numberCarFound1 ? "найден" : "не найден") + ", поиск занял " + searchTime + " нс");

            start = System.nanoTime();
            int numberCarFound2 = Collections.binarySearch(listNumbersCarArray, numberCarToSearch);
            searchTime = System.nanoTime() - start;
            System.out.println("Бинарный поиск:   номер " + (numberCarFound2 >= 0 ? "найден" : "не найден") + ", поиск занял " + searchTime + " нс");

            start = System.nanoTime();
            boolean numberCarFound3 = listNumbersCarHashSet.contains(numberCarToSearch);
            searchTime = System.nanoTime() - start;
            System.out.println("Поиск в HashSet:  номер " + (numberCarFound3 ? "найден" : "не найден") + ", поиск занял " + searchTime + " нс");

            start = System.nanoTime();
            boolean numberCarFound4 = listNumbersCarTreeSet.contains(numberCarToSearch);
            searchTime = System.nanoTime() - start;
            System.out.println("Поиск в TreeSet:  номер " + (numberCarFound4 ? "найден" : "не найден") + ", поиск занял " + searchTime + " нс");
        }
    }
}
