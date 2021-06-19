import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        DataBaseMongoDB dataBaseMongoDB = new DataBaseMongoDB("localhost",27017);

        while (true) {
            System.out.println("Enter the command: ");

            Scanner scanner = new Scanner(System.in);
            String userCommand = scanner.nextLine().trim();

            if (userCommand.equalsIgnoreCase("statistics")) {
                System.out.println(dataBaseMongoDB.getStatistic());

            } else {
                String[] commands = userCommand.split(" ");
                if (commands.length == 2 && commands[0].equalsIgnoreCase("add_shop")) {
                    dataBaseMongoDB.addShop(commands[1]);
                } else if (commands.length == 3 && commands[0].equalsIgnoreCase("add_product")) {
                    dataBaseMongoDB.addProduct(commands[1], Integer.parseInt(commands[2]));
                } else if (commands.length == 3 && commands[0].equalsIgnoreCase("add_product_in_shop")) {
                    dataBaseMongoDB.addProductInShop(commands[1], commands[2]);
                } else {
                    System.out.println("incorrect input, try again");
                }
            }
        }
    }
}
