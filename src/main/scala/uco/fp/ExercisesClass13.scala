package uco.fp

import scala.util.Try

object ExercisesClass13 {

  trait Monad[F[_]] {
    def pure[A]: A => F[A]
    def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B]
  }

  object Monad {
    def apply[F[_]: Monad]: Monad[F] = implicitly[Monad[F]]
  }

  object MonadInstances {

    implicit def option = new Monad[Option] {
      def pure[A]: A => Option[A] = Option.apply
      def flatMap[A, B](fa: Option[A])(f: A => Option[B]): Option[B] =
        fa flatMap f
    }

    type either[A] = Either[String, A]

    implicit def either = new Monad[either] {
      def pure[A]: A => either[A] = Right(_)
      def flatMap[A, B](fa: either[A])(f: A => either[B]): either[B] =
        fa.flatMap(f)
    }

    implicit def toTry = new Monad[Try] {
      override def pure[A]: A => Try[A] = Try(_)
      override def flatMap[A, B](fa: Try[A])(f: A => Try[B]): Try[B] =
        fa flatMap f
    }

    /*implicit def toTry = new Monad[Try] {
      override def pure[A]: A => Try[A] = Try(new Exception("ex"))
      override def flatMap[A, B](fa: Try[A])(f: A => Try[B]): Try[B] =
        fa flatMap f
    } */

  }
  object MonadLaws {

    //def f[A, F[_]]: A => F[A]
    //def g[A, F[_]]: A => F[A]

    def leftIdentity[A, F[_]: Monad](x: A)(f: A => F[A]): Boolean =
      Monad[F].flatMap(Monad[F].pure(x))(f) == f(x)

    def rightIdentity[A, F[_]: Monad](x: A): Boolean =
      Monad[F].flatMap(Monad[F].pure(x))(Monad[F].pure) == Monad[F].pure(x)

    def associativity[A, F[_]: Monad](x: A)(f: A => F[A])(
        g: A => F[A]): Boolean = {
      val m = Monad[F]
      val ap = m.pure(x)
      m.flatMap(ap)(a => m.flatMap(f(a))(g)) == m.flatMap(m.flatMap(ap)(f))(g)
    }
    /*def isAssociaty[V, F[_]](x: V, m: Monad[F], f: V => F, g: V => F)(
        implicit mo: Monad[F]): Boolean = {
      val leftIdentityLaw = mo.flatMap(mo.pure(f(x)))
      val rightIdentityLaw = mo.flatMap(mo.pure(x)) == m.pure(x)
      //val associatvityLaw = m.flatMap(f)
      ???
    }*/
  }

  object Test {
    import MonadInstances._
    import MonadLaws._

  }
}
