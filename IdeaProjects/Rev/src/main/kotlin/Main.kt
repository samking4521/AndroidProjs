fun main(){
    val studentObj = Student("Tesla")
    val secondaryObj = Student( name = "Elon", age = 22)

    println("name = ${studentObj.name}")
    println("age = ${studentObj.age}")
    println("studentYear = ${studentObj.studentYear}")
    println("schoolName = ${studentObj.schoolName}")

    println("\n")

    println("name = ${secondaryObj.name}")
    println("age = ${secondaryObj.age}")
    println("studentYear = ${secondaryObj.studentYear}")
    println("schoolName = ${secondaryObj.schoolName}")


//    studentObj.name = "Tesla"
//    studentObj.age = 22
//    studentObj.studentYear = "Year 3"
//    studentObj.schoolName = "Astra"

//    println("${studentObj.name} is a ${studentObj.age} year old expert, currently in ${studentObj.studentYear}, and studies at ${studentObj.schoolName}")
//
//    studentObj.schoolStarts()
//    studentObj.schoolEnds()

//    println("\n")
//
//    val studentObj2 = Student("Faraday", 25, "Year 5", "Lyon")

//    studentObj2.name = "Faraday"
//    studentObj2.age = 25
//    studentObj2.studentYear = "Year 5"
//    studentObj2.schoolName = "Lyon"

//    println("${studentObj2.name} is a ${studentObj2.age} year old expert, currently in ${studentObj2.studentYear}, and studies at ${studentObj2.schoolName}")
//
//    studentObj.schoolStarts()
//    studentObj.schoolEnds()
}

//Blueprint of an object
class Student(var name: String = "Tesla", var age: Int = 22, var studentYear: String = "Year 1", var schoolName: String = "Starlight") {
//     var name = name.trim()

//    init {
//        if(name.lowercase().startsWith("t")){
//            println("This starts with T or t")
//        }else{
//            this.name = "Cool"
//        }
//    }


//    var name = "Tesla"
//    var age = 20
//    var studentYear = "Year 4"
//    var schoolName = "Mia"

//    var name = name
//    var age = age
//    var studentYear = studentYear
//    var schoolName = schoolName



//    fun schoolStarts(){
//        println("School has started, said by ${this.name}")
//    }
//
//    fun schoolEnds(){
//        println("School has ended, till the next session at ${this.schoolName}")
//    }

    // Secondary constructor
   /* constructor(name: String, age: Int): this(name, age, "Year 5", "Gemini"){
        println("2nd Constructor Called")
    }

    constructor(name: String): this(name, 28, "Year 6", "ChatGPT"){
        println("3rd Constructor Called")
    } */


}