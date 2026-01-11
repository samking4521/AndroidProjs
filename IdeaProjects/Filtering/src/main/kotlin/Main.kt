fun main() {
    val numbers = listOf("one", "two", "three", "four")
    val result = numbers.filter { it.length > 3 }
    println(result)

    val mappedNumbers = mapOf("Key 1" to 1, "Key 2" to 2, "Key 3" to 3, "Key 4" to 4)
    val mapResult = mappedNumbers.filter { it.key.endsWith("1") && it.value == 1 }
    println(mapResult)

    val colors = listOf("red", "blue", "orange", "green", "purple", "gold")

    val resultColor = colors.filterIndexed { index, value -> index != 0 && value.length > 5 }
    println(resultColor)

    val resultFilterNot = numbers.filterNot { it.length <= 3 }
    println(resultFilterNot)

    val mixedTypes = listOf(1, 2, 3, 4, 'A', 'B', 'C', 'D', "Tesla", "Bugatti", true, false)
    mixedTypes.filterIsInstance<Boolean>().forEach {
        println(it)
    }

    val (match, rest) = colors.partition { it.length == 4 }
    println(match)
    println(rest)

    println(colors.any { it.length == 3 })
    println(colors.none { it.endsWith("w") })
    println(colors.all{ it.isNotEmpty() })

}
