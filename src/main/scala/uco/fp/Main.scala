package uco.fp

import uco.fp.ExercicesClass4.Plano

object Main extends App {

  val exercices = ExercicesClass2

  val x = (exercices.factorial _ andThen exercices.printer _)(3)

  val y = (exercices.printer _ compose exercices.factorial _)(3)

  val sumMethod = exercices.suma(6, _ : Int) //Currying function
  val curryngFunction = sumMethod(4) //Continuation of function

  val xz = exercices.hof1(3, "factorial", exercices.factorial)

  val y1 = exercices.division(5, 2)
  val y2 = exercices.division(5, 0)

  //Clase 3 Febrero/17/2018

  val class3 = ExercicesClass3

  val z = class3.mcd(96, 135)
  println(s"MCD $z")

  val cop = COP(1000)
  val usd = USD(1000)
  val trm = List((cop, 20000D), (usd, 29000D))

  val divisa = Divisas.toCOP(usd, trm, Divisas.trms _)

  //Clase 4 Febrero/24/2018

  val class4 = ExercicesClass4

  println(s"Suma segura de lista ${class4.sumSafe((1 to 4).toList)}")

  println(s"Promedio recursivo lista ${class4.prom(Nil)}")

  val mensaje = class4.Mensaje3[Plano]("hola", 2)
  val mensaje2 = class4.Mensaje3[Plano]("", 2)
  println(s"MENSAJE1 $mensaje")
  println(s"MENSAJE2 $mensaje2")

  //Clase 5 Marzo/03/2018

  val clase5 = ExercicesClass5

  implicit val persona: Int = clase5.Person("Jefferson -The kid- Ossa", 24)
  val suma1 = clase5.sum1(1)

  implicit val person: clase5.Person =
    clase5.Person("Jefferson -The kid- Ossa", 24)
  val suma2 = clase5.sum2(2)(person)

}
