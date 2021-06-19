package BankDischarge;
import java.util.List;
import java.util.stream.Collectors;

public class Main
{
    public static void main(String[] args)
    {
        Discharge discharge = new Discharge();

        System.out.println("Сумма расходов: " + discharge.getSumExpenses() + " руб.");
        System.out.println("Сумма дохода: " + discharge.getSumIncome() + " руб.");

        //Детальные расходы
        System.out.println(String.join("\n", discharge.getDetailExpenses()));

        List<Transaction> transactions =  discharge.getTransactions();
        transactions.stream()
                .collect(
                        Collectors.groupingBy(Transaction::getDescription,
                        Collectors.mapping(Summary::fromTransaction,
                        Collectors.reducing(Summary::merge))))
                        .forEach((s, summ) -> System.out.println(s + "\nДоход:" + summ.get().income + " руб. \tРасход:" + summ.get().withdraw + "руб."));

    }
}















