fun main(){
    val CarObj = Car("Tesla", "Red", "2025")

    println("My ${CarObj.carName} ride")
    println("My ${CarObj.carColor} car")
    CarObj.carName = "Bugatti"
    CarObj.carColor = "Gold"
    CarObj.moveCar()
    CarObj.stopCar()
}

class Car(carName: String, carColor: String, var carYear: String){
       var carName = carName
           get(){
               return "CarName : $field"
           }

           set(value){
               println("The value: $value is assigned to carName")
               field = value
           }

      var carColor = carColor
          get(){
              return "carColor : $field"
          }

          set(value){
              println("The value: $value is assigned to carColor")
              field = value
          }

    fun moveCar(){
        println("This is my ${this.carName}, made ${this.carYear}, and i got a ${this.carColor} one")
    }

    fun stopCar(){
        println("Stop the ${this.carColor} car")
    }
}