package merge_sort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {

    @Test
    public void mergeSortTest() {
        //MergeSort.mergeSort(array);
        //Длина 10, самое маленькое 2, самое большое 504
        int[] list = new int[]{103, 202, 504, 100, 8, 15, 35, 74, 2, 68};
        MergeSort.mergeSort(list);
        assertEquals(2, list[0]);
        assertEquals(504, list[9]);
    }
}