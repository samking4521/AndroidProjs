fun main(){
    val names = listOf("sam", "elon", "peter", "king", "louis")
    println(names.first())
    println(names.last())
    println("\n")
    println(names.first { it.length > 4 })
    println(names.last { it.startsWith("l") })
    println("\n")
    println(names.random())
//    println(names.isEmpty())

}