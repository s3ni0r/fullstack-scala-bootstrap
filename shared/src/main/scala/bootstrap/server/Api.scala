package bootstrap.server

trait Api {
  def list(): List[User]
}
