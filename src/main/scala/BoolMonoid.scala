package boolmonoid

import cats.syntax.semigroup._

enum Boolean:
  case True
  case False

def and(a: Boolean, b: Boolean): Boolean = a match
  case Boolean.True  => b
  case Boolean.False => Boolean.False

trait Semigroup[T] {
  extension (x: T) def combine(y: T): T
}

trait Monoid[T] extends Semigroup[T] {
  def unit: T
}

given Monoid[Boolean] with
  extension (x: Boolean) def combine(y: Boolean): Boolean = and(x, y)
  def unit: Boolean = Boolean.True
