
public abstract class Client
{
    protected long accountNumber;
    //В задании написано: "У каждого клиента есть сумма денег на счету (число)", число не очень подойдет, использовал double
    protected double balance;
    //Сделал разные переменные комиссия за снеятие и пополнение, по условию задачи можно было
    //одну переменную использовать, но комиссии могут быть разными за пополнение и снятие
    protected String accountInformation;

    public Client(int accountNumber, double balance)
    {
        this.accountNumber  = accountNumber;
        this.balance        = balance;

    }

    public String withdrawFunds(double amount)
    {
        if (balance < amount)
        {
            return EnumOperationStatus.INSUFFICIENT_FUNDS.TEXT_MESSAGE;
        }else {
            balance = balance - amount;
            return EnumOperationStatus.OPERATION_SUCCESSFULLY.TEXT_MESSAGE;
        }
    };

    public String topUpBalance(double amount)
    {
        balance = balance + amount;
        return EnumOperationStatus.OPERATION_SUCCESSFULLY.TEXT_MESSAGE;
    }

    public String getBalance() {
        return "Balance: " + balance;
    }
    public String getAccountInformation() {
        return accountInformation;
    }


}
