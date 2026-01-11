fun main(){
    val bankAcc = BankAcc("John Doe")

    bankAcc.deposit(15000)
    bankAcc.withdraw(4000)
    bankAcc.withdraw(5000)
    bankAcc.calcBalance()

}

class BankAcc(val accName: String){
    private var accBalance: Int = 0
    private var transactions: MutableList<Int> = mutableListOf()

    fun deposit(cash: Int){
        if(cash > 0){
            accBalance += cash
            transactions.add(cash)
            println("Cash deposit of $cash is successful")
        }else{
            println("Oops! Cannot deposit negative values")
        }
    }

    fun withdraw(cash: Int){
        if(accBalance == 0 || cash > accBalance){
            println("Insufficient funds")
            return
        }

        if(cash <= 0){
            println("Oops! You cannot withdraw nothing or less than 0")
        }else{
            accBalance -= cash
            transactions.add(-cash)
            println("Cash Withdrawal of $cash is successful")
        }

    }

    fun calcBalance(): Int{
        var totalBal = 0
        for(balance in transactions){
              totalBal += balance
        }
        println("Account balance for the acc name: ${this.accName} is $totalBal")
        return totalBal
    }


}