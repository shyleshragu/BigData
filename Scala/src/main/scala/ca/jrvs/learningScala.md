# Scala
Known as functional language but supports some object-oriented programming techniques

## Beginner Scala

To run application: 
> scala filename.scala


### Print
println(....)

println(1)                  ```-> 1```
println(1 + 1)              ```-> 2```
println("Hel")              ```-> Hel```
println("Hel," + " wor")    ```-> Hel, wor```


### Variable Assignment
var a = 1 + 1   ```-> a=2```
val a = 1 + 1   ```-> a=2```

#### Var
var x: Int = 99         ```-> variable x of type: Int (identifier), Value: 99```
var y = 33              ```-> scala is smart so it recognizes that it is a number``` 
                        ```-> variable y of type: Int, Value 33```

var cat = "garfield"
cat = "puss in boots" 
cat = 44                ```-> value can change as long as it is same type as scala is strongly typed language```

#### Value
val pi = 3.14
pi = 9.4                ```-> reassignment of pi not allowed. Value is final on initialization``` 

### Boolean & Logical Comparison
var cool: Boolean = true        ```-> declaration```
println(10 > 3) ```-> true```
13 != 13        ```-> false```

*AND*
true && true    ```-> true```
true && false   ```-> false```


*OR*
true || false   ```-> true``` 


*EQUAL*
"bob" == "bob"  ```-> true```
0 == false      ```-> false, returns error saying cannot compare int to boolean```

40.getClass             ```-> returns Int```
99.isInstanceOf[Int]    ```-> true```
var f = "4.44".toFloat  ```-> string converted to float```
77.toString == "77"     ```-> true```


### Expressions
#### Blocks
- expressions can be combined by surrounding them with {}. This is called a ```block```
- last expression in the block is result of the overall block

> println {
    "hi"
} 
>                      ```-> printing hi```

> println{
    {"hi"}
> }

> println{
    {
        var x = 10
        x*5
    }
}                        ```-> 50. last statement is returned``` 


> println{
    {var x = 10; x*5}
}                         ```-> 50. last statement is returned``` 

### Conditionals
> if (5>1){
    println("hi")
    } else {
        println("bye")    
}

> if (true) println("wow")

---

> var ttt = {
  var first = "katy"
  s"$first perry"
}                                           ```-> returns "katy perry"```
var source = "hotline"
var badsource = {
  s"$source bling"
}                                           ```-> returns "hotline bling"```
var sources = {
  s"$first hello"
}                                           ```-> ERROR. Variables defined within an expression are local to the expression and cannot be accessed outside the expression.```
### Functions
- expressions that take parameters
> val addOne = (x: Int) => x + 1                //1 parameter
> val add = (x: Int, y: Int) => x + y           //multiple parameter
> val getTheAnswer = () => 42                   //no parameter


> def scalFun = ":)"
```\> scalFun```                                   ```-> returns :)```

> def tall = {
    "alt"
    }
```> tall```                                        ```-> returns alt```

> def melike(thing: String): String = {
        s"I like $thing"
    }
    println(melike("cat"))                          ```-> returns I like cat```


> def coolthing(n: String = "po"): String = {
       s"$n is awesome"
}
println(coolthing())                                ```-> returns po is awesome. "po" is default value which is used when no input is sent```
println(coolthing("boooo"))                         ```-> retruns boooo is awesome```


> def howdy() = "hi"                                  ```-> retruns hi and mount```
def view = "mount"                                  ```-> scala does not require parenthesis```

When functions are defined on a single line, brackets {} are not required. 
Multiline functions require brackets.

> def airplane(company: String): String = {
  s"The plane belongs to $company"
}
println(airplane(company = "Delta"))                ```-> Returns The plabe belongs to Delta```

#### Higher Order Functions
- take other functions as parameters or return a function
- possible because functions are first-class values in scala
> val salaries = Seq(20000, 70000, 40000)
  val doubleSalary = (x: Int) => x * 2
  val newSalaries = salaries.map(doubleSalary)                  ```map -> higher order function! doubleSalary gets applied to each element in list salaries```

> val salaries = Seq(20, 70, 40)
  val newSalaries = salaries.map(x => x * 2)                     ```List(40,140,80)```
  val newSalaries2 = salaries.map(_*2)                           ```List(40,140,80)```

