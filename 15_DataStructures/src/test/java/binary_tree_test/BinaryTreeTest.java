package binary_tree_test;

import binary_tree.BinaryTree;
import binary_tree.Node;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;


public class BinaryTreeTest extends TestCase
{
    BinaryTree binaryTree= new BinaryTree();;


    @Override
    protected void setUp()
    {
        binaryTree.addNode(8);
        binaryTree.addNode(55);
        binaryTree.addNode(85);
        binaryTree.addNode(7);
        binaryTree.addNode(2);
        //Равные значения
        binaryTree.addNode(3);
        binaryTree.addNode(3);
        binaryTree.addNode(45);
        binaryTree.addNode(45);

    }

    public void testAddNode(){

        assertEquals(binaryTree.getRoot().getLeft().getLeft().getData() , 2);
    }

    public void testSearchNodes(){
        List<Node> listNode;
        listNode = binaryTree.searchNodes(3);

        assertEquals(listNode.size() , 2);
    }

}
