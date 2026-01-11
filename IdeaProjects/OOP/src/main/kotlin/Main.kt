fun main(){
//    val myCar1 = Car("   Tesla         ", "S Plaid", "2025", "red") // Car Object created

//    val user = User("Abraham", 22, "Johnson")
    val user2 = User("Hosea", 25, "Faraday")

    /*
    myCar1.name = "Tesla"
    myCar1.model = "S Plaid"
    myCar1.color = "red"
    myCar1.year = "2025"
     */

//    println("This is a ${myCar1.name} ${myCar1.model}")

//    myCar1.moveCar(myCar1.name)
//    myCar1.stopCar()
//
//    println("\n")
//
//        val myCar2 = Car("Bugatti", "Chevron", "2025", "Gold") // Car Object created

    /*
    myCar2.name = "Bugatti"
    myCar2.model = "Chevron"
    myCar2.color = "Gold"
    myCar2.year = "2025"
    */

//    println("This is a ${myCar2.name} ${myCar2.model}")
//
//    myCar2.moveCar(myCar2.name)
//    myCar2.stopCar()
}

class User(name: String, var age: Int, var surname: String){
    var name: String

    init {
        if(name.lowercase().startsWith("a")){
            this.name = name
            println("The user is named : ${this.name}")
        }else{
            this.name = "User"
            println("The user name starts not with letter 'a' or 'A'")
        }
    }
}
