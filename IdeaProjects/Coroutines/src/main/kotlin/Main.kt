import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.concurrent.thread

val handler = CoroutineExceptionHandler { _, exception ->
      println("This is a child coroutine error: ${exception.message}")
}

fun main(){
    val startTime = System.currentTimeMillis()
    println("The main program starts ${Thread.currentThread().name}")
    val parentJob = CoroutineScope(Default).launch {


            val job1 = async(start = CoroutineStart.LAZY) {
                 getData1(Thread.currentThread().name)
            }

            val job2 = async(start = CoroutineStart.LAZY) {
                getData2(Thread.currentThread().name)
            }

            println("${job1.await()} and ${job2.await()}")
    }

    runBlocking {
        parentJob.join()
    }

    parentJob.invokeOnCompletion {
        it?.let {
            println("Parent job Failed, ${it.message}")
        } ?: println("Parent Job Success")
    }
    println("Total time: ${System.currentTimeMillis() - startTime}")


    println("The main program ends ${Thread.currentThread().name}")
}

private suspend fun getData1(threadName: String): String {
    println("Fake work1 starts: $threadName")
//    throw CancellationException("This is an error on getData1")
    delay(2000)
    println("Fake work1 finished: $threadName")
    return "Result 1"
}

private suspend fun getData2(threadName: String): String {
    println("Fake work2 starts: $threadName")
//    throw CancellationException("This is an error on getData2")
    delay(2000)
    println("Fake work2 finished: $threadName")
    return "Result 2"
}