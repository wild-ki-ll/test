package testApp

import japgolly.scalajs.react._
import scalajs.js
import org.scalajs.dom
import js.annotation._
import eldis.redux._
import scala.concurrent.ExecutionContext.Implicits.global

object Main extends js.JSApp {

  case class State(testForm: TestForm.State = TestForm.State())

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

  object App {
    def apply(store: Store[State, Action]) =
      react.Provider(store)(
        TestForm()
      )
  }

  def main(): Unit = {
    Dependencies.setup

    val store = createStore[State, Action](
      Reducers.reducer _,
      State()
    )
    ReactDOM.render(App(store), dom.document.getElementById("root"))
  }

}
