import kotlin.concurrent.thread

fun main(){
    println("Main program starts: ${Thread.currentThread().name}")
   val parentJob = CoroutineScope(default).launch {
        println("Fake work starts on ${Thread.currentThread().name}")
        delay(2000)
        println("Fake work ends on ${Thread.currentThread().name}")
    }

    runningBlock{
        parentJob.join()
    }
    println("Main program ends: ${Thread.currentThread().name}")
}

private suspend fun getData1(threadName: String): String{
    println("Fake work1 starts: $threadName")
    delay(2000)
    println("Fake work2 finished: $threadName")
    return "Result 1"
}

private suspend fun getData2(threadName: String): String{
    println("Fake work1 starts: $threadName")
    delay(2000)
    println("Fake work2 finished: $threadName")
    return "Result 2"
}