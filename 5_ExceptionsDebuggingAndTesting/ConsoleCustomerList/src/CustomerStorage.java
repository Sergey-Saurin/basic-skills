import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerStorage
{
    private HashMap<String, Customer> storage;

    public CustomerStorage()
    {
        storage = new HashMap<>();
    }

    public void addCustomer(String data)
    {
        String[] components = data.split("\\s+");
        if (components.length != 4)
        {
            throw new IllegalArgumentException("Wrong format command");
        }
        if(!checkEmail(components[2]))
        {
            throw new IllegalArgumentException("Wrong input mail");
        }
        if(!checkPhone(components[3]))
        {
            throw new IllegalArgumentException("Wrong input phone");
        }

        String name = components[0] + " " + components[1];
        storage.put(name, new Customer(name, components[3], components[2]));
    }

    public void listCustomers()
    {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name)
    {
        storage.remove(name);
    }

    public int getCount()
    {
        return storage.size();
    }

    private boolean checkEmail(String email)
    {
        Pattern pattern;
        Matcher matcher;
        String mailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(mailPattern);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean checkPhone(String phone)
    {
        //Сделал проверку на точный формат как указано в классе консоли addCommand, "+"-обязателен, количество цифр 11
        //+79215637722
        Pattern pattern;
        Matcher matcher;
        String mailPattern = "^[\\+]+([0-9]){11}";
        pattern = Pattern.compile(mailPattern);
        matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}