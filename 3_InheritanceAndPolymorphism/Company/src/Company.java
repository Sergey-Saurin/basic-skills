import java.util.*;
import java.util.stream.Stream;

public class Company
{
    private List<EmployeeAbstractClass> staff = new ArrayList<>();
    private int incomeCompany;
    public Company()
    {

    }


    protected void hire(EmployeeAbstractClass employee)
    {
        staff.add(employee);
    }

    protected void hireAll(List staffList)
    {
        staff.addAll(staffList);
    }
    protected int getIncomeCompany() {
        calculateTheCompanyIncom();
        return incomeCompany;
    }

    protected void calculateTheCompanyIncom()
    {
        incomeCompany = staff.stream()
                .map(EmployeeAbstractClass::getSalesAmount)
                .reduce(Integer::sum).get();
    }


    protected EnumOperationStatus fire(int toFirePercentEmployee)
    {
        if (toFirePercentEmployee > 100 || toFirePercentEmployee < 0)
        {
            return EnumOperationStatus.ERROR;
        }
        //Перемещаем случайно, для увольнения 50% персонала
        Collections.shuffle(staff);
        //Посчитаем сколько сотрудников нужно уволить
        int count = staff.size() * toFirePercentEmployee / 100;
        for (int i = 0; i < count; i++)
        {
            //Индекс всегда ноль, так как список случайно и не важно кого уволить
            //вторая причина индекса всегда ноль, если индекс изменять и удалять больше 50% сотрудников, например использовать "i" будет ошибка
            //индекс за границами массива, так как список уменьшается, соответсвенно и индексы
            staff.remove(0);
        }

        return EnumOperationStatus.OPERATION_SUCCESSFULLY;
    }

    protected void sortStaffBySalary()
    {
        staff.sort(new EmployeeComparator());
    }

    protected List<EmployeeAbstractClass> getTopSalaryStaff(int count)
    {
        if (staff.size() < count || count < 0)
        {
            return null;
        }else {
            sortStaffBySalary();
            int staffSize = staff.size();
            return staff.subList(staffSize - count, staffSize);
        }
    }

    protected List<EmployeeAbstractClass> getLowestSalaryStaff(int count)
    {
        if (staff.size() < count || count < 0)
        {
            return null;
        }else {
            sortStaffBySalary();
            return staff.subList(0, count);
        }
    }

}
