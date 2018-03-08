package uco.fp

object ExercicesClass2 {

  def factorial(x: Int): Int = {
    if (x <= 1) {
      x
    } else
      x * factorial(x - 1)
  }
  def fibonacci(x: Int): Int = {
    if (x > 100)
      x
    else
      fibonacci(x)
  }

  /* def whilee(b: Boolean)(doo: Any)(f: Any => Any) = {
    if (b)
      whilee(true, f(doo))
    else
      doo
  } */

  def conditional(b: Boolean, whenTrue: Any, whenFalse: Any): Any = {
    if (b) whenTrue else whenFalse
  }

  def conditional2(b: Boolean,
                   y: Boolean,
                   whenTrue: Any,
                   whenFalse: Any): Any = {
    if (b) whenTrue
    else if (y)
      whenTrue
    else whenFalse
  }

  def whenByName(b: Boolean, whenTrue: Any, whenFalse: Any): Any = {
    if (b) whenTrue else whenFalse
  }
  def whenByValue(b: Boolean, whenTrue: => Any, whenFalse: => Any): Any = {
    if (b) whenTrue else whenFalse
  }

  def printer(x: Int): String = {
    s"El factorial del número es $x"
  }

  def suma(x: Int, y: Int): Int = x + y

  def hof1[A, B](x: A, msg: String, f: A => B): String = {
    s"El $msg es: ${f(x)}"
  }

  def division(x: Double, y: Double): Double = {
    if (y <= 0) Double.NaN
    else x / y
  }

  /* def kidDivision: Double => Double => Double =
    x =>
      y =>
        y {
          case y if y != 0 => x / y
    }*/
  //andThen : Int => Int => String

}
