package single_linked_list;

public class SingleLinkedList
{
    private ListItem top;

    public void push(ListItem item)
    {
        if(top != null) {
            item.setNext(top);
        }
        top = item;
    }

    public ListItem pop()
    {
        ListItem item = top;
        if(top != null)
        {
            top = top.getNext();
            item.setNext(null);
        }
        return item;
    }

    public void removeTop()
    {
        if(top != null) {
            top = top.getNext();
        }
    }

    public ListItem removeLast()
    {
        if (top == null)
        {
            return null;
        }
        ListItem item = top;
        //два раза .getNext() чтоб item был предпоследним элементом и его сделаем последним
        while(item.getNext().getNext() != null) {
            item = item.getNext();
        }

        item.setNext(null);
        return item;
    }
}
















