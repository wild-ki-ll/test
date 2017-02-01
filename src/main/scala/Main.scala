package TestApp

import scala.scalajs.js.JSApp
import org.scalajs.dom

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

object App extends JSApp {

  val Hello =
    ReactComponentB[Unit]("No args")
      .render(_ => <.div("Hello!"))
      .build

  def main(): Unit = {
    ReactDOM.render(Hello(), dom.document.getElementById("root"))
  }
}
