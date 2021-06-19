import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame();
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setSize(400, 300);

        frame.setIconImage(ImageIO.read(new File("DesktopApp/image/human.png")));

        frame.add(new MainForm().getMainPanel());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
