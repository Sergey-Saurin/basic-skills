import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main
{
    public static void main(String[] args)
    {
        while (true){
            Client clientAccount = null;
            while (clientAccount == null) {
                //Подключимся к счету
                System.out.println("Подключитесь к счету, для введите номер счета \"123\", \"456\" или \"789\": ");
                int accountNumber = new Scanner(System.in).nextInt();
                clientAccount = connectToAccount(accountNumber);
            }

            while (true) {
                String operationStatus;
                System.out.println("Введите команду: ");
                String userCommand = new Scanner(System.in).nextLine().trim();

                Pattern pattern;
                Matcher matcher;

                String stringPattern = "(?<command>take|replenish|balance|info|cancel)\\s?(?<sum>\\-*\\d*+(\\.\\d+)?)";
                pattern = Pattern.compile(stringPattern, Pattern.CASE_INSENSITIVE);
                matcher = pattern.matcher(userCommand);

                if (matcher.find()) {
                    //Преобразуем сумму с double
                    double summa = 0.0;
                    if (matcher.group("sum").compareTo("") != 0) {
                        summa = Double.parseDouble(matcher.group("sum"));
                        if (summa < 0.0) {
                            System.out.println("Invalid command. Try again");
                            continue;
                        }
                    }

                    if (matcher.group("command").compareToIgnoreCase("balance") == 0) {
                        System.out.println("Balance account: " + clientAccount.getBalance());

                    } else if (matcher.group("command").compareToIgnoreCase("take") == 0) {
                        operationStatus = clientAccount.withdrawFunds(summa);
                        System.out.println(operationStatus);

                    } else if (matcher.group("command").compareToIgnoreCase("replenish") == 0) {
                        operationStatus = clientAccount.topUpBalance(summa);
                        System.out.println(operationStatus);

                    }else if (matcher.group("command").compareToIgnoreCase("info") == 0) {
                        System.out.println(clientAccount.getAccountInformation());

                    } else if (matcher.group("command").compareToIgnoreCase("cancel") == 0) {
                        break;
                    }
                }

            }
        }
    }

    private static Client connectToAccount(int accountNumber)
    {
        //Все параметры передаются, подразумевается что они взяты с какой-то формы для ввода даных или с какого-то справочника
        //Была бы форма можно было подключиться по одной строке кода, без switch, уже жду этих уроков))))
        switch (accountNumber){
            case 123 :
                return new ClientIndividual(123, 10000.0);
            case 456 :
                return new ClientEntity(456, 200000.0);
            case 789 :
                return new ClientEntrepreneur(789, 700000.0);
        }
        return null;
    }
}
