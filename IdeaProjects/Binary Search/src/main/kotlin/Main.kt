fun main(){
    val numbers = (1..30).toList()
    findNum(27, numbers)
}

fun findNum(element: Int, list: List<Int>){
     println(list[list.binarySearch(element)])
}