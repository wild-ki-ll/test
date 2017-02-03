package testApp

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import scalajs.js
import org.scalajs.dom
import js.annotation._
import eldis.redux._
import scala.concurrent.ExecutionContext.Implicits.global
import rrf.impl

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

<<<<<<< HEAD
  def App(store: Store[js.Any, impl.Action]): ReactElement = {
    val form = TestForm()
    react.Provider(store)(
      form
    )
  }

  @JSImport("redux-logger", JSImport.Namespace)
  @js.native
  object createLogger extends js.Object {
    def apply(): Middleware[js.Any, impl.Action] = js.native
=======
  object App {
    def apply(store: Store[State, Action]) =
      react.Provider(store)(
        TestForm()
      )
>>>>>>> c792a71... simle sjs-rrf form - it works!
  }

  def main(): Unit = {
    Dependencies.setup

    val store = createStore(
      (s: js.Any, a: js.Any) => s,
      js.undefined,
      impl.combineForms(js.Dynamic.literal(testForm = TestForm.initialState)),
      applyMiddleware(Seq(createLogger()))
    )

    ReactDOM.render(App(store), dom.document.getElementById("root"))
  }

}

