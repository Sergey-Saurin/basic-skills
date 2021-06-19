public class Operator extends EmployeeAbstractClass
{

    public Operator(Company company)
    {
        super(company);
        employeeName = "Operator " + getRandomIntForNameEmployee();
        salary = 70000;
    }
}
