package uco.fp

import cats.data.OptionT
import cats.implicits._

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

case class User(id: String, firstName: String, lastName: String)
case class Address(address: String, user: User)

case class FutOpt[A](value: Future[Option[A]]) {

  def map[B](f: A => B): FutOpt[B] =
    FutOpt(value.map(optA => optA.map(f)))

  def flatMap[B](f: A => FutOpt[B]): FutOpt[B] =
    FutOpt(value.flatMap(opt =>
      opt match {
        case Some(a) => f(a).value
        case None    => Future.successful(None)
    }))
}

case class ListOp[A](value: List[Option[A]]) {

  def map[B](f: A => B): ListOp[B] =
    ListOp(value.map(opA => opA.map(f)))

  def flatMap[B](f: A => ListOp[B]): ListOp[B] =
    ListOp(value.flatMap(op =>
      op match {
        case Some(a) => f(a).value
        case None    => List(None)
    }))
}

object DataBase {

  def findUserById(userId: String): Future[Option[User]] =
    Future(throw new Exception("Error"))

  def findAddressByUser(user: User): Future[Option[Address]] =
    Future.successful(Some(Address("123456", user)))

  def findAddressByUserId(userId: String): Future[Option[Address]] =
    (for {
      user <- {
        println(s"Finding User by userId: $userId")
        FutOpt(findUserById(userId))
      }
      _ = println(s"User is $user")
      address <- {
        println(s"Finding Address by User: $user")
        FutOpt(findAddressByUser(user))
      }
      _ = println(s"Address is $address")
    } yield address).value

  def findAddressByUserIdWithCats(userId: String): Future[Option[Address]] =
    (for {
      user <- {
        println(s"Finding User by userId: $userId")
        OptionT(findUserById(userId))
      }
      _ = println(s"User is $user")
      address <- {
        println(s"Finding Address by User: $user")
        OptionT(findAddressByUser(user))
      }
      _ = println(s"Address is $address")
    } yield address).value

}

object ExercicesClass14 extends App {

  val db = DataBase
  val c = db.findAddressByUserId("1234").recover {
    case ex => println(s"Exception $ex")
  }
  val d = db.findAddressByUserIdWithCats("1234").recover {
    case ex => println(s"Exception $ex")
  }

  val y = Await.result(d, 2 seconds)

}
