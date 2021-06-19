package quick_sort;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest{

    @Test
    public void sortQuickTest() {
        //Длина 10, самое маленькое 2, самое большое 504
        int[] list = new int[]{103, 202, 504, 100, 8, 15, 35, 74, 2, 68};
        QuickSort.sort(list, 0, 9);

        assertEquals(2, list[0]);
        assertEquals(504, list[9]);
    }

}