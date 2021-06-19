package merge_sort;

public class MergeSort
{
    public static void mergeSort(int[] array)
    {
        int n = array.length;
        if(n < 2) {
            return;
        }
        int middle = n / 2;
        int[] leftArray = new int[middle];
        int[] rightArray = new int[n - middle];

        for (int i = 0; i < middle; i++) {
            leftArray[i] = array[i];
        }
        for (int i = middle; i < n; i++) {
            rightArray[i - middle] = array[i];
        }
        mergeSort(leftArray);
        mergeSort(rightArray);

        merge(array, leftArray, rightArray, middle,n - middle);
    }

    private static void merge(int[] array, int[] leftArray, int[] rightArray, int left, int right)
    {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (leftArray[i]<= rightArray[j]) {
                array[k++]= leftArray[i++];
            }
            else {
                array[k++]= rightArray[j++];
            }
        }
        while (i < left) {
            array[k++]= leftArray[i++];
        }
        while (j < right) {
            array[k++]= rightArray[j++];
        }
    }
}
