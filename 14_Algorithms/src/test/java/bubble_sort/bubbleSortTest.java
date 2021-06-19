package bubble_sort;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class bubbleSortTest {

    @Test
    public void sortBubbleTest(){
        //Длина 10, самое маленькое 4, самое большое 504
        int[] list = new int[]{103, 202, 504, 100, 8, 15, 35, 74, 4, 68};
        BubbleSort.sort(list);

        assertEquals(4, list[0]);
        assertEquals(504, list[9]);
    }

}
