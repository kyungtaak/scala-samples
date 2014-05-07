name := "scala-samples"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.10.3"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

autoCompilerPlugins := true

libraryDependencies <+= scalaVersion { v => compilerPlugin("org.scala-lang.plugins" % "continuations" % v) }

scalacOptions += "-P:continuations:enable"

libraryDependencies += "com.typesafe.akka" % "akka-actor_2.10" % "2.2.3"

libraryDependencies += "com.typesafe.akka" % "akka-agent_2.10" % "2.2.3"

libraryDependencies += "com.typesafe.akka" % "akka-dataflow_2.10" % "2.2.3"

libraryDependencies += "org.scala-stm" % "scala-stm_2.10" % "0.7"

libraryDependencies += "org.specs2" % "specs2_2.10" % "2.3.7"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.1.5" % "test"
