fun main(){
     val numberLength = loopFunc()
    println("Even number length : $numberLength")
}

fun loopFunc(): Int{
//    for( i in 1..10){
//        println("i == $i")
//    }

//    for(i in 1 until 10){
//        println(i)
//    }

//    for (i in 1 until 10 step 2){
//        println(i)
//    }

//    for (i in 10 downTo 1 step 2){
//            println(i)
//    }

//    var num = 0
//
//    while(num < 10){
//        num++
//        if(num == 7){
//          break
//        }
//        println("I am number : $num")
//
//    }

//    do{
//        println("I am a number greater than $num")
//        num++
//    }while (num > 10)

//    var value = 0
//
//   outer@ while (value < 5){
//        value++
//        println(value)
//
//        var i = 0
//
//        while(i < 5){
//            if(i == 0) break@outer
//            i++
//            println("****$i")
//        }
//    }

//      var evenNoLength = 0
//    // loop through the numbers 1 to 10
//    for(number in 1..20){
//        // Check even and odd numbers
//         if(number%2 == 0){
//             println(number)
//             evenNoLength++
//         }else{
//             continue
//         }
//    }
//    return evenNoLength

    var num = 0
    var evenNumberCount = 0

    while(num < 20){
          num++
         if(num % 2 == 0){
             println(num)
             evenNumberCount++
         }else{
             continue
         }
    }

    return evenNumberCount
}