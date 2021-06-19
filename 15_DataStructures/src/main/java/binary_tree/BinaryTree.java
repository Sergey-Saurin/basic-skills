package binary_tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree
{
    private Node root;
    List<Node> listNode;

    public void addNode(int data)
    {
        //TODO
        root = addRecursive(root, data, null);
    }

    public List<Node> searchNodes(int data)
    {
        //TODO
        listNode = new ArrayList<>();
        searchNodeRecursive(root, data);
        return listNode;
    }

    private Node addRecursive(Node current, int data, Node parent) {
        if (current == null) {
            return new Node(data, parent);
        }

        if (data < current.getData()) {
            current.setLeft(addRecursive(current.getLeft(), data, current));
        } else if (data >= current.getData()) {
            current.setRight(addRecursive(current.getRight(), data, current));
        } else {
            return current;
        }

        return current;
    }

    private void searchNodeRecursive(Node current, int data) {

        if (current != null)
        {
            if (data < current.getData()){
                searchNodeRecursive(current.getLeft(), data);
            }else if (data >= current.getData())
            {
                searchNodeRecursive(current.getRight(), data);
            }
            if (data == current.getData()) {
                listNode.add(current);
            }
        }
    }

    public Node getRoot() {
        return root;
    }
}