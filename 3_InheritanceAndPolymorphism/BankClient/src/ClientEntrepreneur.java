public class ClientEntrepreneur extends Client
{
    //Сумма при которой комиссия будет ниже, больше суммы и комиссия 0.5%
    final double REDUCTION_OF_COMMISSION_IN_AMOUNT = 1000.0;
    double commission = 1.0;

    public ClientEntrepreneur(int accountNumber, double balance) {
        super(accountNumber, balance);
        accountInformation = "Replenishment with a commission of " + commission + "%, if the amount is less than 1000 rubles. And with a commission of " + (commission / 2) + "%, if the amount is greater than or equal to 1000 rubles";
    }
    @Override
    public String topUpBalance(double amount) {
        //Переменная для динамического процента, если сумма больше 100 уменьшаем процент в два раза
        int reduceCommission = 100;
        if (amount >= REDUCTION_OF_COMMISSION_IN_AMOUNT){
            reduceCommission = 200;
        }
        balance = balance + (amount - (commission/reduceCommission * amount));
        return EnumOperationStatus.OPERATION_SUCCESSFULLY.TEXT_MESSAGE;
    }
}
