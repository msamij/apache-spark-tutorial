file://<WORKSPACE>/net.jgp.books.spark.ch09/src/main/java/net/jgp/books/spark/ch09/lab400_photo_datasource/PhotoMetadataIngestionApp.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 3.3.3
Classpath:
<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala3-library_3/3.3.3/scala3-library_3-3.3.3.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.12/scala-library-2.13.12.jar [exists ]
Options:



action parameters:
uri: file://<WORKSPACE>/net.jgp.books.spark.ch09/src/main/java/net/jgp/books/spark/ch09/lab400_photo_datasource/PhotoMetadataIngestionApp.java
text:
```scala
package net.jgp.books.spark.ch09.lab400_photo_datasource;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

/**
 * Ingest metadata from a directory containing photos, make them available as
 * EXIF.
 */
public class PhotoMetadataIngestionApp {
  public static void main(String[] args) {
    PhotoMetadataIngestionApp app = new PhotoMetadataIngestionApp();
    app.start();
  }

  /**
   * Starts the application.
   * 
   * @return <code>true</code> if all is ok.
   */
  private boolean start() {
    SparkSession spark = SparkSession.builder().appName("EXIF to Dataset").master("local").getOrCreate();

    String importDirectory = "data";

    Dataset<Row> df = spark.read().format("exif")
        .option("recursive", "true").option("limit", "100000")
        .option("extensions", "jpg,jpeg").load(importDirectory);

    System.out.println("I have imported " + df.count() + " photos.");
    df.printSchema();
    df.show(5);
    return true;
  }
}

```



#### Error stacktrace:

```
scala.collection.Iterator$$anon$19.next(Iterator.scala:973)
	scala.collection.Iterator$$anon$19.next(Iterator.scala:971)
	scala.collection.mutable.MutationTracker$CheckedIterator.next(MutationTracker.scala:76)
	scala.collection.IterableOps.head(Iterable.scala:222)
	scala.collection.IterableOps.head$(Iterable.scala:222)
	scala.collection.AbstractIterable.head(Iterable.scala:933)
	dotty.tools.dotc.interactive.InteractiveDriver.run(InteractiveDriver.scala:168)
	scala.meta.internal.pc.MetalsDriver.run(MetalsDriver.scala:45)
	scala.meta.internal.pc.PcCollector.<init>(PcCollector.scala:44)
	scala.meta.internal.pc.PcSemanticTokensProvider$Collector$.<init>(PcSemanticTokensProvider.scala:61)
	scala.meta.internal.pc.PcSemanticTokensProvider.Collector$lzyINIT1(PcSemanticTokensProvider.scala:61)
	scala.meta.internal.pc.PcSemanticTokensProvider.Collector(PcSemanticTokensProvider.scala:61)
	scala.meta.internal.pc.PcSemanticTokensProvider.provide(PcSemanticTokensProvider.scala:90)
	scala.meta.internal.pc.ScalaPresentationCompiler.semanticTokens$$anonfun$1(ScalaPresentationCompiler.scala:110)
```
#### Short summary: 

java.util.NoSuchElementException: next on empty iterator