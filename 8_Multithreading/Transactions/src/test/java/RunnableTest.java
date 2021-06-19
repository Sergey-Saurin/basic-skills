import java.util.HashMap;

public class RunnableTest implements Runnable
{
    private Integer from;
    private Integer to;
    private Bank bank;

    public RunnableTest(Integer from, Integer to, Bank bank) {
        this.from = from;
        this.to = to;
        this.bank = bank;
    }

    @Override
    public void run() {
        long money = (long) (Math.random() * 100000);
        try {
            System.out.println("///////////// " + money);
            System.out.println(from + " from It was: " + bank.getBalance(from));
            System.out.println(to + " to It was: " + bank.getBalance(to));
            Bank.transfer(from,to,money);
            System.out.println(from + " from became: " + bank.getBalance(from));
            System.out.println(to + " to became: " + bank.getBalance(to));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
