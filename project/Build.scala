import sbt._
import Keys._
import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._

import scalajsbundler.sbtplugin.ScalaJSBundlerPlugin
import scalajsbundler.sbtplugin.ScalaJSBundlerPlugin.autoImport._

object test {

  object Versions {
    val scala = "2.11.8"
    val scalaJsReact = "0.11.3"
    val scalaJsDom = "0.9.1"
    val scalatest = "3.0.1"
    val htmlWebpackPlugin = "~2.26.0"
    val htmlLoader = "~0.4.3"
    val react = "~15.4.2"
    val redux = "~3.6.0"
    val reactRedux = "~5.0.2"
    val scalaJsRedux = "0.2.0-SNAPSHOT"
    val reduxLogger = "~2.7.4"
    val reactReduxForm = "~1.5.3"
    val scalaJsReactReduxForm = "0.1.0-SNAPSHOT"
  }

  object Dependencies {

    lazy val library = Seq(
      "com.github.japgolly.scalajs-react" %%%! "core"                     % Versions.scalaJsReact,
      "com.github.eldis"                  %%%! "scalajs-redux"            % Versions.scalaJsRedux,
      "org.scalatest"                     %%%! "scalatest"                % Versions.scalatest % "test",
      "com.github.eldis"                  %%%! "scalajs-react-redux-form" % Versions.scalaJsReactReduxForm
    )

    lazy val npmDep = Seq(
      "react"               -> Versions.react,
      "react-dom"           -> Versions.react,
      "redux"               -> Versions.redux,
      "react-redux"         -> Versions.reactRedux,
      "redux-logger"        -> Versions.reduxLogger,
      "react-redux-form"    -> Versions.reactReduxForm
    )

    lazy val npmDevDep = npmDep ++ Seq(
      "html-webpack-plugin" -> Versions.htmlWebpackPlugin,
      "html-loader"         -> Versions.htmlLoader
    )
  }

  object Settings {
    type PC = Project => Project

    def Prj(prjName: String): PC = { p: Project =>
      p.in(file("."))
        .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
        .settings(
          name := prjName,
          scalaVersion := Versions.scala,
          requiresDOM in Test := true,
          enableReloadWorkflow := false,
          libraryDependencies ++= Dependencies.library,
          npmDependencies     in Compile ++= Dependencies.npmDep,
          npmDevDependencies  in Compile ++= Dependencies.npmDevDep,
          webpackConfigFile in fastOptJS := Some(baseDirectory.value / "config" / "webpack.config.js"),
          webpackConfigFile in fullOptJS := Some(baseDirectory.value / "config" / "webpack.config.js")
        )
      }

  }

  object Projects {
    lazy val test = project.configure( Settings.Prj("testApp"))
  }

}
