import java.util.Scanner;
import java.util.Set;

public class ListMailsClass
{
    static String command;
    static String caseTitle;
    public static void main(String[] args)
    {   String description = "";

        while(true)
        {
            if(description.compareTo("") != 0)
            {
                System.out.println(description);
                description = "";
            }
            System.out.println("\nВведите команду: ");
            Scanner scanner = new Scanner(System.in);
            String userCommand = scanner.nextLine().trim();

            readUserCommand(userCommand);

            if (command.compareToIgnoreCase("list") == 0)
            {
                if (caseTitle.compareTo("") != 0){
                    description = "Некорректна введена коменда, для вывода списка введите команду list";
                    continue;
                }
                Set<String> data = EmailRepository.getData();
                for (String email : data)
                {
                    System.out.println(email);
                }
                continue;
            }else if (command.compareToIgnoreCase("add") == 0) {
                description = EmailRepository.addEmail(command, caseTitle);
            }else description = "Некорректна введена коменда";

        }
    }

    static void readUserCommand(String userCommand)
    {
        String[] arrayCommand = userCommand.split("\\s+",3);
        command = arrayCommand[0];
        caseTitle = "";

        if (arrayCommand.length > 1)
        {
            caseTitle = arrayCommand[1];
        }
    }
}




