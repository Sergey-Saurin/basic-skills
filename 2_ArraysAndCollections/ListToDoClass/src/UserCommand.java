import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class UserCommand
{
    private String command;
    private boolean haveCaseTitle;
    private boolean haveIndexCommand;
    private int indexCommand;
    private String caseTitle;


    public UserCommand(String textUserCommand)
    {
        readUserCommand(textUserCommand);
    }

    public void readUserCommand(String userCommand)
    {
        Pattern pattern;
        Matcher matcher;

        String stringPattern= "(?<command>ADD|EDIT|LIST|DELETE|add|edit|list|delete)\\s?(?<index>\\d*)\\s?(?<text>.*)";
        pattern = Pattern.compile(stringPattern);
        matcher = pattern.matcher(userCommand);

       if (matcher.find()) {
           command = matcher.group("command");
           if (matcher.group("index").compareTo("") != 0) {
               indexCommand = Integer.parseInt(matcher.group("index"));
               haveIndexCommand = true;
           }
           if (matcher.group("text").compareTo("") != 0) {
               caseTitle = matcher.group("text");
               haveCaseTitle = true;
           }
       }
    }

    public String getCommand()
    {
        return command;
    }
    public boolean getHaveCaseTitle()
    {
        return haveCaseTitle;
    }
    public boolean getHaveIndexCommand()
    {
        return haveIndexCommand;
    }
    public int getIndexCommand()
    {
        return indexCommand;
    }
    public String getCaseTitle()
    {
        return caseTitle;
    }
}
