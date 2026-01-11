// Class is a blueprint of an object
class Car(name: String, var model: String, var year: String, var color: String) {

    var name = name.trim()
    /*
    var name = name
    var model = model
    var year = year
    var color = color
     */

    /*
     var name = ""
     var model = ""
     var year = ""
     var color = ""
     */

    fun moveCar(carVal: String){
        println("The $carVal car moves fast")
    }

    fun stopCar(){
        println("Stop the $color car")
    }

}