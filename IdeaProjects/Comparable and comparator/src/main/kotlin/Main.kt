fun main(){
    val numbers = listOf(5,12,1,3,65,2,14)
    numbers.sorted().forEach { println(it) }

    val insObj = mutableListOf(
        Laptop("Mac", 2025, "224"),
                Laptop("Windows", 2023, "198")
    )

    insObj.sortedWith(compareBy({it.year})).forEach { println(it) }
    println("\n")
    insObj.sortedWith(compareBy<Laptop>({it.name}).thenBy { it.ram }).forEach { println(it) }
    println("\n")
    insObj.sortedBy { it.ram }.forEach{ println(it) }
}

data class Laptop(val name: String, val year: Int, val ram: String)