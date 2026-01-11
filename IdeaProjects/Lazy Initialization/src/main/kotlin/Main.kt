fun main(){
    val avengersVal = Avengers("Tony stark", "Peter Parker")
    val avengersVal2 by lazy{
        Avengers("Bruce Wayne", "Peter Parker")
    }

    println(avengersVal2.spiderMan)

}

class Avengers(var ironman: String, var spiderMan: String){
     init{
         println("Object instance created : $ironman")
     }

}