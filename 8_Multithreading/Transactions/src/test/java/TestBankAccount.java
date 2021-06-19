import junit.framework.TestCase;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestBankAccount extends TestCase
{
    Bank bank;
    HashMap<Integer, Account> customerAccounts;
    @Override
    protected void setUp()
    {
        customerAccounts = new HashMap<Integer, Account>();
        int n = 1;
        //Создвется 100 счетов
        for (int i = 0; i < 100; i++)
        {
            //номера счетов, конечно реальные счета не такие короткие от 1, но для тестов само то
            int accountNumber = n++;
            long money = (long) (Math.random() * 1000000);
            customerAccounts.put(accountNumber, new Account(money,accountNumber));
        }
        bank = new Bank(customerAccounts);

    }

    public void testTransfer() throws InterruptedException {
        //Проверка чтоб суммы по всему банку совпали до выполнения переводов и после
        long actual = bank.getWholeBalance();

        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 1; i < 100; i++)
        {
            RunnableTest testRun = new RunnableTest(i,i + 1,bank);
            executor.submit(testRun);
        }
        executor.awaitTermination(1, TimeUnit.SECONDS);
        System.out.println("Всё прошло");
        long expected = bank.getWholeBalance();
        assertEquals(expected, actual);
    }

}


