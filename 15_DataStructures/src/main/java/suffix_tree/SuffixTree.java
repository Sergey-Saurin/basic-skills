package suffix_tree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SuffixTree
{
    private String text;
    private ArrayList<Node> nodes;
    private Node root;

    public SuffixTree(String text)
    {
        this.text = text;
        nodes = new ArrayList<>();

    }

    protected void build()
    {
        //TODO
        root = new Node("", -1 );
        nodes.add(root);
        String[] wordArray = text.split(" ");

        for (String word : wordArray) {
            if (!word.equals("")) {
                addSuffix(word + " ");
            }
        }
    }

    protected List<Integer> search(String query)
    {
        ArrayList<Integer> positions = new ArrayList<>();
        //TODO
        List<Node> nodesArray = searchNode(query, root);

        nodesArray.forEach(node -> positions.add(node.getPosition()));

        return positions;

    }

    private void addSuffix(String suf) {
        int n = 0;
        int i = 0;
        while (i < suf.length()) {
            char b = suf.charAt(i);
            List<Integer> children = nodes.get(n).getNextNodes();
            int x2 = 0;
            int n2;
            while (true) {
                if (x2 == children.size()) {
                    n2 = nodes.size();
                    Node node = new Node(suf.substring(i),n2);
                    nodes.add(node);
                    children.add(n2);
                    return;
                }
                n2 = children.get(x2);
                if (nodes.get(n2).getFragment().charAt(0) == b) break;
                x2++;
            }

            String sub2 = nodes.get(n2).getFragment();
            int j = 0;
            while (j < sub2.length()) {
                if (suf.charAt(i + j) != sub2.charAt(j)) {
                    int n3 = n2;

                    n2 = nodes.size();
                    Node node = new Node(sub2.substring(0, j),n2);

                    node.getNextNodes().add(n3);
                    nodes.add(node);
                    nodes.get(n3).setFragment(sub2.substring(j));
                    nodes.get(n).getNextNodes().set(x2, n2);
                    break;
                }
                j++;
            }
            i += j;
            n = n2;
        }
    }

    private List<Node> searchNode(String query, Node startNode) {
        List<Node> nodesArray = new ArrayList<>();
        if (startNode.getNextNodes().size() == 0)
        {
            startNode = root;
        }

        for (Integer child : startNode.getNextNodes()) {
            Node currentNode = nodes.get(child);

            String nodeText = currentNode.getFragment();
            //Проверка на всякий случай, хотя nodeText.length() == 0 встретиться маловероятно
            if (nodeText.length() == 0) {
                continue;
            }
            if (query.charAt(0) == nodeText.charAt(0)) {
                if (query.length() <= nodeText.length()) {
                    nodesArray.add(currentNode);
                    return nodesArray;
                }

                //compareLength, нужна при рекурсивном вызове метода, с какого символа начинать поиск в паттерне,
                //предыдущие найдены
                int compareLength = Math.min(nodeText.length(), query.length());
                //Проверяем второй символ
                for (int j = 1; j < compareLength; j++) {
                    if (query.charAt(j) != nodeText.charAt(j)) {
                        nodesArray.add(currentNode);
                        return nodesArray;
                    }
                }

                nodesArray.add(currentNode);
                List<Node> nodes2 = searchNode(query.substring(compareLength), currentNode);
                if (nodes2.size() > 0) {
                    nodesArray.addAll(nodes2);
                }
                return nodesArray;
            }
        }
        return nodesArray;
    }

    /**Методы не по заданию, для вывода дерева
     * для понимания, */
    public void visualize() {
        if (nodes.isEmpty()) {
            System.out.println("<empty>");
            return;
        }
        visualize_f(0, "");


    }

    private void visualize_f(int n, String pre) {
        List<Integer> children = nodes.get(n).getNextNodes();
        if (children.isEmpty()) {
            System.out.println("- " + nodes.get(n).getFragment() + " " + nodes.get(n).getPosition());
            return;
        }
        System.out.println("┐ " + nodes.get(n).getFragment()+ " " + nodes.get(n).getPosition());
        for (int i = 0; i < children.size() - 1; i++) {
            Integer c = children.get(i);
            System.out.print(pre + "├─");
            visualize_f(c, pre + "│ ");
        }

        System.out.print(pre + "└─");
        visualize_f(children.get(children.size() - 1), pre + "  ");

    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

}