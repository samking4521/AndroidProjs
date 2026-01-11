import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


fun main(){
   val user = User()
    with(user){
        firstname = "Bang Ji"
        lastname = "Lee"
    }

    with(user){
       println("The greatest swordsman name is $lastname $firstname")
    }

}

class User{
    var firstname by FormatDelegate()
    var lastname by FormatDelegate()
}


class FormatDelegate: ReadWriteProperty<Any?, String> {
    var formatToString = ""
    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return formatToString
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        formatToString = value.lowercase()
    }
}