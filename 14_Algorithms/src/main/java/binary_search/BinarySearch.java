package binary_search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BinarySearch
{
    private ArrayList<String> list;

    public BinarySearch(ArrayList<String> list)
    {
        this.list = list;
    }

    public int search(String query)
    {
        return search(query, 0, list.size() - 1);
    }

    private int search(String query, int from, int to)
    {
        //Не нашел способа проверить отсортирован ли список, как только перебирать весь список и сравнивать значения
        //Думаю лучше сразу на входе в метод отсортировать в любом случаи
        //так как проверка переборов все равно отнимет время, лучше сразу отсортировать
        Collections.sort(list);

        if (list.size() == 0 || (from == 0 && to == 0))
        {
            return -1;
        }
        int middle = (from + to) / 2;
        int comparison = query.compareTo(list.get(middle));

        if (comparison == 0)
        {
            return middle;
        }
        if (comparison > 0)
        {
            return search(query, middle, to);
        }
        if (comparison < 0)
        {
            return search(query, from, middle);
        }
        return -1;
    }
}




















