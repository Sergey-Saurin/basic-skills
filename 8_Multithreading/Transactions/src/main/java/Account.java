import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class Account
{
    private AtomicLong money = new AtomicLong();
    private Integer accNumber;
    private AtomicBoolean locked = new AtomicBoolean();

    public Account(long moneySet, Integer accNumber) {
        money.addAndGet(moneySet);
        this.accNumber = accNumber;
    }

    public void topUpAnAccount(long amount) {

        if (amount > 0) {//Проверка если сумма отрицательна
            money.getAndAdd(amount);
        }
    }

    public boolean withdraw(long amount) {
        //Проверка если сумма отрицательна или не хватает на балансе
        if (amount > 0 && money.get() >= amount) {
            money.getAndAdd((amount * -1));
            return true;
        }
        return false;
    }
    public long getMoney() {
        return money.get();
    }
    public void block() {
        locked.set(true);
    }

    public boolean isLocked() {
        return locked.get();
    }

}
