import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class FileVisitors
{
    public static void CopyDirectory(Path copiedFolder, Path receiverFolder)
    {
        try {
            Files.walkFileTree(copiedFolder, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    Path dstSubDir = receiverFolder.resolve(copiedFolder.relativize(dir));
                    Files.createDirectories(dstSubDir);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Path dstFile = receiverFolder.resolve(copiedFolder.relativize(file));
                    Files.copy(file, dstFile);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
