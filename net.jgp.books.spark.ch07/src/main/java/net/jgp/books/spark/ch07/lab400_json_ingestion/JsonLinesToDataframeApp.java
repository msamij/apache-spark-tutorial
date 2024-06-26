package net.jgp.books.spark.ch07.lab400_json_ingestion;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

/**
 * JSON Lines ingestion in a dataframe.
 * 
 */
public class JsonLinesToDataframeApp {

  public static void main(String[] args) {
    JsonLinesToDataframeApp app = new JsonLinesToDataframeApp();
    app.start();
  }

  private void start() {
    // Creates a session on a local master.
    SparkSession spark = SparkSession.builder().appName("JSON Lines to Dataframe").master("local").getOrCreate();

    // Reads a CSV file with header, called books.csv, stores it in a dataframe.
    Dataset<Row> df = spark.read().format("json").load("data/durham-nc-foreclosure-2006-2016.json");

    // Shows at most 5 rows from the dataframe.
    df.show(5);
    df.printSchema();
  }
}
