import org.imgscalr.Scalr;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class StreamsRunnable implements Runnable
{
    private File file;
    private int newWidth;
    private String dstFolder;
    private long start;

    public StreamsRunnable(File file, int newWidth, String dstFolder,long start) {
        this.file = file;
        this.newWidth = newWidth;
        this.dstFolder = dstFolder;
        this.start = start;

    }
    @Override
    public void run() {

        try {
            BufferedImage image = ImageIO.read(file);
            BufferedImage scaledImage = Scalr.resize(image, Scalr.Method.QUALITY, newWidth);
            File newFile = new File(dstFolder + "/" + file.getName());
            ImageIO.write(scaledImage, "jpg", newFile);
         } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }
}
