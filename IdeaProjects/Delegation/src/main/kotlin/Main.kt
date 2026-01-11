fun main(){
    val Car1 = Engine()
    Car1.println1()
        Car1.println2()
}

class Engine(): A by Car(), B by Tesla(){

//    override fun println1() {
//        println("This is interface 1")
//    }
//
//    override fun println2() {
//       println("This is interface 2")
//    }

}


interface A {
    fun println1()
}

interface B {
    fun println2()
}

class Car(): A{
    override fun println1() {
        println("This is the first interface")
    }

}

class Tesla(): B{
    override fun println2() {
        println("This is the second interface")
    }
}

