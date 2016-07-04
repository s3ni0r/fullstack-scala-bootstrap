package bootstrap.client.services

import bootstrap.server.{Api, User}

class ApiService extends Api {
  var users = List(User(1, "Foo"), User(2, "Bar"))
  def list() : List[User] = {
    println(s"Sending ${users.size} users")
    users
  }
}
