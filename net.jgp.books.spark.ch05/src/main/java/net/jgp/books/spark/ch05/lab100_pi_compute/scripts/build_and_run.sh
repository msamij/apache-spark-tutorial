#!/bin/bash

# Run Maven package
mvn package

class_name="net.jgp.books.spark.ch05.lab100_pi_compute.PiComputeApp"
jar_path="/home/muhammadsami/Public/Projects/Spark/net.jgp.books.spark.ch05/target/spark-in-action2-chapter05-1.0.0-SNAPSHOT.jar"

# Check if the Maven build was successful
if [ $? -eq 0 ]; then
    # Run spark-submit with the specified class and JAR file
    spark-submit --class "$class_name" "$jar_path"
else
    echo "Maven package failed. Exiting..."
    exit 1
fi
