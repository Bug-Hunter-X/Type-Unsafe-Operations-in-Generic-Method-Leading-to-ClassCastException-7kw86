```scala
class MyClass[T](val value: T) {
  def myMethod(other: MyClass[T]): T = {
    value match {
      case i: Int => i + other.value.asInstanceOf[Int] // This is unsafe
      case s: String => s + other.value.asInstanceOf[String] // This is unsafe
      case _ => throw new IllegalArgumentException("Unsupported type")
    }
  }
}

object Main extends App {
  val int1 = new MyClass(10)
  val int2 = new MyClass(5)
  println(int1.myMethod(int2)) // Works fine

  val str1 = new MyClass("Hello")
  val str2 = new MyClass(" World")
  println(str1.myMethod(str2)) // Works fine

  val mixed1 = new MyClass(10)
  val mixed2 = new MyClass("test")
  println(mixed1.myMethod(mixed2)) // ClassCastException at runtime
}
```