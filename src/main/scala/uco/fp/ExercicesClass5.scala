package uco.fp

object ExercicesClass5 {

  def sum1: Int => Int => Int = a => b => a + b
  def sum(a: String, b: String): String = a + b
  def sum(a: Int)(implicit b: Int): Int = a + b

  implicit val i: Int = "4"
  implicit def toS: Int => String = _.toString
  implicit def toI: String => Int = java.lang.Integer.valueOf(_)

  case class Person(nombre: String, edad: Int, patrimonio: Int = 10)
  implicit def pertoI: Person => Int = _.edad

  def sum2(a: Int)(implicit b: Person): Int = a + b.edad

  def mayor(str1: String, str2: String): Boolean = //FORMA FEA
    if (str1.length <= str2.length)
      false
    else true

  implicit class StringOps(s: String) {
    def >==(s2: String): Boolean =
      s.length >= s2.length
    //Ej: "Hola" >== Chao
  }

  //Ad hoc polimorfismo

  //Type Class pattern
  //1. Definir un trait con la funcion polimorfica que se quiere implementar
  //2. Definir las instancias de como la funcion polimorfica opera con ciertos tipos concretos
  //3. Definir el uso de la funcion inyectandole el type class

  trait Sumable[T] {
    def sumar(a: T, b: T): T
    def zero: T
  }

  object SumableOps {
    implicit object IntSumable extends Sumable[Int] {
      def sumar(a: Int, b: Int): Int = a + b
      def zero: Int = 0
    }
    implicit object StringSumable extends Sumable[String] {
      def sumar(a: String, b: String): String = a + b
      def zero: String = ""
    }
    implicit object PersonaSumable extends Sumable[Person] {
      def sumar(a: Person, b: Person): Person =
        Person("Juridica", 0, a.patrimonio + b.patrimonio)
      override def zero: Person = Person("Juridica", 0, 0)
    }
  }

  import SumableOps._
  def sum[T](a: T, b: T)(implicit s: Sumable[T]): T =
    s.sumar(a, b) //Primera forma de escribirlo
  def sum2[T: Sumable](a: T, b: T) =
    implicitly[Sumable[T]].sumar(a, b) //Segunda forma de escribirlo
  //Use:
  sum(1, 2)
  sum("1", "2")
  println(s"OEE ${sum2(Person("Andres", 3, 50), Person("Pablo", 4, 60))}")

}
