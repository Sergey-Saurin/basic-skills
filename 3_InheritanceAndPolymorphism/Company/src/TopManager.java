public class TopManager extends EmployeeAbstractClass
{

    public TopManager(Company company)
    {
        super(company);
        employeeName = "Top manager " + getRandomIntForNameEmployee();
        salary = 150000;
    }

    @Override
    public int getMonthSalary() {
        int companyBonusForIncomeMore = 10000000;
        int percentageSales = 150;
        return salary + (companyBonusForIncomeMore < company.getIncomeCompany() ? (salary * percentageSales / 100) : 0);
    }
}
