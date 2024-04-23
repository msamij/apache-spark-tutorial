#!/bin/bash

# Run Maven package
mvn package

args="noop"
class_name="net.jgp.books.spark.ch04.lab200_transformation_and_action.TransformationAndActionApp"
jar_path="/home/muhammadsami/Public/Projects/Spark/net.jgp.books.spark.ch04/target/spark-in-action2-chapter04-1.0.0-SNAPSHOT.jar"

# Check if the Maven build was successful
if [ $? -eq 0 ]; then
    # Run spark-submit with the specified class and JAR file
    spark-submit --class "$class_name" "$jar_path" "$args"
else
    echo "Maven package failed. Exiting..."
    exit 1
fi
