fun main(){
   /* var myArr = arrayOf("Tesla", "Audi", "Buggatti", 12, true, 'A')

    myArr[0] = "Hyundai"
    println("First Element is : ${myArr[0]}")
    println("myArr length is ${myArr.size}")

    for(elements in myArr){
        if(elements is String){
            println(elements)
        }

    }*/

    /*val maxArr = arrayOf(1,100,3,1000,5,6,7,8,9,10)
    val minArr = arrayOf(16,20,3,40,5,6,7,8,9,7)


    val maxValue = maxNumber(maxArr)
    val minValue = minNumber(minArr)
    val thirdArr = arrayOf(maxValue, minValue)
    val thirdMaxValue = thirdFunc(thirdArr)

    println("Min val: $minValue, Max value: $maxValue, Third func Max Value: $thirdMaxValue")*/

    val compareValue = findMaxAndMinNumbers(arrayOf(22,10,14,12,5), true)
    println("Max number in the array is : $compareValue")
//    println("Min number in the array is : $compareValue")

}

/*fun maxNumber(arr: Array<Int>): Int{
    val maxVal = arr.maxOf { it }
    return maxVal
}

fun minNumber(arr: Array<Int>): Int{
    val minVal = arr.minOf { it }
    return minVal
}

fun thirdFunc(arr: Array<Int>): Int{
    val maxVal = arr.maxOf { it }
    return maxVal
}*/

fun findMaxAndMinNumbers(arr: Array<Int>, findType: Boolean): Int{
      if(findType){
          var max = arr[0]
          for(num in arr){
              if(num > max){
                  max = num
              }
          }
          return max
      }else{
          var min = arr[0]
          for(num in arr){
              if(num < min){
                  min = num
              }
          }
          return min
      }
}
