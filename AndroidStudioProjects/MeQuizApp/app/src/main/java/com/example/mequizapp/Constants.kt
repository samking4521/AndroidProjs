package com.example.mequizapp

object Constants {
    const val PLAYER_NAME = "the_player_name"
    const val SCORE = "player_score"
    const val PLAYER_RESULT = "player_result"

    data class Question(
        val id: Int,
        val flag: Int,
        val optionOne: String,
        val optionTwo: String,
        val optionThree: String,
        val optionFour: String,
        val correctAnswer: Int
    )


    fun getQuizQuestions(): MutableList<Question> {

        val quest1 = Question(
            1,
            R.drawable.canada,
            "Canada",
            "USA",
            "Argentina",
            "South Korea",
            1
        )
        val quest2 = Question(
            2,
            R.drawable.nigeria,
            "France",
            "Nigeria",
            "Italy",
            "Egypt",
            2
        )
        val quest3 = Question(
            3,
            R.drawable.egypt,
            "Israel",
            "Canada",
            "Japan",
            "Egypt",
            4
        )
        val quest4 = Question(
            4,
            R.drawable.france,
            "UAE",
            "France",
            "Rwanda",
            "Spain",
            2
        )
        val quest5 = Question(
            5,
            R.drawable.uk,
            "UK",
            "Senegal",
            "South Africa",
            "Switzerland",
            1
        )
        val quest6 = Question(
            6,
            R.drawable.greece,
            "Qatar",
            "China",
            "Greece",
            "Saudi Arabia",
            3
        )
        val quest7 = Question(
            7,
            R.drawable.argentina,
            "Argentina",
            "Egypt",
            "Denmark",
            "Croatia",
            1
        )
        val quest8 = Question(
            8,
            R.drawable.china,
            "Romania",
            "China",
            "Australia",
            "Poland",
            2
        )
        val quest9 = Question(
            9,
            R.drawable.india,
            "India",
            "South Sudan",
            "Mali",
            "Belgium",
            1
        )
        val quest10 = Question(
            10,
            R.drawable.usa,
            "Niger",
            "USA",
            "Canada",
            "South Korea",
            2
        )


        return mutableListOf(
            quest1,
            quest2,
            quest3,
            quest4,
            quest5,
            quest6,
            quest7,
            quest8,
            quest9,
            quest10
        )


    }
}