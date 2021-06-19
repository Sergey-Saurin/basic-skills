package single_linked_list_test;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import single_linked_list.ListItem;
import single_linked_list.SingleLinkedList;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class SingleLinkedListTest {

    @Test
    @DisplayName("Удалить хвост, односвязный список")
    public void removeLastTest(){
        ListItem item1 = new ListItem(new Date().toString());
        ListItem item2 = new ListItem(new Date().toString());
        ListItem item3 = new ListItem(new Date().toString());
        ListItem item4 = new ListItem(new Date().toString());

        item1.setNext(item2);
        item2.setNext(item3);
        item3.setNext(item4);

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.push(item1);
        ListItem itemNowLast =singleLinkedList.removeLast();

        assertEquals(item3 , itemNowLast);
    }
}
