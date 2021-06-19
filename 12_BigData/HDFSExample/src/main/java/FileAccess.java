import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.util.Progressable;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileAccess
{
    private Configuration configuration;
    private FileSystem hdfs;
    private String rootPath;
    /**
     * Initializes the class, using rootPath as "/" directory
     *
     * @param rootPath - the path to the root of HDFS,
     * for example, hdfs://localhost:32771
     */
    public FileAccess(String rootPath) throws URISyntaxException, IOException {
        configuration = new Configuration();
        configuration.set("dfs.client.use.datanode.hostname", "true");
        System.setProperty("HADOOP_USER_NAME", "root");
        this.rootPath = rootPath;

        hdfs = FileSystem.get(new URI(rootPath), configuration);


    }

    /**
     * Creates empty file or directory
     *
     * @param path
     */
    public void create(Path path) throws IOException {
        if (hdfs.exists(path))
        {
            hdfs.delete( path, true );
        }
        OutputStream os = hdfs.create(path);
        os.close();
    }

    /**
     * Appends content to the file
     *
     * @param path
     * @param content
     */
    public void appendContent(Path path, String content) throws IOException {

        if ((hdfs.exists(path) && hdfs.isFile(path))) {

            hdfs.append(path).writeChars(content);

        } else {
            System.out.println("File is not exist");
        }
    }

    /**
     * Returns content of the file
     *
     * @param path
     * @return
     */
    public String read(Path path) throws IOException {
        StringBuilder strBuilder = new StringBuilder();
        if ((hdfs.exists(path) && hdfs.isFile(path))) {
            FSDataInputStream open = hdfs.open(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(open));
            String line;
            while ((line = reader.readLine()) != null) {
                strBuilder.append(line);
            }
            open.close();
            reader.close();
        } else {
            strBuilder.append("This is not file or this file does not exist");
        }

        return strBuilder.toString();
    }

    /**
     * Deletes file or directory
     *
     * @param path
     */
    public void delete(Path path) throws IOException {
        hdfs.delete(path, true);
    }

    /**
     * Checks, is the "path" is directory or file
     *
     * @param path
     * @return
     */
    public boolean isDirectory(Path path) throws IOException {
      return hdfs.isDirectory(path);
    }

    /**
     * Return the list of files and subdirectories on any directory
     *
     * @param path
     * @return
     */
    public List<String> list(Path path) throws IOException {
        List<String> listFiles = new ArrayList<>();
        if (isDirectory(path)) {
            FileStatus[] files = hdfs.listStatus(path);

            for (FileStatus file : files) {
                listFiles.add(file.getPath().getName());
                if (file.isDirectory()) {
                    listFiles.addAll(Collections.singleton(file.getPath().toString()));
                }
            }
        }
        return listFiles;
    }

    public void closeHdfs() throws IOException {
        hdfs.close();
    }
}
