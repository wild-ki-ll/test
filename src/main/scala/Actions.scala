/**
 * Created by maria on 02.02.2017.
 */
package testApp

sealed trait Action
case class ChangeUsername(eventType: TestForm.EventType) extends Action

