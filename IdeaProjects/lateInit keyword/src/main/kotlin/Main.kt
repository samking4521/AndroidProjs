fun main(){
    val carObj = Car("Tesla", "Red", "2025")


     carObj.carBrand = "Bugatti"
     println(carObj.carBrand)
    println("My ${carObj.carName} ride")
    println("My ${carObj.carColor} car")
    println("My ${carObj.carYear} car")


}

class Car(var carName: String, var carColor: String, var carYear: String) {
      lateinit var carBrand: String
}