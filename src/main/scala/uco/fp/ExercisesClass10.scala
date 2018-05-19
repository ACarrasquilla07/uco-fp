package uco.fp

object ExercisesClass10 {

  //Functors
  //https://github.com/debasishg/frdomain/blob/master/src/main/scala/frdomain/ch4/patterns/Functor.scala

  trait Functor[F[_]] {
    def map[A, B](a: F[A])(f: A => B): F[B]
  }

  object Functor {
    /*def apply[F: Functor]: Functor[F] =
      implicitly[Functor[F]]*/

    implicit def ListFunctor: Functor[List] = new Functor[List] {
      def map[A, B](a: List[A])(f: A => B): List[B] = a map f
    }

    implicit def OptionFunctor: Functor[Option] = new Functor[Option] {
      def map[A, B](a: Option[A])(f: A => B): Option[B] = a map f
    }

    implicit def Tuple2Functor[A1]: Functor[({ type f[x] = (A1, x) })#f] =
      new Functor[({ type f[x] = (A1, x) })#f] {
        def map[A, B](a: (A1, A))(f: A => B): (A1, B) = (a._1, f(a._2))
      }

    implicit def SeqFunctor: Functor[Seq] = new Functor[Seq] {
      def map[A, B](a: Seq[A])(f: A => B): Seq[B] = a map f
    }
  }
}
