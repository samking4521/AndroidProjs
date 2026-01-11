fun main(){
    val sumResult = sumNumbers(4, 10, 10)
    println("Addition result : $sumResult")
}

fun sumNumbers(vararg numbers: Int): Int{
    var result = 0
//    for(number in numbers){
//        result += number
//    }
//    return result
    numbers.forEach{
        result += it
    }

    return result
}