fun main(){
    val names = mutableListOf("Tesla", "Bugatti", "Ford", "Benz")
    val addName = names + "Ferrari"
    val minusName = names - mutableListOf("Ford")
    println(addName)
    println(minusName)
}