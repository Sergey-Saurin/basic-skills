import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.util.Arrays;
import java.util.regex.Pattern;

//docker run -ti -p 4040:4040 -p 8020:8020 -p 8032:8032 -p 8088:8088 -p 9000:9000 -p 10020:10020
// -p 19888:19888 -p 50010:50010 -p 50020:50020 -p 50070:50070 -p 50075:50075 -p 50090:50090 harisekhon/hadoop

public class MainSpark {
    private static final Pattern SPACE = Pattern.compile("\\s+");

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: JavaWordCount <in_file> <out_file>");
            System.exit(1);
        }

        SparkSession spark = SparkSession
                .builder()
                .config("spark.master", "local")
                .appName("JavaWordCount")
                .getOrCreate();

        JavaPairRDD<String, Integer> counts = spark.read().textFile(args[0]).javaRDD()
                .flatMap(s -> Arrays.asList(SPACE.split(s))
                        .iterator()).mapToPair(s -> new Tuple2<>(s, 1))
                .reduceByKey((i1, i2) -> i1 + i2);

        counts.saveAsTextFile(args[1]);

        spark.stop();
    }
}