package BankDischarge;

public class Transaction
{
    double income;
    double withdraw;
    String description;
    public Transaction(double income, double withdraw, String description)
    {
        this.income = income;
        this.withdraw = withdraw;
        this.description = description;
    }

    public double getIncome() {
        return income;
    }

    public double getWithdraw() {
        return withdraw;
    }

    public String getDescription() {
        return description;
    }
}
