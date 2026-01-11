fun main(){
    val names = listOf("Sam", "Zsa", "Tesla", "Hyundai")
    println(names.joinToString())

    val text = StringBuffer("These names are cool: ")
    println(names.joinTo(text))

    println(names.joinToString(separator = " | ", prefix = "Cool names : ", postfix = " Cool!"))

    val numbers = (1..100).toList()
    println(numbers.joinToString(limit = 25, truncated = "<...>"))

    val colors = listOf("Blue", "Green", "Purple", "Yellow", "Red")
    println(colors.joinToString { "color : ${it.uppercase()}" })
}