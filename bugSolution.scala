```scala
class MyClass[T](val value: T) {
  def myMethod(other: MyClass[T])(implicit ev: T => Numeric[T]): T = {
    implicitly[Numeric[T]].plus(value, other.value)
  }
}

object Main extends App {
  val int1 = new MyClass(10)
  val int2 = new MyClass(5)
  println(int1.myMethod(int2)) // Works fine

  val str1 = new MyClass("Hello")
  val str2 = new MyClass(" World")
  // Doesn't compile, showing the compiler's type checking
  // println(str1.myMethod(str2))

  //To make this work with Strings, you'll need a different approach
  // Example using a typeclass for String concatenation
  implicit val stringNumeric = new Numeric[String] {
    override def plus(x: String, y: String): String = x + y
    override def minus(x: String, y: String): String = x //Implementation is arbitrary
    override def times(x: String, y: String): String = x //Implementation is arbitrary
    override def negate(x: String): String = x //Implementation is arbitrary
    override def fromInt(x: Int): String = x.toString
    override def toInt(x: String): Int = x.toInt
    override def toLong(x: String): Long = x.toLong
    override def toDouble(x: String): Double = x.toDouble
    override def toFloat(x: String): Float = x.toFloat
  }
  println(str1.myMethod(str2))

  // The following will now give a compile time error,
  // preventing the runtime ClassCastException
  //val mixed1 = new MyClass(10)
  //val mixed2 = new MyClass("test")
  //println(mixed1.myMethod(mixed2))
}
```