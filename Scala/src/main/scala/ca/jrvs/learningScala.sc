val a = "abc"

var b = "123"
b = "234"

var c: Int = 0

var t = true
var f: Boolean = false

10 < 3
5 != 5
5 == 5
true && false
5 < 6 && 10 == 10
false || true
40.getClass
99.isInstanceOf[Int]
var fl = "4.44".toFloat
77.toString == "77"


var num1 = {val x = 2 * 2; x + 40}

var ttt = {
  var first = "katy"
  s"$first perry"
}

var source = "hotline"
var badsource = {
  s"$source bling"
}


var band ={
  var name1 = "sub"
  name1
}

if (10 > 2) { println("ten is greater than two") }


def howdy() = "hi"
def view = "mount"

def pi(number: Double): String = {
  s"${number.toString} is cool"
}
println(pi(3.14))
def fullN(first: String, last: String = "b"): String = {
  s"$first $last"
}
println(fullN("j"))

def airplane(company: String): String = {
  s"The plane belongs to $company"
}
println(airplane(company = "Delta"))



class User(n: String) {
  val name: String = n
}
var u = new User(n = "Frank")
u.name

class Carrot(val flavor: String)
var c2 = new Carrot(flavor = "weird")
c2.flavor
c2 = new Carrot("hel")
c2.flavor;


class Singer(var alias: String)
var singers = List(
  new Singer("miley"),
  new Singer("bradley")
)
singers map(_.alias)

class A {
  def hi = "Hello from A"
}
class B extends A
var bInstance = new B
bInstance.hi

class Tree {
  def about = "I am made of wood"
}
class Pine extends Tree {
  override def about = super.about + " and sticky!"
}
var aPine = new Pine
aPine.about


class Mango(var status: String) {
  def updateStatus(s: String): Unit = {
    status = s
  }
}
var myMango = new Mango("ripe")
myMango.status
myMango.updateStatus("rotten")
myMango.status

class Person(var firstName: String, var lastName: String) {
  def fullName = {
    s"$firstName $lastName"
  }
}
var bob = new Person("bob", "loblaw")
bob.fullName


class Cloud {
  def apply() = {
    "floating"
  }
}
var c3 = new Cloud
c3.apply()
c3()


object Hello {
  def speak = "sup"
}
Hello.speak

class Simpson {
  var color = "yellow"
}
object Bart extends Simpson {
  def speak = {
    s"I am $color"
  }
}
Bart.speak

object Cake {
  var base = "cook"
  var sauce = "peanut"
  def apply() = {
    s"Give me $base and $sauce"
  }
}
Cake()

trait Cool {
  var speak = "I am groo"
}
object JoeCamel extends Cool
JoeCamel.speak

trait Hyper {
  var how = "Give caffe"
}
class Coffee extends Hyper
var myCup = new Coffee
myCup.how

trait Speed {
  def run = {
    "really fast"
  }
}
trait Jump {
  def leap = {
    "really high"
  }
}
object Spiderman extends Speed with Jump {
  def describe = {
    s"I can run $run and jump $leap"
  }
}
Spiderman.describe


class Greet(pre: String, suf: String) {
  def greet(name: String): Unit =
    println(pre + name + suf)
}

class Greet1 {
  def greet(name: String): Unit = {
    println("bye")
    println("hello")
    s"h"
  }
}
val hey = new Greet1()
hey.greet("maya")


class Greet2 {
  def greet(name: String): String = {
    println("bye")
    println("hello")
    s"h"
  }
}
val hey1 = new Greet2()
hey1.greet("maya")

case class Point(x: Int, y: Int)
val point =  Point(1,2)
val point1 = Point(1,3)
if (point == point1){
  println(point + "&" + point1 + ": true")
} else {
  println("false")
}


object IdFactory {
  private var counter = 0
  def create(): Int = {
    counter += 1
    counter
  }
}
val newId: Int = IdFactory.create()
val newerId: Int = IdFactory.create()


