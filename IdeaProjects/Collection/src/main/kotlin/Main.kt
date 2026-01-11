fun main(){
//    var items = mutableListOf<Any?>("Car", "Bag", "Map", "Football", 2, null)
//    items[2] = "Globe"
//    items.add("Hyundai")
//    items.remove("Bag")
//    items.removeAt(3)
//    items.forEach {
//        println(it)
//      }

    var user = User("Samuel")
    var user2 = User("Samuel")

    var items = setOf<User>(user, user2)

        items.forEach {
        println(it.name)
    }
//    var items = mutableSetOf<String>("Tesla", "Hyundai", "Ferrari", "Tesla")
//     items.add("Golf")
//      items.remove("Hyundai")
//    items.forEach {
//        println(it)
//    }

//    var items = mutableMapOf<Int, Any>(1 to "Tesla", 2 to "Golf", 3 to 4000, 4 to "Lee Bang Ji")
//      items.put(10, "Elon")
//    items.remove(2)
//      println(items[4])
//     items.forEach{ (a, b) ->
//         println("$a : $b")
//     }

}

data class User(var name: String){

}


