/**
 * Created by maria on 02.02.2017.
 */
package testApp

object Reducers {

  def reducer(s: Main.State, a: Action): Main.State =
    a match {
      case ChangeUsername(eventType) => {
        eventType match {
          case TestForm.SetUser(usermane) => {
            s.copy(testForm = s.testForm.copy(username = usermane))
          }
        }
      }

      case _ => {
        assert(false)
        s
      }
    }
}
