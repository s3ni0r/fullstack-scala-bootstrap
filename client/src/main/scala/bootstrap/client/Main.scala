package bootstrap.client

import autowire._
import services.AjaxClient
import bootstrap.server.Api
//implicits
import boopickle.Default._

import scalatags.JsDom.all._
import org.scalajs.dom.document

import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

@JSExport("MainApp")
object MainApp extends JSApp{
  @JSExport
  def main(): Unit = {
    val outputBox = div.render
    val $post = AjaxClient[Api]

    def getUsers(): Unit = {
      $post.list().call().foreach { users =>
        users.map{ user =>
          outputBox.appendChild(li(
            user.name
          ).render)
          println(user)
        }
      }
    }
    getUsers()

    document.getElementById("root").appendChild(outputBox)
  }
}

