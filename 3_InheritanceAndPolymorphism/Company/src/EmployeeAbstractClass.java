import java.util.Random;

public abstract class EmployeeAbstractClass implements Employee
{
    protected Company company;
    protected String employeeName;
    protected int salary;
    protected int salesAmount;

    public EmployeeAbstractClass(Company company)
    {
        this.company = company;

    }

    @Override
    public int getMonthSalary() {
        return salary;
    }

    //Сделал разными методы получения рандомного числа доход по сотруднику в классе Manager (getEmployeeIncomeForCompany), так как этот метод только менеджеру нужен
    //чтоб не тянулся в другие классы сотрудников
    // и рандомное число для имени сотрудника(getRandomIntForNameEmployee)
    //Хотел сделать один метод и передавать туда параметры maxIncome и minIncome, но по-моему разными методами правильнее
    //Так как по смыслу и назначению они координально отличаются, хотя код внутри методов одинаковый
    protected int getRandomIntForNameEmployee()
    {
        int minIncome = 100;
        int maxIncome = 1000;
        Random random = new Random();
        return minIncome + random.nextInt(maxIncome - minIncome);
    }
    public int getSalesAmount() {
        return salesAmount;
    }
}
