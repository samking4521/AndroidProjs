fun main() {
    val colors = listOf("red", "blue", "orange", "green", "purple", "gold")

    println(colors.any { it.length == 3 })
    println(colors.none { it.endsWith("w") })
    println(colors.all{ it.isNotEmpty() })
}
