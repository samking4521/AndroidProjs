fun main(){
    // Nullable type marker
    var myName: String? = null
    // Safe call operator
    println(myName?.length)
    myName = "Samuel"
    // Elvis operator
    val text = myName?: "The variable is null"
    println(text)

}