fun main(){
    val Num = Int.MAX_VALUE
    val result = Calculator.sum(11,22)
    val addNum = Calculator.add
    println(result)
    println(addNum)

}

class Calculator(){
    companion object {
        var add = 20
        fun sum(a: Int, b: Int): Int{
            return a + b
        }
    }

}