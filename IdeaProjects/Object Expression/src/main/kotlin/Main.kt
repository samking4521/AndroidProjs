fun main(){

    val carType = Tesla("Samuel", "Red", object : ClickFunc{
        override fun onClick() {
           println("Bought a Tesla")
        }
    })

    val carType2 = Tesla("King", "Gold", object : ClickFunc {
        override fun onClick() {
           println("Bought a Hyundai")
        }
    }  )

    carType.clickEvent.onClick()
    carType2.clickEvent.onClick()
}

class Tesla(var name: String, var color: String, var clickEvent: ClickFunc){

}

class Clicked():ClickFunc{
    override fun onClick() {
        println("Bought a Tesla")
    }
}



interface ClickFunc{
    fun onClick()
}