##### Methods into function
- pass methods as arguments to higer-order functions
- because, Scala compiler will coerce the method into a function
> case class WeeklyWeatherForecast(temperatures: Seq[Double]) { 
    private def convertCtoF(temp: Double) = temp * 1.8 + 32
    def forecastInFahrenheit: Seq[Double] = temperatures.map(convertCtoF)       ```passing the method convertCtoF```
  }

### Methods
- similar to functions except defined by ```def``` keyword and followed by name, parameters, retrun type and body.
- def + name + (parameters) + : + return type + = + body
- last line in body is method's return value
- 1 parameter, multiple parameter, no parameter
> def add(x: Int, y: Int): Int = x + y                                                      //parameters 
> def name: String = System.getProperty("user.name")                                        //no parameters


### Classes
- class + name + (constructor parmeters)

> class Greeter(prefix: String, suffix: String) {
  def greet(name: String): Unit =
    println(prefix + name + suffix)
}
val greeter = new Greeter("he", "man")                      ```making an instance of a class```
greeter.greet("the great")                                  ```-> he the great man. no return```


#### Return type: Unit
- no return. 
- Similar to void in Java and C.

> class Greet(pre: String, suf: String){
>   def greet(name: String): Unit =
>      s"hel" 
>}                                                                  ```-> no return```


> class Greet1 {
    def greet(name: String): Unit = {
      println("bye")
      println("hello")
      s"h"                                                          ```-> no return.```
    }
  }
  val hey = new Greet1()
  hey.greet("maya")                                                 ```-> prints: bye hello```

- written() - singleton value of type Unit


#### Case class
- immutable and compared by value
- can be instantiated without use of new keyword

> case class Point(x: Int, y: Int)
> val point = Point(1,2)                    ```instantiated without new keyword```
> val point1 = Point(1,3)
> if (point == point1){
>   println(point + "&" + point1 + ": true")
>} else {
>   println("false")
>}


#### Object
- basically singletons of their own objects
- single instances of their own definitions

> object IdFactory {
>   private var counter = 0
>   def create(): Int = {
>       counter += 1
>       counter
>   }                                               ```returns counter```
>}
>val newID: Int = IdFactory.create()
>println(newID)                                     ```1```
>val newID2: Int = IdFactory.create()
>println(newID2)                                    ```2```


> object Cake {
    var base = "cook"
    var sauce = "butter"
    def apply() = {
        s"Give me $base and  $sauce"
    }
}
println(Cake.apply())                           ```-> Give me cook and butter```
println(Cake())                                 ```-> give me cook and butter. apply() method is special```


#### traits 
- similar to abstract classes. 
- Can be inherited (multiple inheritance aka single object inherit from multiple inheritance) but not instaniated
- can have default implementations
- used to share interfaces and fields between classes
- cannot be instantiated thus no parameters
- no parameters

