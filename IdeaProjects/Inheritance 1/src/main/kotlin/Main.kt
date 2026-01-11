fun main(){
   val carModel = Car("Audi", "gold", "2025", "S")
    carModel.move()
    carModel.stop()

}

open class Vehicle(val name: String, val color: String){
    open fun move(){
        println("$name is moving")
    }

    fun stop(){
        println("$name stops")
    }

}

class Car(name: String, color: String, val year: String, val model: String): Vehicle(name, color){
    override fun move() {
        println("$name moves fast with its $color beauty")
        super.move()
    }
}
