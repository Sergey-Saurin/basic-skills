package double_linked_list;

public class DoubleLinkedList
{
    private ListItem head;
    private ListItem tail;

    public ListItem getHeadElement()
    {
        return head;
    }

    public ListItem getTailElement()
    {
        return tail;
    }

    public ListItem popHeadElement()
    {
        //TODO
        ListItem item = head;

        if (item != null) {
            ListItem nextItem = item.getNext();
            if (nextItem != null) {
                nextItem.setPrev(null);
                head = nextItem;
            }
            item.setNext(null);
        }
        return item;
    }

    public ListItem popTailElement()
    {
        //TODO
        ListItem item = tail;

        if (item != null) {
            ListItem prevItem = item.getPrev();
            if (prevItem != null) {
                prevItem.setNext(null);
                tail = prevItem;
            }
            item.setPrev(null);
        }
        return item;
    }

    public void removeHeadElement()
    {
        //TODO
        ListItem item = head;

        if (item != null) {
            ListItem nextItem = item.getNext();
            if (nextItem != null) {
                nextItem.setPrev(null);
                item.setNext(null);
                head = nextItem;
            }
        }
    }

    public void removeTailElement()
    {
        //TODO
        ListItem item = tail;

        if (item != null) {
            ListItem prevItem = item.getPrev();
            if (prevItem != null) {
                prevItem.setNext(null);
                item.setPrev(null);
                tail = prevItem;
            }
        }
    }

    public void addToHead(ListItem item)
    {
        //TODO
        if (head != null) {
            head.setPrev(item);
            item.setNext(head);
            //На всякий случай, сделаем item.setPrev(null) не известно какое значение Prev у переданного Item
            item.setPrev(null);
            head = item;
        }else{
            head = item;
        }
    }

    public void addToTail(ListItem item)
    {
        //TODO
        if (tail != null) {
            tail.setNext(item);
            item.setPrev(tail);
            //На всякий случай, сделаем item.setNext(null); не известно какое значение next у переданного Item
            item.setNext(null);
            tail = item;
        }else{
            tail = item;
        }
    }
}