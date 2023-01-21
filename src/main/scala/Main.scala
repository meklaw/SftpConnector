package ru.meklaw

import org.apache.hadoop.conf.Configuration
import org.apache.spark.sql.{SaveMode, SparkSession}

object Main {
  val user = "user"
  val password = "pass"
  val port = "2222"
  val ipv4 = "127.0.0.1"

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("SftpConnector")
      .master("local")
      .getOrCreate()

    val df = spark.read.csv("src/main/resources/books.csv")

    val hadoopConf = new Configuration()
    hadoopConf.set("fs.sftp.impl", "org.apache.hadoop.fs.sftp.SFTPFileSystem")
    hadoopConf.set("fs.sftp.host", "127.0.0.1")
    hadoopConf.set("fs.sftp.host.port", "2222")
    hadoopConf.set("fs.sftp.username", "user")
    hadoopConf.set("fs.sftp.password", "pass")

    spark.sparkContext.hadoopConfiguration.addResource(hadoopConf)

    val sftpPath = "sftp://user:pass@127.0.0.1:2222//home/user/upload"
    df.write
      .mode(SaveMode
        .Overwrite)
      .option("header", "true")
      .csv(sftpPath)


    spark.stop()
  }
}
