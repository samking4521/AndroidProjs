fun main(){
    val numbers = listOf(10,20,30,40,50,60,70)
    println("The sum of the transaction is ${numbers.sum()}")
    println("The number of transactions is ${numbers.count()}")
    println("Average revenue is ${numbers.average()}")
    println("The maximum transaction is ${numbers.max()}")
    println("The minimum transaction is ${numbers.min()}")
    println("Double of the total sum : ${numbers.sumOf { it * 2 }}")
}