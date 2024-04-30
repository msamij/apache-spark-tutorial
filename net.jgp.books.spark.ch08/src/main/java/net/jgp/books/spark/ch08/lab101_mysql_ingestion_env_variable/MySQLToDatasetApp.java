package net.jgp.books.spark.ch08.lab101_mysql_ingestion_env_variable;

import java.util.Properties;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

/**
 * MySQL injection to Spark, using the Sakila sample database, taking the
 * password from an environment variable instead of a hard-coded value.
 */
public class MySQLToDatasetApp {

  public static void main(String[] args) {
    MySQLToDatasetApp app = new MySQLToDatasetApp();
    app.start();
  }

  private void start() {
    SparkSession spark = SparkSession.builder()
        .appName("MySQL to Dataframe using a JDBC Connection")
        .master("local")
        .getOrCreate();

    final String dbConnectionUrl = "jdbc:mysql://localhost:3306/sakila";

    Properties props = new Properties();
    props.put("driver", "com.mysql.cj.jdbc.Driver");
    props.put("user", "root");

    byte[] password = System.getenv("DB_PASSWORD").getBytes();
    props.put("password", new String(password));
    password = null;
    props.put("useSSL", "false");

    Dataset<Row> df = spark.read().jdbc(dbConnectionUrl, "actor", props);
    df = df.orderBy(df.col("last_name"));

    // Displays the dataframe and some of its metadata.
    df.show(5);
    df.printSchema();
    System.out.println("The dataframe contains " + df.count() + " record(s).");
  }
}
