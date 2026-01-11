fun main (){
   val myTeam = Team<CounterStrikerPlayer>(
       "football",
       mutableListOf<GamesPlayer>(GamesPlayer("ball"))
   )
}

class Team<T: Player>(val name: String, val players: MutableList<in T>){
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
open class GamesPlayer(name: String): Player(name)
class CounterStrikerPlayer(name: String): GamesPlayer(name)
