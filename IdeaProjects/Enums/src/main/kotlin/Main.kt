fun main(){
//    val direction = Direction.NORTH
//    println(direction)
//    val direction = Direction.NORTH.distance
//    println(direction)

//    for (direction in Direction.values()){
//          println("direction: $direction")
//    }

//    for (direction in Direction.values()){
//        println("direction: ${direction.name}, distance ${direction.distance}, and time ${direction.time} ")
//    }
//
//    Direction.NORTH.printData()

//    val direction = Direction.EAST
//    val direction = Direction.valueOf("east".uppercase())
    val direction = Direction.valueOf("EAST")

    when(direction){
        Direction.NORTH -> println("This is north")
        Direction.SOUTH -> println("This is south")
        Direction.EAST -> println("This is east")
        Direction.WEST -> println("This is west")
    }

}

enum class Direction(val distance: Int, val time: Int){
    NORTH(10, 100),
    SOUTH(15, 120),
    EAST(18, 200),
    WEST(25, 400);

    fun printData(){
        println("This is enum for direction ${Direction.NORTH.name}")
    }
}