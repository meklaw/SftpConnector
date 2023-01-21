//ThisBuild / version := "0.1.0-SNAPSHOT"
//
//ThisBuild / scalaVersion := "2.12.10"
//
//lazy val root = (project in file("."))
//  .settings(
//    name := "SftpConnector",
//    idePackagePrefix := Some("ru.meklaw")
//  )
//
//val sparkVersion = "3.2.2"
//
//libraryDependencies ++= Seq(
//  "org.apache.spark" %% "spark-core" % sparkVersion,
//  "org.apache.spark" %% "spark-sql" % sparkVersion
//)
//
//addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "2.1.0")
//
//lazy val app = (project in file("app"))
//  .settings(
//    assembly / mainClass := Some("com.example.Main"),
//    // more settings here ...
//  )
//
//lazy val utils = (project in file("utils"))
//  .settings(
//    assembly / assemblyJarName := "utils.jar",
//    // more settings here ...
//  )

ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.12.10"

val sparkVersion = "3.2.2"

lazy val root = (project in file("."))
  .settings(
    name := "SftpConnector",
    idePackagePrefix := Some("ru.meklaw"),
    libraryDependencies ++= Seq(
      "org.apache.spark" %% "spark-core" % sparkVersion,
      "org.apache.spark" %% "spark-sql" % sparkVersion
    ),
    assemblyJarName in assembly := s"${name.value}-${version.value}.jar",
    assemblyMergeStrategy in assembly := {
      case PathList("META-INF", xs @ _*) => MergeStrategy.discard
      case x => MergeStrategy.first
    }
  ).enablePlugins(AssemblyPlugin)




// https://mvnrepository.com/artifact/com.jcraft/jsch
//libraryDependencies += "com.jcraft" % "jsch" % "0.1.55"
// https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-shade-plugin
