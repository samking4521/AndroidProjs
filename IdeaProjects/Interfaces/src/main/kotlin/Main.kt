fun main(){
    val car = Tesla("Tesla", "Blue")
    val car2 = Hyundai("Hyundai", "Green")
    car.startEngine()
    car2.startEngine()
}

interface Engine {
    val brand: String
    fun startEngine()
}

class Tesla (var name: String, var color: String ): Engine{
    override val brand = "Tesla"
    override fun startEngine() {
        println("Tesla Engine Started, $brand")
    }
}

class Hyundai (var name: String, var color: String ): Engine{
    override val brand = "Hyundai"
    override fun startEngine() {
        println("Hyundai Engine Started, $brand")
    }
}



