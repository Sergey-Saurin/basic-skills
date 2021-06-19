import java.util.*;

public class MainCompany {

    public static void main(String[] args)
    {
        Company company = new Company();
        //Сделал 4 переменные чтоб программа не ругалась что что в одну переменную два раза записываются данные
        List<EmployeeAbstractClass> topSalaryList;
        List<EmployeeAbstractClass> lowSalaryList;
        List<EmployeeAbstractClass> topSalaryList2;
        List<EmployeeAbstractClass> lowSalaryList2;
        //Примем по одному сотруднику
        hireOneEmployee(company);

        //Примем список сотрудников
        hireToListEmployee(company);

        //Сделал возврат List, час наверное потратил, не мог понять почему после выполнения "lowSalaryList = company.getLowestSalaryStaff(30);" выполнилось вторым
        //"topSalaryList = company.getTopSalaryStaff(15);" - выполнилось первым, выпадает в ошибку, сразу и не понял что из-за сортировки повторной в getTopSalaryStaff(15).
        //Как понял, всё значения которые вернулись в "topSalaryList" и "lowSalaryList" - это и есть company.staff просто выведено в интервале указанных индексов, правильно?
        //Выведем 15 самых большых зарплат
        topSalaryList = company.getTopSalaryStaff(15);

        //Выведем 30 самых маленьких зарплат
        lowSalaryList = company.getLowestSalaryStaff(30);

        //Уволить 50 процентов персонала
        fireEmployee(company);

        //Выведем 15 самых большых зарплат
        topSalaryList2 = company.getTopSalaryStaff(15);

        //Выведем 30 самых маленьких зарплат
        lowSalaryList2 = company.getLowestSalaryStaff(30);

    }
 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static void fireEmployee(Company company)
    {
        EnumOperationStatus status;
        status = company.fire(50);
        System.out.println(status.getTextMessage());
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Метод к лассе консоли так как создается объект сотрудник, в классе Company нельзя создавать объект сотрудник
    //так как класс Company будет зависеть от классов сотрудников. А нужно максимально независимые классы
    //Правильный ход мысли?
    public static void hireToListEmployee(Company company)
    {
        List<EmployeeAbstractClass> staffList = new ArrayList<>();
        EmployeeAbstractClass employee = null;
        //По заданию нанять 80 менеджеров
        for(int i = 80; i > 0; i--)
        {
            employee = EmployeeFactory.create(EmployeeType.MANAGER, company);
            staffList.add(employee);
        }

        //По заданию нанять 180 операторов
        for(int i = 180; i > 0; i--)
        {
            employee = EmployeeFactory.create(EmployeeType.OPERATOR, company);
            staffList.add(employee);
        }

        //По заданию нанять 10 топ менеджеров
        for(int i = 10; i > 0; i--)
        {
            employee = EmployeeFactory.create(EmployeeType.TOP_MANAGER, company);
            staffList.add(employee);
        }

        company.hireAll(staffList);

    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static void hireOneEmployee(Company company)
    {
        EmployeeAbstractClass employee;
        //Создадим одного менеджера
        employee = EmployeeFactory.create(EmployeeType.MANAGER, company);
        company.hire(employee);

        //Создадим одного оператора
        employee = EmployeeFactory.create(EmployeeType.OPERATOR, company);
        company.hire(employee);

        //Создадим одного Top manager
        employee = EmployeeFactory.create(EmployeeType.TOP_MANAGER, company);
        company.hire(employee);

    }

}
