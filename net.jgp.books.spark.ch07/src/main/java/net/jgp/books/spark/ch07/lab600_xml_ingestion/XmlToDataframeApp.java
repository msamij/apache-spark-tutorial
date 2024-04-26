package net.jgp.books.spark.ch07.lab600_xml_ingestion;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

/**
 * XML ingestion to a dataframe.
 * 
 * Source of file: NASA patents dataset -
 * https://data.nasa.gov/Raw-Data/NASA-Patents/gquh-watm
 */
public class XmlToDataframeApp {

  public static void main(String[] args) {
    XmlToDataframeApp app = new XmlToDataframeApp();
    app.start();
  }

  private void start() {
    // Creates a session on a local master
    SparkSession spark = SparkSession.builder().appName("XML to Dataframe").master("local").getOrCreate();

    // Reads a XML file with header, called nasa-patents.xml, stores it in a
    // dataframe
    Dataset<Row> df = spark.read().format("xml").option("rowTag", "row").load("data/nasa-patents.xml");

    // Shows at most 5 rows from the dataframe
    df.show(5);
    df.printSchema();
  }
}
