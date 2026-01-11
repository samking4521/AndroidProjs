fun main (){
   val carBrand = Tesla()
    println(carBrand.name)
    carBrand.move()
    carBrand.stop()
}

abstract class Car(){
    abstract var name: String
     abstract fun move()


    fun stop(){
        println("The car has stopped moving")
    }
}

class Tesla(): Car(){
    override var name: String = "Tesla"


    override fun move() {
       println("This is my Tesla Car")
    }
}

