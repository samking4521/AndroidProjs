fun main(){
//    val numbers = setOf<Int>(1,2,3,4,5)
//     numbers.map {
//         if(it == 3){
//             println(it + 10)
//         }else{
//             println(it * 20)
//         }
//
//    }
//
//   val objVal = mapOf<String, Int>("Key 1" to 1, "Key 2" to 2, "Key 3" to 3, "Key 4" to 4)
//    objVal.map {
//        println("${it.key} : ${it.value}")
//    }
//
//    println(objVal.mapKeys {
//        println(it)
//    })
//

//    val cars = listOf<String>("Tesla", "Hyundai", "Ford", "Bugatti", "Benz")
//    val price = listOf<Int>(10000, 2000, 1500, 20000, 15000)
//    println(cars.zip(price))
//
//    val estateReport = listOf("House 1" to 1000, "House 2" to 2000, "House 3" to 3000, "House 4" to 4000)
//    println(estateReport.unzip())

//        val cars = listOf<String>("Tesla", "Hyundai", "Ford", "Bugatti", "Benz")
//
//    println(cars.associateWith { it.length })
//    println(cars.associateBy { it.first().uppercase() })
//    println(cars.associateBy(keySelector = {it.first().lowercase()}, valueTransform = {it.length}))

//    val numbersArr = arrayOf(arrayOf(1,2,3), arrayOf(4,5,6), arrayOf(7,8,9))
//    for (number in numbersArr){
//        for ( num in number){
//             println(num)
//        }
//    }

    val numbersArr = listOf(setOf(1,2,3), setOf(4,5,6), setOf(7,8,9))
//    for(number in numbersArr){
//        for(num in number){
//            println(num)
//        }
//    }
    val numberModifeid = numbersArr.flatten()
    for(num in numberModifeid){
        println(num)
    }
}
