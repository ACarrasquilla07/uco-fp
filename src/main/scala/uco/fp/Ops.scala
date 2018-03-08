package uco.fp

trait Ops[K] {

  def add(a: K, b: K): K

  def subs(a: K, b: K): K
}

object Num extends Ops[Int] {

  override def add(a: Int, b: Int): Int = a + b

  override def subs(a: Int, b: Int): Int = a - b
}

object Str extends Ops[String] {

  override def add(a: String, b: String): String = s"$a + $b"

  override def subs(a: String, b: String): String = s"$a - $b"
}

object Lst extends Ops[List[_]] {

  override def add(a: List[_], b: List[_]): List[_] = a ++ b

  override def subs(a: List[_], b: List[_]): List[_] = a.diff(b)
}
