fun main() {
    val student: Student? = null

    val cool = student?.run {
        println(name)
        println(age)
        "text"
    }

//    with(student){
//        println(name)
//        println(age)
//    }

//    val name: String? = null
//
//   name?.let {
//        println(name)
//        88
//    }


//    Student("Peter", 14).also { println(it) }

//    val user = User().apply {
//        name = "Sam"
//        lastname = "King"
//        age = 23
//    }
//    user.name = "Sam"
//    user.lastname = "King"
//    user.age = 22

//    val userValue = with(user){
//        name = "Sam"
//        lastname = "King"
//        age = 22
//        "Cool"
//    }

//    println(user.name)

}

//class User{
//    var name: String = ""
//    var lastname: String = ""
//    var age: Int = -1
//}
//

data class Student(var name: String, var age: Int){

}