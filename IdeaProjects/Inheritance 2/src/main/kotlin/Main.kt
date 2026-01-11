fun main(){
    val viewComponent = View()
    val buttonComponent = Button("Button", "Blue")
    val pressableComponent = Pressable("Click Me", "Gold", "Cool", 20)

    viewComponent.draw()
    buttonComponent.draw()
    pressableComponent.draw()

}

open class View(){
    open fun draw(){
        println("View is drawn")
    }
}

open class Button(val title: String, val backgroundColor: String): View(){
     override fun draw() {
        println("Button is drawn")
    }
}

class Pressable(title: String, backgroundColor: String, val text: String, val borderRadius: Int): Button(title, backgroundColor){
    override fun draw() {
       println("Pressable is drawn")
    }
}