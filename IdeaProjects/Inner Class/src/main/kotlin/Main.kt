fun main(){
    val names = Names(arrayOf("Sam", "Eli", "John", "Elon"))

    names.Position().printPosition(2)
}

class Names(val namesList: Array<String>){
    inner class Position{
        fun printPosition(position: Int){
            println("Value is $position")
        }
    }
}