trait Greeter {
  def greet(name: String): Unit =
    println("Hello, " + name + "!")
}
class DefaultGreeter extends Greeter
class CustomizableGreeter(prefix: String, postfix: String) extends Greeter {
  override def greet(name: String): Unit = {
    println(prefix + name + postfix)
  }
}
val greeter = new DefaultGreeter()
greeter.greet("Scala developer")
val customGreeter = new CustomizableGreeter("How are you, ", "?")
customGreeter.greet("Scala developer")






val list: List[Any] = List(
  "a string",
  732,
  'c',
  true,
  () => "an anonymous function returning a string"
)
list.foreach(element => println(element))


val x4: Long = 987654321
val y4: Float = x4
val face: Char = '☺'
val number: Int = face
val x5: Long = 987654321
val y5: Float = x5


class Point_1(var x: Int, var y: Int) {
  def move(dx: Int, dy: Int): Unit = {
    x = x + dx
    y = y + dy
  }
  override def toString: String =
    s"($x, $y)"
}
val point_1 = new Point_1(2, 3)
point_1.x
println(point_1)


class Point3(var x: Int = 2, var y: Int = 1)
val origin = new Point3
val point3 = new Point3(4)
val point4 = new Point3(y=2)
println(origin.x)
println(point3.x)
println(point4.y)

class Point5 {
  private var _x = 0
  private var _y = 0
  private val bound = 100
  def x = _x
  def x_= (newValue: Int): Unit = {
    if (newValue < bound) _x = newValue else printWarning
  }
  def y = _y
  def y_= (newValue: Int): Unit = {
    if (newValue < bound) _y = newValue else printWarning
  }
  private def printWarning = println("WARNING: Out of bounds")
}
val point5_ = new Point5
point5_.x = 99
point5_.y = 101

class Point6(x: Int, y: Int)
val point6 = new Point(1, 2)
point6.x


trait Iterator[A] {
  def hasNext: Boolean
  def next(): A
}
class IntIterator(to: Int) extends Iterator[Int] {
  private var current = 0
  override def hasNext: Boolean = current < to
  override def next(): Int = {
    if (hasNext) {
      val t = current
      current += 1
      t
    } else 0
  }
}
val iterator = new IntIterator(10)
iterator.next()
iterator.next()

import scala.collection.mutable.ArrayBuffer
trait Pet {
  val name: String
}
class Cat(val name: String) extends Pet
class Dog(val name: String) extends Pet
val dog = new Dog("Harry")
val cat = new Cat("Sally")
val animals = ArrayBuffer.empty[Pet]
animals.append(dog)
animals.append(cat)
animals.foreach(pet => println(pet.name))


val ingredient = ("Sugar" , 25)
ingredient._1
ingredient._2
val (name, quantity) = ingredient
name
quantity


val planets = List(("Mercury", 57.9), ("Venus", 108.2), ("Earth", 149.6),
    ("Mars", 227.9), ("Jupiter", 778.3))
planets.foreach{
  case ("Earth", distance) =>
    println(s"Our planet is $distance million kilometers from the sun")
  case _ => println("hello")
}
val numPairs = List((2, 5), (3, -7), (20, 56))
for ((a, b) <- numPairs) {
  println(a+ "," +b)
}

abstract class A1 {
  val message3: String
}
class B1 extends A1 {
  val message3 = "I'm an instance of class B"
}
trait C1 extends A1 {
  def loudMessage = message3.toUpperCase()
}
class D1 extends B1 with C1

val d = new D1
d.message3
d.loudMessage


abstract class AbsIterator {
  type T
  def hasNext: Boolean
  def next(): T
}
class StringIterator(s: String) extends AbsIterator {
  type T = Char
  private var i = 0
  def hasNext = i < s.length
  def next() = {
    val ch = s charAt i
    i += 1
    ch
  }
}
trait RichIterator extends AbsIterator {
  def foreach(f: T => Unit): Unit =
    while (hasNext)
      f(next())
}
class RichStringIter extends StringIterator("Scala") with RichIterator
val richStringIter = new RichStringIter
richStringIter foreach println
