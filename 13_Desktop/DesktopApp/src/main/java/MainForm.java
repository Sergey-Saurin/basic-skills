import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class MainForm
{
    private JPanel MainPanel;
    private JTextField surname;
    private JTextField name;
    private JTextField patronymic;
    private JPanel panelName;
    private JPanel panelSurname;
    private JPanel panelPatronymic;
    private JButton button;
    private JLabel patronymicLabel;
    private JLabel surnameLabel;
    private JLabel nameLabel;
    private JTextField fullName;
    private JPanel panelFullName;


    public JPanel getMainPanel()
    {
        return MainPanel;
    }

    public MainForm()
    {
        addActionButton();

    }

    private void addActionButton()
    {
        button.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {

                if (button.getText().equals("Collapse")) {
                    if (name.getText().equals("")) {
                        JOptionPane.showMessageDialog(MainPanel, "Fill name","ERROR", JOptionPane.PLAIN_MESSAGE);
                    }else if (surname.getText().equals("")){
                        JOptionPane.showMessageDialog(MainPanel, "Fill surname","ERROR", JOptionPane.PLAIN_MESSAGE);
                    }else {
                        executeCollapse();
                        button.setText("Expand");
                    }

                }else if (button.getText().equals("Expand"))
                {
                    String[] fullNameArray = fullName.getText().split("\\s+");
                    if (fullNameArray.length < 2)
                    {
                        JOptionPane.showMessageDialog(MainPanel, "Fill name and surname","ERROR", JOptionPane.PLAIN_MESSAGE);
                    }else {
                        executeExpand(fullNameArray);
                        button.setText("Collapse");
                    }
                }

            }
        });
    }

    private void executeCollapse()
    {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(name.getText()).append(" ").append(surname.getText()).append(" ").append(patronymic.getText());

        //Уберем с формы отдельный поля и обнулим значения +++
        panelName.setVisible(false);
        name.setText("");

        panelSurname.setVisible(false);
        surname.setText("");

        panelPatronymic.setVisible(false);
        patronymic.setText("");
        //Уберем с формы отдельный поля ----

        fullName.setText(strBuilder.toString());
        panelFullName.setVisible(true);
    }

    private void executeExpand(String[] fullNameArray)
    {
        panelName.setVisible(true);
        name.setText(fullNameArray[0]);

        panelSurname.setVisible(true);
        surname.setText(fullNameArray[1]);

        //имя и фамилия без проверки, потому что проверка actionPerformed
        if (fullNameArray.length > 2) {
            patronymic.setText(fullNameArray[2]);
        }
        panelPatronymic.setVisible(true);

        fullName.setText("");
        panelFullName.setVisible(false);
    }


}
