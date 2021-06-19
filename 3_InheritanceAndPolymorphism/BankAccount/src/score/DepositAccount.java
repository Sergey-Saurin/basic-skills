package score;
import java.time.LocalDate;

public class DepositAccount extends BankAccount
{
    private LocalDate replenishmentDate;

    public DepositAccount() {
        super();
    }

    @Override
    public EnumOperationStatus topUpBalance(double amount) {
        EnumOperationStatus operationStatus;
        operationStatus = super.topUpBalance(amount);
        replenishmentDate = LocalDate.now().plusMonths(1);
        return operationStatus;
    }

    @Override
    public EnumOperationStatus withdrawAmount(double amount) {
        EnumOperationStatus operationStatus;
        //Если null значит ни разу не пополняли т.е. нет средств
        if (replenishmentDate == null)
        {
            operationStatus = EnumOperationStatus.INSUFFICIENT_FUNDS;

        }else if (replenishmentDate.isBefore(LocalDate.now())) {

            operationStatus = super.withdrawAmount(amount);

        }else operationStatus = EnumOperationStatus.FORBIDDEN_TO_REMOVE;

        return operationStatus;
    }
}
