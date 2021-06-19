public class Main
{
    public static void main(String[] args)
    {
        Container container = new Container();
        container.count += 12345;
        //Не описано нужно было объект создавать или можно просто число передать,
        //сделал и так и так

        int number1 = sumDigits(container.count);
        int number2 = sumDigits(10);
        int number3 = sumDigits(5059191);
        System.out.println("12345 = " + number1 + "\n" +
                "10 = " + number2 + "\n" +
                "5059191 = " + number3);
    }

    public static int sumDigits(Integer number)
    {
        //@TODO: write code here
        int totalSum = 0;
        String numberString = number.toString();
        int lengthNumberString = numberString.length();

        for (int i = 0; i != lengthNumberString; i++)
        {
            char numberIndex = numberString.charAt(i);
            totalSum =  totalSum + Character.getNumericValue(numberIndex);
        }
        return totalSum;
    }
}
