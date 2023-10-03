import person.Person
import json.*
import json.JsonInstances.{given}
import cats._
import cats.implicits._
import superadder._

val orderList =
  List(
    superadder.Order(1, 2),
    superadder.Order(2, 3),
    superadder.Order(1, 5),
    superadder.Order(1, 5)
  )

@main def hello: Unit =
  val intResult = "Hi" |+| "there" |+| Monoid[String].empty

  val p = Json.JsObject(
    Map(
      "person" -> Person("Hello", "world").write,
      "name" -> "Name".write,
      "number" -> 1.0.write
    )
  )

  println(add(orderList))

def msg = "I was compiled by Scala 3. :)"
