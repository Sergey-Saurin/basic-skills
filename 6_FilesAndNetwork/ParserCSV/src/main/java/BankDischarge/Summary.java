package BankDischarge;

class Summary {

    double income;
    double withdraw;

    Summary(double income, double withdraw) {
        this.income = income;
        this.withdraw = withdraw;
    }

    static Summary merge(Summary s1, Summary s2) {
        return new Summary(s1.income + s2.income, s1.withdraw + s2.withdraw);
    }

    static Summary fromTransaction(Transaction t) {
        return new Summary(t.getIncome(), t.getWithdraw());
    }

}