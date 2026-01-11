fun main(){
    val numbersString = listOf("one", "two", "three", "four", "five", "six")

    println(numbersString.slice(0..3))
    println(numbersString.slice(setOf(4, 5)))

    println("\n")

    println(numbersString.take(3))
    println(numbersString.takeLast(2))
    println(numbersString.drop(2))
    println(numbersString.dropLast(1))

    println("\n")

    println(numbersString.takeWhile { it.length == 3 })
    println(numbersString.takeLastWhile { !it.startsWith("f")})
    println(numbersString.dropWhile { it.startsWith("o") })
    println(numbersString.dropLastWhile { it.endsWith("x") })

    println("\n")

    val numbers = (1..10).toList()
    println(numbers.chunked(2) {it.sum()})

    println("\n")
    println(numbers.windowed(3))
    println(numbers.windowed(3) {it.sum()})

    
}