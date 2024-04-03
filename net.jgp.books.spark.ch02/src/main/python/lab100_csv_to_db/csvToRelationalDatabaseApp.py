'''
  CSV to a relational database.

  @author rambabu.posa
'''

from pyspark.sql import SparkSession
from pyspark.sql import functions as F
import os

current_dir = os.path.dirname(__file__)
relative_path = "../../../../data/authors.csv"
absolute_file_path = os.path.join(current_dir, relative_path)

# Creates a session on a local master
spark = SparkSession.builder.appName("CSV to DB").master("local").getOrCreate()

#  Step 1: Ingestion
#  ---------
#
#  Reads a CSV file with header, called authors.csv, stores it in a dataframe
df = spark.read.csv(header=True, inferSchema=True, path=absolute_file_path)

# Step 2: Transform
# ---------
# Creates a new column called "name" as the concatenation of lname, a
# virtual column containing ", " and the fname column
df = df.withColumn("name", F.concat(F.col("lname"), F.lit(", "), F.col("fname")))

# Step 3: Save
# ----
#
# The connection URL, assuming your PostgreSQL instance runs locally on the
# default port, and the database we use is "spark_labs"
dbConnectionUrl = "jdbc:postgresql://localhost/spark_labs"

# Properties to connect to the database, the JDBC driver is part of our pom.xml
prop = {"driver":"org.postgresql.Driver", "user":"jgp", "password":"Spark<3Java"}

# Write in a table called ch02
df.write.jdbc(mode='overwrite', url=dbConnectionUrl, table="ch02", properties=prop)

# Good to stop SparkSession at the end of the application
spark.stop()
