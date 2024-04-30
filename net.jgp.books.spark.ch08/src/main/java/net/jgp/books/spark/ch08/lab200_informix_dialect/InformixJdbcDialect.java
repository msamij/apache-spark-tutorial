package net.jgp.books.spark.ch08.lab200_informix_dialect;

import org.apache.spark.sql.jdbc.JdbcDialect;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.MetadataBuilder;

import scala.Option;

/**
 * An Informix dialect for Apache Spark.
 */
public class InformixJdbcDialect extends JdbcDialect {
  private static final long serialVersionUID = -672901;

  @Override
  public boolean canHandle(String url) {
    return url.startsWith("jdbc:informix-sqli");
  }

  /**
   * Processes specific JDBC types for Catalyst.
   */
  @Override
  public Option<DataType> getCatalystType(int sqlType, String typeName, int size, MetadataBuilder md) {
    // Refactor.
    Option<DataType> optionDataType = switch (typeName.toLowerCase()) {
      case "serial" -> Option.apply(DataTypes.IntegerType);
      case "calendar" -> Option.apply(DataTypes.BinaryType);
      case "calendarpattern" -> Option.apply(DataTypes.BinaryType);
      case "se_metadata" -> Option.apply(DataTypes.BinaryType);
      case "sysbldsqltext" -> Option.apply(DataTypes.BinaryType);
      case "timeseries" -> Option.apply(DataTypes.BinaryType);
      case "st_point" -> Option.apply(DataTypes.BinaryType);
      case "tspartitiondesc_t" -> Option.apply(DataTypes.BinaryType);
      default -> Option.empty();
    };
    return optionDataType;

    // if (typeName.toLowerCase().compareTo("serial") == 0) {
    // return Option.apply(DataTypes.IntegerType);
    // }
    // if (typeName.toLowerCase().compareTo("calendar") == 0) {
    // return Option.apply(DataTypes.BinaryType);
    // }
    // if (typeName.toLowerCase().compareTo("calendarpattern") == 0) {
    // return Option.apply(DataTypes.BinaryType);
    // }
    // if (typeName.toLowerCase().compareTo("se_metadata") == 0) {
    // return Option.apply(DataTypes.BinaryType);
    // }
    // if (typeName.toLowerCase().compareTo("sysbldsqltext") == 0) {
    // return Option.apply(DataTypes.BinaryType);
    // }
    // if (typeName.toLowerCase().startsWith("timeseries")) {
    // return Option.apply(DataTypes.BinaryType);
    // }
    // if (typeName.toLowerCase().compareTo("st_point") == 0) {
    // return Option.apply(DataTypes.BinaryType);
    // }
    // if (typeName.toLowerCase().compareTo("tspartitiondesc_t") == 0) {
    // return Option.apply(DataTypes.BinaryType);
    // }

    // return Option.empty(); // An object from the Scala library
  }
}
