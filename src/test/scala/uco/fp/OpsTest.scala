package uco.fp

import org.scalatest.{ BeforeAndAfterAll, Matchers, WordSpec }

class OpsTest extends WordSpec with Matchers with BeforeAndAfterAll {

  val intNumbers = Num
  val strings    = Str
  val lists      = Lst
  val factorial  = ExercicesClass2

  "Integer numbers" should {
    "be added with another number" in {
      intNumbers.add(10, 20) shouldBe 30
    }
    "be subtracted with another number" in {
      intNumbers.subs(20, 10) shouldBe 10
    }
  }

  "Strings" should {
    "be added with another string" in {
      strings.add("Hello", "World") shouldBe "Hello + World"
    }
    "be subtracted with another string" in {
      strings.subs("Hola", "Mundo") shouldBe "Hola - Mundo"
    }
  }

  "Lists" should {
    "be added with another list" in {
      lists.add(List(1, 2, 3), List("uno", "dos", "tres")) shouldBe List(1, 2, 3, "uno", "dos", "tres")
    }
    "calculate its difference with other list" in {
      lists.subs(List(1, 2, 3), List(2, 3)) shouldBe List(1)
    }
    "return the first parameter list when its doesn't have a difference" in {
      lists.subs(List(1, 2, 3), List("uno", "dos", "tres")) shouldBe List(1, 2, 3)
    }
  }

  "Factorial" should {
    "calcuate factorial of 3" in {
      factorial.factorial(3) shouldBe 6
    }
  }

}
