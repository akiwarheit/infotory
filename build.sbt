name := "infobate"

version := "1.0-SNAPSHOT"

resolvers += "Atlassian's Maven Public Repository" at "https://maven.atlassian.com/content/groups/public"

resolvers += "Local Maven Repository" at "file://" + Path.userHome + "/.m2/repository"

resolvers += Resolver.url("Objectify Play Repository", url("http://schaloner.github.io/releases/"))(Resolver.ivyStylePatterns)

resolvers += Resolver.url("Objectify Play Snapshot Repository", url("http://schaloner.github.io/snapshots/"))(Resolver.ivyStylePatterns)

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  javaJpa,
  cache,
  "org.hibernate" % "hibernate-entitymanager" % "3.6.9.Final",
  "org.apache.httpcomponents" % "httpclient" % "4.3.3",
  "commons-io" % "commons-io" % "2.4",
  "commons-lang" % "commons-lang" % "2.6",
  "commons-codec" % "commons-codec" % "1.6",
  "com.google.guava" % "guava" % "17.0",
  "mysql" % "mysql-connector-java" % "5.1.18",
  "be.objectify" %% "deadbolt-java" % "2.2.1-RC2",
  "com.google.inject" % "guice" % "3.0"
)     

play.Project.playJavaSettings