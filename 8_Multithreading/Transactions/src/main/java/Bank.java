

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Bank
{
    private static HashMap<Integer, Account> accounts;
    private static final Random random = new Random();

    public Bank(HashMap<Integer, Account> accounts) {
        this.accounts = accounts;
    }


    public static synchronized boolean isFraud(Integer fromAccountNum, Integer toAccountNum, long amount)
        throws InterruptedException
    {
        Thread.sleep(1000);
        return random.nextBoolean();

    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (как – на ваше усмотрение)
     */
    public static void transfer(Integer fromAccountNum, Integer toAccountNum, long amount) throws InterruptedException
    {

        Account fromAcc = accounts.get(fromAccountNum);
        Account toAcc = accounts.get(toAccountNum);
        // Если сумма отрицательна, не найдены счета, отмена операции
        // или какой-то из счетов заблокирован
        if(amount < 0 || fromAcc == null || toAcc == null
                || fromAcc.isLocked() || toAcc.isLocked())
        {
            return;
        }

        if(amount > 50000)
        {
            boolean locked = isFraud(fromAccountNum, toAccountNum, amount);
            System.out.println(locked);
            if (locked){
                blockAccounts(fromAcc, toAcc);
            }

        }

        //Исключение дедлока
        if (fromAccountNum < toAccountNum) {
            synchronized (fromAcc) {
                synchronized (toAcc) {
                    boolean done = fromAcc.withdraw(amount);
                    if (done){
                        toAcc.topUpAnAccount(amount);
                    }
                }
            }
        } else  {
            synchronized (toAcc) {
                synchronized (fromAcc) {
                    boolean done = fromAcc.withdraw(amount);
                    if (done){
                        toAcc.topUpAnAccount(amount);
                    }
                }
            }
        }

    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(Integer accountNum)
    {
        return accounts.get(accountNum).getMoney();
    }

    public long getWholeBalance()
    {
        return accounts.entrySet().stream().map(Map.Entry::getValue).mapToLong(s -> s.getMoney()).sum();
    }

    private static void blockAccounts(Account fromAcc, Account toAcc)
    {
        fromAcc.block();
        toAcc.block();
    }
}
