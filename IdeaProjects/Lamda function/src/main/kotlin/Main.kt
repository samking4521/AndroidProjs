fun main(){
    addNum(10, 4)
    calculate(1, 11) {z: Int -> println("Result is: $z")}
    println("\n")
    toUppercase("hello"){it.uppercase()}
}

val addNum = {a:Int, b: Int -> println("Sum is : ${a + b}")}

fun calculate(x: Int, y: Int, result: (Int) -> Unit){
    result(x + y)
}

fun toUppercase(str: String, convert: (String) -> String){
    val convertUppercase = convert(str)
    println("result : $convertUppercase")
}