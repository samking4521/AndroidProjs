fun main(){
    val num1 = 50


    val textResult = when{
        num1<= 20 -> println("This is equal to one")
         num1 <= 30 -> println("This ranges 3")
        num1<=50 || num1<=100 ->
           "This is a large number"
        else -> println("This does not match")
    }

    println("textResult : $textResult")
}