name := "infobate"

version := "1.0-SNAPSHOT"

resolvers += "Atlassian's Maven Public Repository" at "https://maven.atlassian.com/content/groups/public"

resolvers += "Local Maven Repository" at "file://" + Path.userHome + "/.m2/repository"

libraryDependencies ++= Seq(
  javaJdbc,
  javaJpa,
  cache,
  "org.hibernate" % "hibernate-entitymanager" % "3.6.9.Final",
  "commons-io" % "commons-io" % "2.4",
  "commons-lang" % "commons-lang" % "2.6",
  "commons-codec" % "commons-codec" % "1.6",
  "com.google.guava" % "guava" % "17.0",
  "mysql" % "mysql-connector-java" % "5.1.18"
)     

play.Project.playJavaSettings