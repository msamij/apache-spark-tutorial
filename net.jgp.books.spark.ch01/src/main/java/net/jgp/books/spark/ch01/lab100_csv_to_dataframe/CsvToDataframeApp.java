package net.jgp.books.spark.ch01.lab100_csv_to_dataframe;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class CsvToDataframeApp {
  public static void main(String[] args) {
    CsvToDataframeApp app = new CsvToDataframeApp();
    app.start();
  }

  private void start() {
    // Creates a session on a local master.
    SparkSession spark = SparkSession.builder().appName("CSV to Dataset").master("local").getOrCreate();

    // Reads a CSV file with header, called books.csv, stores it in a dataframe.
    Dataset<Row> df = spark.read().format("csv").option("header", "true").load("data/books.csv");

    // Shows at most 5 rows from the dataframe.
    df.show(5);
  }
}
