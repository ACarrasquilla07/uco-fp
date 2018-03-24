package uco.fp

import scala.concurrent._
import scala.concurrent.duration._

import scala.concurrent.ExecutionContext.Implicits.global

object ExercisesClass7 {

  def sumaFuturos: Int = {
    val event = Future {
      println(s"Futuro 1")
      1
    }
    val event2 = Future {
      println(s"Futuro 2")
      2
    }
    val event3 = Future {
      println(s"Futuro 3")
      3
    }

    println(s"Inicio for: ")
    val res = for {
      f1 <- event
      f2 <- event2
      f3 <- event3
    } yield {
      println(s"Suma de futuros ----> End")
      f1 + f2 + f3
    }

    println("Principal")

    val result = Await.result(res, 2.seconds)
    println("Await")
    println(result)
    result
  }

  def sumFuturosWithMap = {

    lazy val ff1 = Future {
      println(s"Futuro 1")
      1
    }
    lazy val ff2 = Future {
      println(s"Futuro 2")
      2
    }
    lazy val ff3 = Future {
      println(s"Futuro 3")
      3
    }

    println(s"Inicio futuros lazy val")

    val l = for {
      f1 <- ff1
      f2 <- ff2
      f3 <- ff3
    } yield {
      val r = f1 + f2 + f3
      println(s"sumando futuros for lazy val: $r")
      r
    }
    println(s"Fin futuros lazy val")

    println(s"Inicio futuros for")
    for {
      f1 <- Future {
        println(s"Futuro 1 - for")
        1
      }
      f2 <- Future {
        println(s"Futuro 2 - for")
        2
      }
      f3 <- Future {
        println(s"Futuro 3 - for")
        3
      }
    } yield {
      val r = f1 + f2 + f3
      println(s"sumando futuros for: $r")
      r
    }
    println(s"Fin futuros for")
    Thread.sleep(2000)
    l
  }

  case class ID(num: Int)
  case class Name(name: String)
  sealed trait Gender
  final case object Male extends Gender
  final case object Female extends Gender
  case class User(id: ID, name: Name, gender: Option[Gender])
  object User {
    def apply(id: ID, name: Name, gender: Option[Gender]): User =
      new User(id, name, gender)
  }

  def getName: Future[Name] = {
    Future(Name("Pedro"))
  }
  def getID: Future[ID] = {
    Future(ID(1234))
  }
  def getGender: Future[Option[Gender]] = {
    Future(Option(Male))
  }

  def getOrchestator: Future[User] = {

    for {
      name <- getName
      id <- getID
      gender <- getGender
    } yield {
      User(id, name, gender)
    }

  }

}
