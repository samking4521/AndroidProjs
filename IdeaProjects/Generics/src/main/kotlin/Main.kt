fun main (){
     val footballPlayer = FootballPlayer("Football Player 1")
     val footballPlayer2 = FootballPlayer("Football Player 2")

    val baseballPlayer = BaseballPlayer("Baseball Player 1")
    val baseballPlayer2 = BaseballPlayer("Baseball Player 2")

    val gameballPlayer = GamesPlayer("Gameball Player 1")

    val footballTeam = Team<FootballPlayer>("football", mutableListOf(footballPlayer))
     footballTeam.addPlayers(footballPlayer2)
    footballTeam.addPlayers(footballPlayer)

    println("\n")

    val baseballTeam = Team<BaseballPlayer>("baseball", mutableListOf(baseballPlayer))
    baseballTeam.addPlayers(baseballPlayer2)
    baseballTeam.addPlayers(baseballPlayer)

    val gamePlayerTeam = Team<GamesPlayer>("game", mutableListOf(gameballPlayer))
}

class Team<T: Player>(val name: String, val players: MutableList<T>){
    fun addPlayers(player: T){
        if(players.contains(player)){
            println("Player: ${(player as Player).name} is already in the team")
        }else{
            players.add(player)
            println("Player: ${(player as Player).name} is added to the team")
        }
    }
}

open class Player(val name: String)

class FootballPlayer(name: String): Player(name)
class BaseballPlayer(name: String): Player(name)
class GamesPlayer(name: String): Player(name)
