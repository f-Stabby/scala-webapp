name := "play-scala-webapp"

version := "1.0"

scalaVersion := "2.13.15"

lazy val root = project.in(file("."))
  .enablePlugins(PlayScala)

resolvers += "Akka library repository".at("https://repo.akka.io/maven")

lazy val akkaVersion = "2.8.0"

fork := true

libraryDependencies ++= Seq(
  guice,
  "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.0" % Test,
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "ch.qos.logback" % "logback-classic" % "1.5.8",
  "com.typesafe.play" %% "play-slick" % "5.2.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "5.2.0",
  "org.postgresql" % "postgresql" % "42.7.2"
)

// Assembly merge strategy
assembly / assemblyMergeStrategy := {
  case "module-info.class" => MergeStrategy.first
  case x =>
    val oldStrategy = (assembly / assemblyMergeStrategy).value
    oldStrategy(x)
}
