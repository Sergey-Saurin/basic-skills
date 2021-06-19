package quick_sort;

public class QuickSort
{
    public static void sort(int[] array)
    {
        if(array.length <= 1) {
            return;
        }
        sort(array, 0, array.length - 1);
    }

    public static void sort(int[] array, int from, int to)
    {
        if(from < to)
        {
            int pivot = partition(array, from, to);
            sort(array, from, pivot - 1);
            sort(array, pivot + 1, to);
        }
    }

    private static int partition(int[] array, int from, int to)
    {
        if (from <= to)
        {
            int i = from;
            int j = to;
            int temp = i - (i - j) / 2;
            while (i < j) {
                while (i < temp && (array[i] <= array[temp]))
                {
                    i++;
                }
                while (j > temp && (array[temp] <= array[j]))
                {
                    j--;
                }
                if (i < j) {
                    int temp2 = array[i];
                    array[i] = array[j];
                    array[j] = temp2;
                    if (i == temp) {
                        temp = j;
                    }else if (j == temp) {
                        temp = i;
                    }
                }
            }
            return temp;
        }
        return 0;
    }
}
