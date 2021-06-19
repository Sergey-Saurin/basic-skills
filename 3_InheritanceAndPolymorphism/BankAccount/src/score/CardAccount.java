package score;
import java.text.DecimalFormat;

public class CardAccount extends BankAccount
{
    public CardAccount() {
        super();
    }

    @Override
    public EnumOperationStatus withdrawAmount(double amount) {
        EnumOperationStatus operationStatus;
        operationStatus = super.withdrawAmount(amount);

        if (operationStatus.equals(EnumOperationStatus.OPERATION_SUCCESSFULLY)){
            //Указал жестко что коммиссия 1 процент списания
            double commission = amount * 0.01;
            this.balance = this.balance - commission;
        }
        return operationStatus;
        }

}
