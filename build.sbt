ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.4"

lazy val root = (project in file("."))
  .settings(
    name := "zio-sample",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio-kafka" % "2.9.0",
      "dev.zio" %% "zio-json" % "0.7.3",
      // logging
      "dev.zio" %% "zio-logging" % "2.4.0",
      "dev.zio" %% "zio-logging-slf4j" % "2.4.0",
      "ch.qos.logback" % "logback-classic" % "1.5.16",

      // Testing
      "dev.zio" %% "zio-test"          % "2.1.14" % Test,
      "dev.zio" %% "zio-test-sbt"      % "2.1.14" % Test,
      "dev.zio" %% "zio-test-magnolia" % "2.1.14" % Test
    )
  )
