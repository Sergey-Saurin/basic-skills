import java.util.Comparator;

public class EmployeeComparator implements Comparator<EmployeeAbstractClass>
{
    @Override
    public int compare(EmployeeAbstractClass o1, EmployeeAbstractClass o2)
    {
        Integer salary1 = o1.getMonthSalary();
        Integer salary2 = o2.getMonthSalary();
        return salary1.compareTo(salary2);
    }
}
