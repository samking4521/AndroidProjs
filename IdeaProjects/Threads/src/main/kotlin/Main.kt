import kotlin.concurrent.thread

fun main() {
    thread()
}

fun thread(){
    println("Message 1")
    println("Message 2")
    println("Message 3")
    println("Message 4")
    println("Message 5")
    thread {
        Thread.sleep(5000)
        println("Thread 2 has finished running")
    }
    println("Message 6")
    println("Message 7")
    println("Message 8")
    println("Message 9")
    println("Message 10")

}