public class ClientEntity extends Client
{
    double commission = 1.0;

    public ClientEntity(int accountNumber, double balance) {
        super(accountNumber, balance);
        accountInformation = "Withdrawal with a commission of " + commission +"%";
    }

    @Override
    public String withdrawFunds(double amount) {
        if (balance < amount)
        {
            return EnumOperationStatus.INSUFFICIENT_FUNDS.TEXT_MESSAGE;
        }else {
            balance = balance - (amount + (commission/100 * amount));
            return EnumOperationStatus.OPERATION_SUCCESSFULLY.TEXT_MESSAGE;
        }
    }


}
