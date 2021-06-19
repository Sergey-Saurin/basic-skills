package score;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserCommand {

    private EnumCommand command;
    private double summa;

    public UserCommand()
    {

    }

    public void userCommandProcessing(String userCommandEntered)
    {
        command = null;
        summa = 0.0;
        Pattern pattern;
        Matcher matcher;

        String stringPattern = "(?<command>take|replenish|balance|send)\\s?(?<sum>\\-*\\d*+(\\.\\d+)?)";
        pattern = Pattern.compile(stringPattern, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(userCommandEntered);

        if (matcher.find()) {

            switch (EnumCommand.valueOf(matcher.group("command").toUpperCase())) {
                case TAKE:
                    command = EnumCommand.TAKE;
                    break;
                case REPLENISH:
                    command = EnumCommand.REPLENISH;
                    break;
                case BALANCE:
                    command = EnumCommand.BALANCE;
                    break;
                case SEND:
                    command = EnumCommand.SEND;
                    break;
                default:

            }

            //Преобразуем сумму с double
            if (matcher.group("sum").compareTo("") != 0) {
                summa = Double.parseDouble(matcher.group("sum"));
            }
        }

    }

    public EnumCommand getCommand() {
        return command;
    }

    public double getSumma() {
        return summa;
    }

    public boolean correctCommand() {
        //Сразу посчитаем команду корректной, если условие ниже выполнится, значит ошибка
        boolean correct = true;

        if(command == null)//enum значение  не получено
        {
            correct = false;
        }
        return correct;
    }
}


