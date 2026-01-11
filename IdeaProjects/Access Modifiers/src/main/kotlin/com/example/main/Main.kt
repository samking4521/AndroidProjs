package com.example.main
import com.example.classes.*

fun main() {
   val user = TestModifiers()
   val print = Cool()
   print.printInfo()

//   user.name = "Tesla"
   user.surname = "Cool"
   getData()
   mainClasses()
   user.getName()
}

open class TestModifiers {
   protected var name: String = "Tesla"
   var surname: String = ""

   fun getName(){
      name = "Hyundai"
      println("This car is an $name")
   }
}

class Cool: TestModifiers(){
   fun printInfo(){
      println(name)
   }
}