/**
 * Created by maria on 02.02.2017.
 */
package testApp

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import eldis.redux._

object TestForm {
  sealed trait EventType
  case class SetUser(name: String) extends EventType

  case class Props(
    username: String = "",
    onChangeUserName: String => Callback = String => Callback.empty
  )

  case class State(username: String = "someone")

  val component = FunctionalComponent[Props] {
    props: Props =>
      {
        def onChange(e: ReactEventI): Callback =
          props.onChangeUserName(e.target.value)

        <.div(
          <.label("Username: "),
          <.input.text(^.value := props.username, ^.onChange ==> onChange),
          <.label(s"Hello, ${props.username}!")
        )
      }
  }

  val connected =
    react.connect(
      (d: Dispatcher[Action]) => {
        val onChangeUserName = (userName: String) => Callback {
          d(ChangeUsername(SetUser(userName)))
        }

        (s: Main.State) =>
          Props(
            username = s.testForm.username,
            onChangeUserName = onChangeUserName
          )
      },
      component
    )

  def apply() = connected(Props())
}
