fun main() {
    calculateNum()
}

fun calculateNum(){
    val num1 = 5
    val num2 = 0

    val result = try{
        println(num1/num2)
        num1/num2
    }catch(e: ArithmeticException){
        println("This is an ${e.message } error")
        e.message
    }finally{
        println("This is a division operation")
    }

    println("The result : $result")
}