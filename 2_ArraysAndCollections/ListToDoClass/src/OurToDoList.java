import java.util.Scanner;

public class OurToDoList
{

    public static void main(String[] args)
    {
        String messagesToUser = "";
        System.out.println("Введите команду, при необходиомсти через пробел индекс:\n" +
                            "\tLIST    — выводит дела с их порядковыми номерами\n" +
                            "\tADD     — добавляет дело в конец списка или дело на определённое место, сдвигая остальные дела вперёд, если указать номер \n" +
                            "\tEDIT    — заменяет дело с указанным номером\n" +
                            "\tDELETE  — удаляет");
        //Создал как объект, выполнение команд, обращаться через сеттеры
        WorkingWithAnArray workingWithArray = new WorkingWithAnArray();
        while(true)
        {
            System.out.println(messagesToUser);

            Scanner scanner = new Scanner(System.in);
            String textUserCommand = scanner.nextLine().trim();
            if (textUserCommand.compareTo("") == 0)
            {
                messagesToUser = "Введите команду.";
                continue;
            }

             UserCommand userCommand = new UserCommand(textUserCommand);

            //Проверка если ввели не правильную команду
             if (userCommand.getCommand() == null)
             {
                 messagesToUser = getTextError();

             }else if (userCommand.getCommand().compareToIgnoreCase("list") == 0)
             {
                 messagesToUser = workingWithArray.setCommandList(userCommand);

             }else if (userCommand.getCommand().compareToIgnoreCase("add") == 0)
             {
                 messagesToUser = workingWithArray.setCommandAdd(userCommand);

             }else if (userCommand.getCommand().compareToIgnoreCase("edit") == 0)
             {
                 messagesToUser = workingWithArray.setCommandEdit(userCommand);

             }else if (userCommand.getCommand().compareToIgnoreCase("delete") == 0)
             {
                 messagesToUser = workingWithArray.setCommandDelete(userCommand);

             }
        }
    }

    static String getTextError()
    {
        return "Некорректно введена команда, примеры: \n" +
                "\tLIST,\n" +
                "\tADD \"индекс строки при необходимости\" \"Название дела обязательно\",\n" +
                "\tEDIT \"индекс строки обязательно\" \"Новое название дела обязательно\",\n" +
                "\tDELETE \"индекс строки\". Если индекс не указан, список будет очищен.";
    }
}











