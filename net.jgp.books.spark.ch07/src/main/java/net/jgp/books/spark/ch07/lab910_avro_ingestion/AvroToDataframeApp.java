package net.jgp.books.spark.ch07.lab910_avro_ingestion;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

/**
 * Avro ingestion in a dataframe.
 * 
 * Source of file: Apache Avro project -
 * https://github.com/apache/orc/tree/master/examples
 * 
 */
public class AvroToDataframeApp {

  public static void main(String[] args) {
    AvroToDataframeApp app = new AvroToDataframeApp();
    app.start();
  }

  private void start() {
    // Creates a session on a local master.
    SparkSession spark = SparkSession.builder().appName("Avro to Dataframe").master("local").getOrCreate();

    // Reads an Avro file, stores it in a dataframe.
    Dataset<Row> df = spark.read().format("avro").load("data/weather.avro");

    // Shows at most 10 rows from the dataframe.
    df.show(10);
    df.printSchema();
    System.out.println("The dataframe has " + df.count() + " rows.");
  }
}
