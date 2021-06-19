package score;

public class BankAccount
{
    //Оставил переменные protected и private для вопроса. С private всё понятно, получаем и изменяем геттером и сеттером соответственно
    //а protected для каких случаев нужен, ведь если мы создадим объект BankAccount в другой папке мы не сможем обратиться, т.е. в любом случаи
    //нужен геттер и сеттер, какого тогда назначение protected?
    protected double balance;

    public BankAccount()
    {

    }

    public double getBalance() {
        return balance;
    }

    public EnumOperationStatus withdrawAmount(double amount) {
        //ВОПРОС
        //Как-то не очень красиво тут два таких условия, вернуть разные значения нужно, нет ли каких-нибудь методов которые подойдут в такой ситуации
        //switch не пойдет, тернарная тоже не пойдет, а много "if" писать думаю не очень правильно, этот тут два, а если больше?
        if (balance < amount)
        {
            return EnumOperationStatus.INSUFFICIENT_FUNDS;
        }
        if (amount < 0.0)
        {
            return EnumOperationStatus.WRONG_AMOUNT;
        }
        balance = balance - amount;
        return EnumOperationStatus.OPERATION_SUCCESSFULLY;

    }
    public EnumOperationStatus topUpBalance(double amount) {
        if (amount < 0.0)
        {
            return EnumOperationStatus.WRONG_AMOUNT;
        }
        balance = balance + amount;
        return EnumOperationStatus.OPERATION_SUCCESSFULLY;
    }


    public EnumOperationStatus send(BankAccount receiver, BankAccount source, double amount)
    {
        EnumOperationStatus operationStatus;
        operationStatus = source.withdrawAmount(amount);
        if (operationStatus == EnumOperationStatus.OPERATION_SUCCESSFULLY) {
            //Ограничений на пополнение нет никаких, поэтому проверка не нужно, вернеться true
            return receiver.topUpBalance(amount);
        } else return operationStatus;

    }
}
