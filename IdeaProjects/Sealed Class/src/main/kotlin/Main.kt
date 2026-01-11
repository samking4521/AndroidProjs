fun main(){
    val success = Result.Success("Success")
    val error = Result.Error.ErrorMessage("Error")
    val loading = Result.Loading("Loading")

    getData(success)
    getData(error)
    getData(loading)
}

fun getData(result: Result){
    when(result){
        is Result.Error.ErrorMessage -> result.showMessage()
        is Result.Success -> result.showMessage()
        is Result.Loading -> result.showMessage()
    }
}

sealed class Result(var message: String){
    fun showMessage(){
        println("RESULT: $message")
    }

    class Success(message: String): Result(message)
    sealed class Error(var message: String){
        class ErrorMessage(message: String): Result(message)
    }
    class Loading(message: String): Result(message)
}
