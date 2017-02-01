package testApp

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import scalajs.js
import org.scalajs.dom
import js.annotation._
import eldis.redux._
import scala.concurrent.ExecutionContext.Implicits.global

object Hello {

  val component = ReactComponentB[Unit]("testComp")
    .render { scope =>
      <.div("Hello")
    }.build

  def apply() = component()

}

object Main extends js.JSApp {

  private object Dependencies {

    @JSImport("react", JSImport.Namespace)
    @js.native
    object React extends js.Object {}

    @JSImport("react-dom", JSImport.Namespace)
    @js.native
    object ReactDOM extends js.Object {}

    def setup = {
      js.Dynamic.global.React = React
      js.Dynamic.global.ReactDOM = ReactDOM
    }
  }

  def App(store: Store[js.Any, js.Any]): ReactElement = {
    react.Provider(store)(
      Hello()
    )
  }

  @JSImport("redux-logger", JSImport.Namespace)
  @js.native
  object createLogger extends js.Object {
    def apply(): Middleware[js.Any, js.Any] = js.native
  }

  def main(): Unit = {
    Dependencies.setup

    val store = createStore(
      (s: js.Any, a: js.Any) => s,
      js.undefined,
      js.undefined,
      applyMiddleware(Seq(createLogger()))
    )

    ReactDOM.render(App(store), dom.document.getElementById("root"))
  }
}

