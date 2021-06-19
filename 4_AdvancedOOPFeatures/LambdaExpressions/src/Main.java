import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;



public class Main
{
    private static String staffFile = "data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args)
    {
        ArrayList<Employee> staff = loadStaffFromFile();

        //Использовал Calendar, так как при создании new Date(год,месяц,число), Date становится зачеркнутым,
        //прочитал что это значит не рекомендуется так писать код, хоть он и будет работать(считается устаревшим)
        //так как есть Calendar
        Calendar calendar = new GregorianCalendar(2017,Calendar.JANUARY,1);
        Date dateStart = calendar.getTime();
        calendar.add(1, 1);
        Date dateEnd = calendar.getTime();

        Optional<Employee> employeeOptional = staff.stream()
                .filter(d -> d.getWorkStart().after(dateStart) && d.getWorkStart().before(dateEnd))
                .max(Comparator.comparing(Employee::getSalary));
        //Проверка существует ли запись, чтоб не выкидовало в ошибку если ложь
        if (employeeOptional.isPresent())
        {
            Employee employeeMaxSalary = employeeOptional.get();
            System.out.println(employeeMaxSalary);
        }


    }

    private static ArrayList<Employee> loadStaffFromFile()
    {
        ArrayList<Employee> staff = new ArrayList<>();
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for(String line : lines)
            {
                String[] fragments = line.split("\t");
                if(fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(
                    fragments[0],
                    Integer.parseInt(fragments[1]),
                    (new SimpleDateFormat(dateFormat)).parse(fragments[2])
                ));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return staff;
    }
}