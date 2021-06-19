package score;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static EnumOperationStatus operationStatus = EnumOperationStatus.ERROR;
    private static BankAccount bankAccount = new BankAccount();
    private static DepositAccount depositAccount = new DepositAccount();
    private static CardAccount cardAccount = new CardAccount();

    public static void main(String[] args) {

        //Сделал класс команды пользователя
        UserCommand userCommand = new UserCommand();
        while (true) {
            System.out.println("Введите команду: ");
            String userCommandEntered = new Scanner(System.in).nextLine().trim();
            //обработаем введенную команду пользователем
            userCommand.userCommandProcessing(userCommandEntered);

            //Получим переменные из обработанной команды
            double summa = userCommand.getSumma();
            EnumCommand command = userCommand.getCommand();

            boolean correctCommand = userCommand.correctCommand();
            //если не правильно введена команда или сумма отрицательная
            if (!correctCommand) {
                System.out.println("Invalid command. Try again");
                continue;
            }

            operationStatus = executeCommand(command, summa);

            //Здесь выведем сообщение пользователю

            System.out.println(operationStatus.getTextMessage());
        }
    }

    private static EnumOperationStatus executeCommand(EnumCommand command, double summa)
    {
        switch (command) {
            case BALANCE:
                System.out.println(("Balance account: " + bankAccount.getBalance() +
                        "\nBalance deposit: " + depositAccount.getBalance() +
                        "\nBalance card: " + cardAccount.getBalance()));
                operationStatus = EnumOperationStatus.NO_TEXT;
                break;
            case SEND:
                System.out.println("Введите счет приемник: 1 - DepositAccount, 2 - CardAccount");
                Integer userCommandSend = new Scanner(System.in).nextInt();
                switch (userCommandSend) {
                    case 1:
                        operationStatus = bankAccount.send(depositAccount, cardAccount, summa);
                        break;
                    case 2:
                        operationStatus = bankAccount.send(cardAccount, depositAccount, summa);
                        break;
                }
                break;
            //Есл икоманда не SEND и BALANCE значит снять или пополнить
            default:
                System.out.println("Введите счет : 1 - DepositAccount, 2 - CardAccount, 3 - BankAccount");
                Integer accountNumber = new Scanner(System.in).nextInt();
                switch (accountNumber) {
                    case 1:
                        operationStatus = bankAccountOperations(depositAccount, command, summa);
                        break;
                    case 2:
                        operationStatus = bankAccountOperations(cardAccount, command, summa);
                        break;
                    case 3:
                        operationStatus = bankAccountOperations(bankAccount, command, summa);
                        break;
                }

        }
        return operationStatus;
    }

    //нужен только здесь, так как нужно создавать независимые классы, методы могут вызываться в других классах по другому
    //поэтому вызов методов по командам только здесь
    private static EnumOperationStatus bankAccountOperations(BankAccount account, EnumCommand command, double summa)
    {
        EnumOperationStatus operationStatus = EnumOperationStatus.ERROR;
        switch (command)
        {
            case TAKE:
                operationStatus = account.withdrawAmount(summa);
                break;
            case REPLENISH:
                operationStatus = account.topUpBalance(summa);
                break;
        }
        return operationStatus;

    }

}
