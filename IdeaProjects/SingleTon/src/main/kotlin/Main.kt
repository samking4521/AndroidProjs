// First way to use singleTon
//fun main(){
//    val diaryRef = Diary.getDiary()
//    val diaryRef1 = Diary.getDiary()
//    val diaryVal = Diary.getDiary()?.month
//    val diaryVal2 = Diary.getDiary()?.year
//
//    println("ref 1: $diaryRef, ref 2: $diaryRef1")
//    println("\n")
//    println("The date specified in the diary is the month of $diaryVal, in the year $diaryVal2.")
//}
//
//class Diary private constructor(var month: String,var year: Int){
//    companion object{
//        private var diaryDetails: Diary? = null
//        fun getDiary(): Diary?{
//            if(diaryDetails == null){
//                diaryDetails = Diary("January", 2025)
//            }
//            return diaryDetails
//        }
//    }
//}

// Recommended way to create and use singleTon

fun main(){
    val value = Car
    val value1 = Car

    println("$value, $value1")
    println(Car.name)
    println(Car.age)
    Car.getCar()
}

object Car {
    var name = "Say"

    init{
       if(name.length <= 3){
            name = "No Name"
       }
        else{
            name = name
       }

    }

    var age = 44
    init{
        var newAge = 10
         age = age + newAge
    }

    fun getCar(){
        println("The Toyota brand has been in existence for over $age years now")
    }
}
