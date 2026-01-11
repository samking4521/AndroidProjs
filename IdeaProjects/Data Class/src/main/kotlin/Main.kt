fun main(){

    val user = User("Samuel")
    val user2 = User("Samuel")

    println(user == user2)
    println(user)
    println(user2)
}

//class User(var name: String){
//    override fun equals(other: Any?): Boolean {
//         if(this === other){
//             return true
//         }
//
//        if(other is User){
//            return this.name == other.name
//        }
//
//        return false
//    }
//
//    override fun hashCode(): Int {
//        return 0
//    }
//
//    override fun toString(): String {
//        return "User(name = $name)"
//    }
//}

data class User(var name: String)
