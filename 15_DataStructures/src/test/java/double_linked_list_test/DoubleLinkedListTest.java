package double_linked_list_test;

import double_linked_list.DoubleLinkedList;
import double_linked_list.ListItem;
import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.Date;

public class DoubleLinkedListTest extends TestCase
{
    ListItem item1;
    ListItem item2;
    ListItem item3;
    ListItem item4;

    DoubleLinkedList dList1;

    @Override
    protected void setUp()
    {

        item1 = new ListItem(new Date().toString());
        item2 = new ListItem(new Date().toString());
        item3 = new ListItem(new Date().toString());
        item4 = new ListItem(new Date().toString());

        item1.setNext(item2);

        item2.setPrev(item1);
        item2.setNext(item3);

        item3.setPrev(item2);
        item3.setNext(item4);

        item4.setPrev(item3);

        dList1 = new DoubleLinkedList();
        dList1.addToHead(item1);
        dList1.addToTail(item4);

    }

    public void testPopHeadElement(){

        ListItem headElement = dList1.popHeadElement();
        assertEquals(item1 , headElement);
    }

    public void testPopTailElement(){

        ListItem headElement = dList1.popTailElement();
        assertEquals(item4 , headElement);
    }

    public void testremoveHeadElement(){

        dList1.removeHeadElement();
        assertEquals(item1.getNext(), null);
    }

    public void testremoveTailElement(){

        dList1.removeTailElement();
        assertEquals(item4.getPrev(), null);
    }


}














