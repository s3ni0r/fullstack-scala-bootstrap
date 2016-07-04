import sbt._
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
/**
  * Object holding all scala properties and project dependencies
  */
object Settings {
  val name = "bootstrap"
  val version = "1.0"

  val scalacOptions = Seq(
    "-Xlint",
    "-unchecked",
    "-deprecation",
    "-feature"
  )

  object versions {
    val scala = "2.11.8"
    val autowire = "0.2.5"
    val booPickle = "1.2.4"
    val playScripts = "0.5.0"

    val scalaDom = "0.9.0"
    val scalaTags = "0.5.5"

    val bootstrap = "3.3.6"
    val jQuery = "1.11.1"
  }

  val sharedDependencies = Def.setting(Seq(
    "com.lihaoyi" %%% "autowire" % versions.autowire,
    "me.chrons" %%% "boopickle" % versions.booPickle
  ))

  val jvmDependencies = Def.setting(Seq(
    "com.vmunier" %% "play-scalajs-scripts" % versions.playScripts,
    "org.webjars" % "font-awesome" % "4.3.0-1" % Provided,
    "org.webjars" % "bootstrap" % versions.bootstrap % Provided,
    "me.chrons" %% "boopickle" % versions.booPickle
  ))

  val scalaJsDependencies = Def.setting(Seq(
    "org.scala-js" %%% "scalajs-dom" % versions.scalaDom,
    "com.lihaoyi" %%% "scalatags" % versions.scalaTags
  ))

  val jsDependencies = Def.setting(Seq(
    "org.webjars" % "jquery" % versions.jQuery / "jquery.js" minified "jquery.min.js",
    "org.webjars" % "bootstrap" % versions.bootstrap / "bootstrap.js" minified "bootstrap.min.js" dependsOn "jquery.js"
  ))
}