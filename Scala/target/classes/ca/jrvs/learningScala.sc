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