package com.example.classes
import com.example.interfaces.*

fun getData(){
    println("This function was actually declared in the classes file")
}

fun mainClasses (){
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