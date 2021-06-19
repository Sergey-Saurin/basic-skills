package BankDischarge;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;

public class Discharge
{

    private List<String[]> allDischarge;

    public Discharge()
    {
        allDischarge = CSVReaderBankDischarge.readBankStatement();
    }

    public double getSumExpenses()
    {
        return allDischarge.stream().mapToDouble(d -> Double.parseDouble(d[7])).filter(d -> d > 0.0).sum();
    }

    public double getSumIncome()
    {
        return allDischarge.stream().mapToDouble(d -> Double.parseDouble(d[6])).filter(d -> d > 0.0).sum();
    }

    public List<String> getDetailExpenses()
    {
        Object[] listExpensesStream = new ArrayList<>(allDischarge).stream().filter(d -> parseDouble(d[7]) > 0.0).toArray();
        //Создадим новый массив и вернем его, в него добавим записи о расходах и сумму расхода
        List<String> list = new ArrayList<>();
        list.add("\nСуммы расходов по организациям:");
        for (int i = 0; i < listExpensesStream.length; i++)
        {                                                                                 // \t не разделяет, написал 4 пробела, это в принципе и есть знак табуляции
            list.add(list.size() ,((String[])listExpensesStream[i])[5].split("    ")[1].trim() + " - " + ((String[])listExpensesStream[i])[7] + " руб.");
        }

        return list;
    }

    public List<Transaction> getTransactions()
    {
        List<Transaction> transactions = new ArrayList<>();

        for (int i = 0; i < allDischarge.size(); i++)
        {
            double income = Double.parseDouble(allDischarge.get(i)[6]);
            double withdraw = Double.parseDouble(allDischarge.get(i)[7]);
            String counterparty = allDischarge.get(i)[5].split("    ")[1].trim();
            Transaction transaction = new Transaction(income, withdraw, counterparty);
            transactions.add(transaction);
        }
        return transactions;
    }
}
