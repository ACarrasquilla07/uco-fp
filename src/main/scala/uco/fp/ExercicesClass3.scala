package uco.fp

import scala.annotation.tailrec

object ExercicesClass3 {
  def fact(n: Int): Int = n match {
    case 0 => 1
    case _ => n * fact(n - 1)
  }

  /*
  def fact0: Int => Int = {case 0 => 1}
  def factR: Int => Int = {case n => n * factR(n-1)}
  def factC: Int => Int = fact0 orElse factR
   */ //Debe ser de tipo PartialFunction

  def fact0: PartialFunction[Int, Int] = { case 0 => 1 }
  def factR: PartialFunction[Int, Int] = { case n => n * factR(n - 1) }
  def factC: Int => Int                = fact0 orElse factR

  def factO(x: Int): Int = {
    @tailrec
    def factLoop(n: Int, acc: Int): Int = {
      if (n <= 0) acc else factLoop(n - 1, n * acc)
    }
    factLoop(x, 1)
  }

  //Ejercicio Bonus
  def mcd(x: Int, y: Int): Int = {
    @tailrec
    def func(x: Int, y: Int): Int = {
      if (y <= 0) x else func(y, x % y)
    }
    func(x, y)
  }
}
