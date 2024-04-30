package net.jgp.books.spark.ch08.lab320_ingestion_partitioning;

import java.util.Properties;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

/**
 * MySQL injection to Spark, using the Sakila sample database.
 */
public class MySQLToDatasetWithoutPartitionApp {

    public static void main(String[] args) {
        MySQLToDatasetWithoutPartitionApp app = new MySQLToDatasetWithoutPartitionApp();
        app.start();
    }

    private void start() {
        SparkSession spark = SparkSession.builder()
                .appName("MySQL to Dataframe using JDBC without partitioning")
                .master("local")
                .getOrCreate();

        // Using properties.
        Properties props = new Properties();
        props.put("user", "root");
        props.put("driver", "com.mysql.cj.jdbc.Driver");
        props.put("password", "84E4sC2G9g!");
        props.put("useSSL", "false");

        // Let's look for all movies matching the query.
        Dataset<Row> df = spark.read().jdbc("jdbc:mysql://localhost:3306/sakila", "film", props);

        // Displays the dataframe and some of its metadata.
        df.show(5);
        df.printSchema();
        System.out.println("The dataframe contains " + df.count() + " record(s).");
        System.out.println("The dataframe is split over " + df.rdd().getPartitions().length + " partition(s).");
    }
}
