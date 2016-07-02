import sbt.Project.projectToRef

lazy val shared = (crossProject.crossType(CrossType.Pure) in file("shared"))
  .settings(
    scalaVersion := Settings.versions.scala,
    libraryDependencies ++= Settings.sharedDependencies.value
  ).jsConfigure(_ enablePlugins ScalaJSPlay)

lazy val sharedJVM = shared.jvm.settings(name := "sharedJVM")

lazy val sharedJS = shared.js.settings(name := "sharedJS")

lazy val client: Project = (project in file("client"))
  .settings(
    name := "client",
    version := Settings.version,
    scalaVersion := Settings.versions.scala,
    scalacOptions := Settings.scalacOptions,
    libraryDependencies ++= Settings.scalaJsDependencies.value,
    jsDependencies ++= Settings.jsDependencies.value
  )
  .enablePlugins(ScalaJSPlugin, ScalaJSPlay)
  .dependsOn(sharedJS)

lazy val clients = Seq(client)

lazy val server: Project = (project in file("server"))
  .settings(
    name := "server",
    version := Settings.version,
    scalaVersion := Settings.versions.scala,
    scalacOptions := Settings.scalacOptions,
    libraryDependencies ++= Settings.jvmDependencies.value,
    scalaJSProjects := clients
  )
  .enablePlugins(PlayScala)
  .disablePlugins(PlayLayoutPlugin)
  .aggregate(clients.map(projectToRef): _*)
  .dependsOn(sharedJVM)


onLoad in Global := (Command.process("project server", _: State)) compose (onLoad in Global).value
