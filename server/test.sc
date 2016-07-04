import boopickle.Default._

val data = Seq("Hello", "World!")
val r = Pickle.intoBytes(data)
