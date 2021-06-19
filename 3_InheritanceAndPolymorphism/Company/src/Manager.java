import java.util.Random;

public class Manager extends EmployeeAbstractClass
{
    public Manager(Company company)
    {
        super(company);
        employeeName = "Manager " + getRandomIntForNameEmployee();
        salary = 100000;
        //Сколько заработал менеджер для компании
        salesAmount = getEmployeeIncomeForCompany();

    }

    @Override
    public int getMonthSalary() {

        //Процент вознаграждения за продажи
        int percentageSales = 5;
        return salary + (salesAmount * percentageSales / 100);
    }

    private int getEmployeeIncomeForCompany()
    {
        int minIncome = 115000;
        int maxIncome = 140000;
        Random random = new Random();
        return minIncome + random.nextInt(maxIncome - minIncome);
    }


}