> trait Speed {
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
println(Spiderman.describe)                               ```-> I can run really fast and jump really high is printed.```

> object Pikachu {
  def superpower = "electricity"
}
var p = new Pikachu
println(p.superpower)                                       ```Error! Pikachu is an object and cannot be instantiated. new Pikachu causes the error```

> trait Hyper {
  var how = "Give caffeine"
}
class Coffee extends Hyper
var myCup = new Coffee
println(myCup.how)                                          ```-> Give caffeine```

> trait X {
  var yyy = "hi"
}
trait Y {
  var yyy = "bye"
}
object Moo extends X with Y
println(Moo.yyy)                                            ```Error. yyy is within both parent classes and thus scala gets confused```


>trait Iterator[A] {
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
iterator.next()                                             ```0```
iterator.next()                                             ```1```

##### Mixins
- traits which are used to compose a class
- classes can have only one superclass and many mixins
- keyword: ```extends``` and ```with```
> abstract class A {
    val message: String
  }
  class B extends A {
    val message = "I'm an instance of class B"
  }
  trait C extends A {
    def loudMessage = message.toUpperCase()
  }
  class D extends B with C 
  val d = new D
  println(d.message)                            ```I'm an instance of class B```
  println(d.loudMessage)                        ```I'M AN INSTANCE OF CLASS B```
- class D has a superclass B and mixin C


> abstract class AbsIterator {                   ```a class with abstract type and standard iterator methods```
    type T
    def hasNext: Boolean
    def next(): T
  }
  class StringIterator(s: String) extends AbsIterator {     ```concrete class with all abstract members```
    type T = Char                                           ```Used to iterate over the s: String```
    private var i = 0
    def hasNext = i < s.length
    def next() = {
      val ch = s charAt i
      i += 1
      ch
    }
  }
  trait RichIterator extends AbsIterator {          ```continously calls function f: T => Unit on next() element while(hasNext) is true```
    def foreach(f: T => Unit): Unit =
      while (hasNext)
        f(next())
  }
  class RichStringIter extends StringIterator("Scala") with RichIterator    ```combined functionality of StringIterator and RichIterator into 1 class```
>                                                                           ```for RichStringIter: StringIterator is superclass and RichIterator is mixin```
  val richStringIter = new RichStringIter
  richStringIter foreach println

#### Subtyping
>import scala.collection.mutable.ArrayBuffer
 trait Pet {
   val name: String
 }
 class Cat(val name: String) extends Pet
 class Dog(val name: String) extends Pet
 val dog = new Dog("Har")
 val cat = new Cat("Sal")
 val animals = ArrayBuffer.empty[Pet]
 animals.append(dog)
 animals.append(cat)
 animals.foreach(pet => println(pet.name))                  ```Har Sal```

---

> class Point_1(var x: Int, var y: Int) {
  def move(dx: Int, dy: Int): Unit = {
    x = x + dx
    y = y + dy
  }
  override def toString: String =
    s"($x, $y)"
}
val point_1 = new Point_1(2, 3)
point_1.x                                                   ```2```
println(point_1)                                            ```Point(2,3)```


### Constructors
> class Point3(var x: Int = 2, var y: Int = 1)
  val origin = new Point3
  val point3 = new Point3(4)
  val point4 = new Point3(y=2)
  println(origin.x)                                         ```2```
  println(point3.x)                                         ```4```
  println(point4.y)                                         ```2```

### Private
- parameters without val or var are private values

> class Point {
  private var _x = 0
  private var _y = 0
  private val bound = 100
  def x = _x
  def x_= (newValue: Int): Unit = {                 //validation & setting value
    if (newValue < bound) _x = newValue else printWarning
  }
  def y = _y
  def y_= (newValue: Int): Unit = {                 //validation & setting value
    if (newValue < bound) _y = newValue else printWarning
  }
  private def printWarning = println("WARNING: Out of bounds")
}
val point1 = new Point
point1.x = 99                                               ```x = 99```
point1.y = 101                                              ```Warning: Out of bound. y = 0```

> class Point(x: Int, y: Int)
val point = new Point(1, 2)
point.x                                                     ```Error. does not compile```

---
## Object-Oriented Scala
> class User(n: String){
    val name: String = n
}
var u = new User(n = "Frank")                       ```u = "Frank"```
\> u                                                   ```User = User!606d... tells location```
\> u.name                                              ```String = Frank```


> class Cat(val nam: String){
    def greet: String = {
        s"My name is $nam"
    }
}
var myCat = new Cat(name = "garfield")
println{myCat.greet}                                         ```-> My name is garfield```


> class Singer(var name: String)
val m = new Singer("mi")
val b = new Singer("brad")
val singers = List(m,b)
println {
    singers.map { (s: Singer) =>
    s.name
    }
}                                                       ```-> List(mi.brad)```


> class Mango(var status: String)
val myMango = new Mango(status = "ripe")
myMango.status                                          ```-> ripe```
myMango.status = "rotten"                               ```could be updated because we defined it in class using var keyword```
myMango.status                                          ```-> rotten```

##### inheritance
Scala allows multiple inheritance

> class A {
    def hi = "Hello from A"
}
class B extends A                                       ```-> inherits from A```
var bInstance = new B
println(bInstance.hi)                                   ```-> Hello from A```

##### super & override
> class Tree {
    def about = "I am made of wood"
}
class Pine extends Tree {
    override def about = super.about + " and stickly"    ```about = I am made of wood and sticky```
}
var aPine = new Pine
println(aPine.about)                                    ```-> I am made of wood and stickly```


> class Singer(var alias: String)
> var singers = List(
>   new Singer("miley"),
>   new Singer("bradley")
> )                                                       ```-> List[String] = List(miley, bradley) ```
> singers map(_.alias)                                    ```uses shortcut wildcard syntax```
>                                                         ```println {
>                                                              singers.map { (s: Singer) =>
>                                                                s.alias
>                                                              }
>                                                            }```
>                                                         ```


### Abstract classes
> abstract class Mammal {                                 ```cannot be instantiated directly. Only inheret from it```
  val warmBlooded: Boolean = true
}
class Pig extends Mammal
var missPiggy = new Pig
println(missPiggy.warmBlooded)                          ```-> true```


### Method Overloading
> class Whatever {
  def aaa(s: String): String = {
    "I like words"
  }
  def aaa(i: Int): String = {
    "Numbers are fun"
  }
}
var w = new Whatever
println(w.aaa("hi"))                                    ```-> I like workds```
println(w.aaa(34))                                      ```-> Numbers are fun```

### Apply method
> class Cloud {
  def apply() = {
    "floating"
  }
}
var c = new Cloud
println(c.apply())                                      ```used when no method is specified```
println(c())

Apply method is special and is called by default if the method name is not specified. 
> s.apply() -> "shining" :the apply method was called


- extends and override keyword
>  trait Greeter {
     def greet(name: String): Unit =
       println("Hello, " + name + "!")
   }
> class DefaultGreater extends Greeter
>
> class CustomizableGreeter(pre: String, pos: String) extends Greeter {
>   override def greet(name: String)): Unit = {
>       println(pre + name + pos)
>   }
>}
>val greeter = new DefaulrGreeter()
>greeter.greet("scala")                                                 ```Hello, scala!```
>val customGreet = new CustomizableGreeter("aa","bb")
>customGreeter.greet("developer")                                       ```aa developer bb```

---
### Scala Type Hierarchy
#### Any
- top type
- supertype of all types
- two direct subclasses:
1. AnyVal
2. AnyRef

##### AnyVal
- represents value types
- 9 non-nullable predefined value types:
1. Double
2. Float
3. Long
4. Int
5. Short
6. Byte
7. Char
8. Unit
9. Boolean


##### AnyRef
- represents reference types
- all non-value types

> val list: List[Any] = List(
  "a string",
  732,                                                          ```integer```
  'c',                                                          ```character```
  true,                                                         ```boolean```
  () => "an anonymous function returning a string"
)
list.foreach(element => println(element))                       ```732
                                                                   c
                                                                   true
                                                                   <function0>```

### Casting
- unidirectional
byte -> short -> int -> long -> float -> double
char -> int

> val x: Long = 987654321
  val y: Float = x                                              ```9.8765434E8 ... precision lost```
  val face: Char = '☺'
  val number: Int = face                                        ```9786```
  val x1: Long = 987654321
  val y1: Float = x1                                            ```9.8765434E8```
  val z1: Long = y1                                             ```error! Does not conform```


### Nothing and Null
#### Nothing
- bottom type
- subtype of all types
- there is no value with type Nothing
- common use: signal non-termination
eg. thrown exception, program exit or an infinte loop
i.e: type of expression which does not evaluate to a value or method that does not return normally

#### Null
- subtype of all reference types i.e. subtype of AnyRef
- keyword: null
- ideally not used in scala


### Tuples
- a value that contains a fixed number of elements each with a distinct type
- immutable
- series of classes: Tuple2, Tuple3 ... Tuple22. Each class has as many type parameters as it has elements

eg. tuples with 2 elements (a String and Int element) -> Tuple2[String, Int]
> val ingredient = ("Sugar", 25)

#### Accessing elements
- use position
> println(ingredient._1)            ```Sugar```
> println(ingredient._2)             ```25```

._1 -> 1st element
._2 -> 2nd element

#### Pattern matching
> val (name, quantity) = ingredient     ```inferred types => name: String, quantity: Int```
> println(name)                         ```Sugar```
> println(quantity)                     ```25```

> val planets = List(("Mercury", 57.9), ("Venus", 108.2), ("Earth", 149.6),
    ("Mars", 227.9), ("Jupiter", 778.3))
planets.foreach{
  case ("Earth", distance) =>
    println(s"Our planet is $distance million kilometers from the sun")
  case _ => println("hello")
}                                                                  ```hello
                                                                      hello
                                                                      Our planet is 149.6 million kilometers from the sun
                                                                      hello
                                                                      hello```

>val numPairs = List((2, 5), (3, -7), (20, 56))
for ((a, b) <- numPairs) {
  println(a * b)
}                                                                    ```10 -> 2x5
                                                                        -21 -> 3x-7
                                                                        1120 -> 20x56```

#### Tuples vs Case Classes
- case classes have named elements whereas tuples do not
eg.
> case class Planet(name:String, distance: Double)

> val planet = ("earth", "24")



## Bibliography
https://www.codequizzes.com/scala/tutorial
https://docs.scala-lang.org/tour/basics.html
https://docs.scala-lang.org/tour/unified-types.html

