import java.util.ArrayList;

public class WorkingWithAnArray
{
    private ArrayList<String> listBusiness;

    public WorkingWithAnArray()
    {
        listBusiness = new ArrayList<>();
    }

    public String setCommandList(UserCommand userCommand)
    {
        StringBuilder stringBuilder = new StringBuilder();
        if (checkingCorrectnessOfCommand(false, false, userCommand))
        {
            return "Некорректно введена команда. Для вывода списка введите команду \"LIST\"";
        }
        for (int i = 0; i < listBusiness.size(); i++) {
            stringBuilder.append(i + " - " + listBusiness.get(i) + "\n");
        }
        return stringBuilder.toString();
    }
    public String setCommandAdd(UserCommand userCommand)
    {
        if (!userCommand.getHaveCaseTitle()) {
            return "Некорректно введена команда. Для добавления записи, укажите название.";
        }
        //Проверка, введенный индекс не превыщает size() если превышает, просто добавляем в конец списка
        //и введен ли вообще индекс, если нет тоже в конец списка
        int indexAdd = (userCommand.getHaveIndexCommand() && userCommand.getIndexCommand() <= listBusiness.size()) ? userCommand.getIndexCommand() : listBusiness.size();
        listBusiness.add(indexAdd, userCommand.getCaseTitle());
        return "Добавлен элемент: " + indexAdd + " - " + userCommand.getCaseTitle();

    }
    public String setCommandEdit(UserCommand userCommand)
    {
        //Тут обязателен и индекс и название, индекс не должен быть больше arrayList.size()
        if (checkingCorrectnessOfCommand(true, true,userCommand) || userCommand.getIndexCommand() >= listBusiness.size())
        {
            return "Некорректно введена команда. Для изменения записи, укажите существующий индекс и новое название";//укажем, так как в ошибку может попасть индекс больше списка
        }
        listBusiness.set(userCommand.getIndexCommand(), userCommand.getCaseTitle());
        return "Изменен элемент: " + userCommand.getIndexCommand() + " - " + userCommand.getCaseTitle();

    }
    public String setCommandDelete(UserCommand userCommand)
    {
        if (userCommand.getHaveIndexCommand() && userCommand.getIndexCommand() <= listBusiness.size())
        {
            listBusiness.remove(userCommand.getIndexCommand());
            return "Удален элемент: " + userCommand.getIndexCommand();
        }else if (!userCommand.getHaveIndexCommand())
        {
            listBusiness.clear();
            return "Список полностью очищен";
        }else
            return "Не корректно введена команда. Укажите существующий индекс.";

    }

    private boolean checkingCorrectnessOfCommand(boolean needHaveIndexCommand, boolean needHaveCaseTitle,UserCommand userCommand)
    {
        //проверяем правильно ли ввели команду
        //needHaveIndexCommand - нужен ли индекс, например для команды LIST он не нужен, а пользователь его ввел, значит где-то ошибся
        //needHaveCaseTitle - нужно ли название дела, аналогично
        if (needHaveIndexCommand == userCommand.getHaveIndexCommand() && needHaveCaseTitle == userCommand.getHaveCaseTitle())
        {
            return false;
        }else
            return true;
    }
}
