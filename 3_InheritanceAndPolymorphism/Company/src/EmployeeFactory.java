public class EmployeeFactory
{

    public static EmployeeAbstractClass create(EmployeeType type, Company company)
    {
        EmployeeAbstractClass employee = null;
        switch (type) {
            case MANAGER:
                employee = new Manager(company);
                break;
            case OPERATOR:
                employee = new Operator(company);
                break;
            case TOP_MANAGER:
                employee = new TopManager(company);
                break;
        }
        return employee;
    }
}
