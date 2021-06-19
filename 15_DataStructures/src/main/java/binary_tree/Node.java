package binary_tree;

public class Node
{
    private int data;

    private Node parent;
    private Node left;
    private Node right;

    //TODO: create and implement methods

    public Node(int data, Node parent) {
        this.data = data;
        this.parent = parent;
    }
    public int getData() {
        return data;
    }


    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }


}