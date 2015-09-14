import sbt._

import com.typesafe.sbt.packager.Keys._

import sbt.Keys._

import com.typesafe.sbt.SbtNativePackager._

import sbtassembly.Plugin._

import AssemblyKeys._

import com.typesafe.sbteclipse.plugin.EclipsePlugin.EclipseKeys

assemblySettings

EclipseKeys.createSrc in ThisBuild := EclipseCreateSrc.Default + EclipseCreateSrc.Resource

name := "mongoplay"

organization := "com.yarenty"

jarName in assembly := "mongoplay-1.0.0.jar"

version := "1.0.0"

fork in run := true

scalaVersion := "2.11.6"

scalacOptions ++= Seq("-deprecation", "-feature")

scalacOptions in Test ++= Seq("-Yrangepos")

// no scala classes in assembly
assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)


javaOptions in(Test, run) += "-XstartOnFirstThread"


// LIBRARIES
libraryDependencies += "org.mongodb" %% "casbah" % "2.8.2"


//-------------------
//all for test
libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"

libraryDependencies += "junit" % "junit" % "4.12" % "test"

libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.12.2" % "test"

libraryDependencies += "org.specs2" %% "specs2-core" % "3.1" % "test"

// --------------------
//scala default libraries

//Standard library for the Scala Programming Language 
libraryDependencies += "org.scala-lang" % "scala-library" % "2.11.6" % "provided"

//Compiler for the Scala Programming Language
libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.11.6" % "provided"

//Compiler for the Scala Programming Language
libraryDependencies += "org.scala-lang" % "scala-compiler" % "2.11.6" % "provided"

//swing for scala
//libraryDependencies += "org.scala-lang" % "scala-swing" % "2.11.0-M7"


//-------------------
// logging
//The slf4j API 
libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.10" % "provided"

//SLF4J Simple binding
libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.7.10" % "provided"

//Log4j implemented over SLF4J 
libraryDependencies += "org.slf4j" % "log4j-over-slf4j" % "1.7.10" % "provided"

libraryDependencies += "log4j" % "log4j" % "1.2.17" % "provided"


//-------------------
// some additional stuff
//REST framework
//libraryDependencies += "com.squareup.retrofit" % "retrofit" % "1.9.0" % "provided"


//akka
//libraryDependencies += "com.typesafe.akka" % "akka-actor_2.11" % "2.3.9" % "provided"

//libraryDependencies += "com.typesafe.akka" % "akka-testkit_2.11" % "2.3.9" % "provided"

//libraryDependencies += "com.typesafe.akka" % "akka-slf4j_2.11" % "2.3.9" % "provided"


//RESOLVERS

//resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

resolvers += "Local Maven Repository" at "" + Path.userHome.asFile.toURI.toURL + ".m2/repository"

test in assembly := {}

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
{
  case PathList("javax", "servlet", xs @ _*)         => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".html" => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".RSA"  => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith "mailcap"  => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".properties"  => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".xml"  => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".class"  => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".thrift"  => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".jnilib"  => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".dll"  => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".so"  => MergeStrategy.first
  case "application.conf" => MergeStrategy.concat
  case "log4j.properties"     => MergeStrategy.discard
  case x => old(x)
}
}
