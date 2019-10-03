# Scala
Known as functional language but supports some object-oriented programming techniques

##Beginner Scala

To run application: 
> scala filename.scala


### Print
println(....)

### Variable Assignment
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
> AND
true && true    ```-> true```
true && false   ```-> false```
>

> OR
true || false   ```-> true``` 
>

> EQUAL
"bob" == "bob"  ```-> true```
0 == false      ```-> false, returns error saying cannot compare int to boolean```
>

40.getClass             ```-> returns Int```
99.isInstanceOf[Int]    ```-> true```
var f = "4.44".toFloat  ```-> string converted to float```
77.toString == "77"     ```-> true```


### Expressions
#### Blocks
- expressions can be combined by surrounding them with {}. This is called a ```block```
- last expression in the block is result of the overall block

println {
    "hi"
}                       ```-> printing multi```
println{
    {"hi}
}

println{
    {
        var x = 10
        x*5
    }
}                        ```-> 50. last statement is returned``` 

println{
    {var x = 10; x*5}
}                         ```-> 50. last statement is returned``` 

### Conditionals
if (5>1){
    println("hi")
    } else {
        println("bye")    
}

if (true) println("wow")


---

var ttt = {
  var first = "katy"
  s"$first perry"
}                                           ```-> returns "katy perry"```
var source = "hotline"
var badsource = {
  s"$source bling"
}                                           ```-> returns "hotline bling"```
var sources = {
  s"$first hello"
}                                           ```-> ERROR. Variables defined within an expression are local to the expression 
                                                and cannot be accessed outside the expression.```


### Scala Functions
def scalFun = ":)"
```\> scalFun```                                   ```-> returns :)```

def tall = {
    "alt"
    }
```> tall```                                        ```-> returns alt```

def melike(thing: String): String = {
        s"I like $thing"
    }
    println(melike("cat"))                          ```-> returns I like cat```


def coolthing(n: String = "po"): String = {
       s"$n is awesome"
}
println(coolthing())                                ```-> returns po is awesome. "po" is default value which is used when no input is sent```
println(coolthing("boooo"))                         ```-> retruns boooo is awesome```


def howdy() = "hi"                                  ```-> retruns hi and mount```
def view = "mount"                                  ```-> scala does not require parenthesis```

>When functions are defined on a single line, brackets {} are not required. 
Multiline functions require brackets.

def airplane(company: String): String = {
  s"The plane belongs to $company"
}
println(airplane(company = "Delta"))                ```-> Returns The plabe belongs to Delta```


---
## Object-Oriented Scala
class User(n: String){
    val name: String = n
}
var u = new User(n = "Frank")                       ```u = "Frank"```
\> u                                                   ```User = User!606d... tells location```
\> u.name                                              ```String = Frank```


class Cat(val nam: String){
    def greet: String = {
        s"My name is $nam"
    }
}
var myCat = new Cat(name = "garfield")
println{myCat.greet}                                         ```-> My name is garfield```


class Singer(var name: String)
val m = new Singer("mi")
val b = new Singer("brad")
val singers = List(m,b)
println {
    singers.map { (s: Singer) =>
    s.name
    }
}                                                       ```-> List(mi.brad)```


class Mango(var status: String)
val myMango = new Mango(status = "ripe")
myMango.status                                          ```-> ripe```
myMango.status = "rotten"                               ```could be updated because we defined it in class using var keyword```
myMango.status                                          ```-> rotten```

##### inheritance
> Scala allows multiple inheritance

class A {
    def hi = "Hello from A"
}
class B extends A                                       ```-> inherits from A```
var bInstance = new B
println(bInstance.hi)                                   ```-> Hello from A```

##### super & override
class Tree {
    def about = "I am made of wood"
}
class Pine extends Tree {
    override def about = super.about + " and stickly"    ```about = I am made of wood and sticky```
}
var aPine = new Pine
println(aPine.about)                                    ```-> I am made of wood and stickly```


class Singer(var alias: String)
var singers = List(
  new Singer("miley"),
  new Singer("bradley")
)                                                       ```-> List[String] = List(miley, bradley) ```
singers map(_.alias)                                    ```uses shortcut wildcard syntax```
                                                        ```println {
                                                             singers.map { (s: Singer) =>
                                                               s.alias
                                                             }
                                                           }```


### Abstract classes
abstract class Mammal {                                 ```cannot be instantiated directly. Only inheret from it```
  val warmBlooded: Boolean = true
}
class Pig extends Mammal
var missPiggy = new Pig
println(missPiggy.warmBlooded)                          ```-> true```


### Method Overloading
class Whatever {
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
class Cloud {
  def apply() = {
    "floating"
  }
}
var c = new Cloud
println(c.apply())                                      ```used when no method is specified```
println(c())

> apply method is special and is called by default if the method name is not specified. 
> s.apply() -> "shining" :the apply method was called



### Singleton
object Cake {
    var base = "cook"
    var sauce = "butter"
    def apply() = {
        s"Give me $base and  $sauce"
    }
}
println(Cake.apply())                           ```-> Give me cook and butter```
println(Cake())                                 ```-> give me cook and butter. apply() method is special```


> traits - similar to abstract classes. Can be inherited (multiple inheritance aka single object inherit from multiple inheritance) but not instaniated

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
println(Spiderman.describe)                               ```-> I can run really fast and jump really high is printed.```

object Pikachu {
  def superpower = "electricity"
}
var p = new Pikachu
println(p.superpower)                                       ```Error! Pikachu is an object and cannot be instantiated. new Pikachu causes the error```

trait Hyper {
  var how = "Give caffeine"
}
class Coffee extends Hyper
var myCup = new Coffee
println(myCup.how)                                          ```-> Give caffeine```

trait X {
  var yyy = "hi"
}
trait Y {
  var yyy = "bye"
}
object Moo extends X with Y
println(Moo.yyy)                                            ```Error. yyy is within both parent classes and thus scala gets confused```


### Functions
- expressions that take parameters
> val addOne = (x: Int) => x + 1                //1 parameter
> val add = (x: Int, y: Int) => x + y           //multiple parameter
> val getTheAnswer = () => 42                   //no parameter

### Methods
- similar to functions except defined by ```def``` keyword and followed by name, parameters, retrun type and body.
- def + name + (parameters) + : + return type + = + body
- last line in body is method's return value
> def add(x: Int, y: Int): Int = x + y                                                      //parameters 
> def name: String = System.getProperty("user.name")                                        //no parameters


### Classes
- class + name + (constructor parmeters)

class Greeter(prefix: String, suffix: String) {
  def greet(name: String): Unit =
    println(prefix + name + suffix)
}



## Bibliography
https://www.codequizzes.com/scala/tutorial
https://docs.scala-lang.org/tour/basics.html