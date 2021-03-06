import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "haproxy-rest-config"
  val appVersion      = "1.0-SNAPSHOT"
  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    anorm,
    "com.typesafe" %% "scalalogging-slf4j" % "1.0.1",
    "com.wordnik" %% "swagger-play2-utils" % "1.2.5" excludeAll(ExclusionRule(organization="org.slf4j"))


  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here     
//	resolvers += "Sonatype OSS Snapshots for Wordnik" at "https://oss.sonatype.org/content/repositories/snapshots/",
//	resolvers += "Maven.org repo1" at "http://repo1.maven.org/maven2/"   
	
    resolvers := Seq(
      "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository",
      "sonatype-snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
      "sonatype-releases" at "https://oss.sonatype.org/content/repositories/releases",
      "java-net" at "http://download.java.net/maven/2",
      "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
      "Maven Central Repository" at "http://repo.maven.apache.org/")  
)

}
