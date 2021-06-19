package suffix_tree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main
{
    public static void main(String[] args) {
        BufferedReader reader = null;
        try {
            reader= new BufferedReader(new FileReader("res/text.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String text = null;
        try {
            text = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SuffixTree suffixTree = new SuffixTree(text);
        suffixTree.build();
        suffixTree.visualize();
        List<Node> nodeList = suffixTree.getNodes();
        List<Integer> positions = suffixTree.search("с ним Илья общаться встречается; также  ");

        StringBuilder stringBuilder = new StringBuilder();
        for (Integer pos : positions)
        {
            stringBuilder.append(nodeList.get(pos).getFragment());
        }

        System.out.println(stringBuilder.toString());

    }
}
