package superadder

import cats._
import cats.implicits._

case class Order(totalCost: Double, quantity: Double)

implicit val orderAdditionMonoid: Monoid[Order] = new Monoid[Order] {
  def empty: Order = Order(0, 0)
  def combine(x: Order, y: Order): Order =
    Order(x.totalCost + y.totalCost, x.quantity + y.quantity)
}

def add[A](items: List[A])(implicit monoid: Monoid[A]): A =
  items.foldLeft(monoid.empty)(_ |+| _)
