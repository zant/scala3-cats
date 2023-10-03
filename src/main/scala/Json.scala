package json

import person.*

// Enum
enum Json:
  case JsObject(get: Map[String, Json])
  case JsString(get: String)
  case JsNumber(get: Double)
  case JsNull

// Json
object JsonInstances {
  // Traits
  trait JsonWriter[A]:
    extension (value: A) def write: Json
  trait JsonReader:
    extension (v: Json) def read: String

  given JsonReader with
    extension (v: Json)
      def read: String = v match
        case Json.JsObject(get: Map[String, Json]) => {
          get.foldLeft("")((acc: String, kv: (String, Json)) =>
            acc + "{ " + kv._1 + ": " + kv._2.read + " }\n"
          )
        }
        case Json.JsString(get: String) => get
        case Json.JsNumber(get: Double) => get.toString()
        case Json.JsNull                => ""

  given JsonWriter[Person] with
    extension (v: Person)
      def write: Json =
        Json.JsObject(
          Map(
            "email" -> Json.JsString(v.email),
            "name" -> Json.JsString(v.name)
          )
        )

  given JsonWriter[String] with
    extension (v: String) def write: Json = Json.JsString(v)

  given JsonWriter[Double] with
    extension (v: Double) def write: Json = Json.JsNumber(v)
}
