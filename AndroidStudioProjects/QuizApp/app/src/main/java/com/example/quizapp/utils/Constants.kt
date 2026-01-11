package com.example.quizapp.utils

import com.example.quizapp.R
import com.example.quizapp.model.Questions

object Constants {
     const val USER_NAME = "user_name"
     const val TOTAL_QUESTIONS = "total_questions"
     const val SCORE = "correct_answers"

     fun getQuestions(): MutableList<Questions>{
         val questions = mutableListOf<Questions>()
         val quest1 = Questions(
             1,
                "What country does this flag belong to?",
             R.drawable.usa,
             "Italy",
             "Japan",
             "Spain",
             "USA",
             4
         )
         questions.add(quest1)

         val quest2 = Questions(
             2,
             "What country does this flag belong to?",
             R.drawable.canada,
             "Egypt",
             "Dubai",
             "Canada",
             "USA",
             3
         )
         questions.add(quest2)

         val quest3 = Questions(
             3,
             "What country does this flag belong to?",
             R.drawable.india,
             "Morocco",
             "India",
             "Mauritania",
             "Angola",
             2
         )
         questions.add(quest3)

         val quest4 = Questions(
             4,
             "What country does this flag belong to?",
             R.drawable.greece,
             "Ireland",
             "Niger",
             "Greece",
             "USA",
             3
         )
         questions.add(quest4)

         val quest5 = Questions(
             5,
             "What country does this flag belong to?",
             R.drawable.nigeria_flag,
             "Nigeria",
             "India",
             "Ivory Coast",
             "Ireland",
             1
         )
         questions.add(quest5)

         val quest6 = Questions(
             6,
             "What country does this flag belong to?",
             R.drawable.argentina,
             "Ireland",
             "Japan",
             "Spain",
             "Argentina",
             4
         )
         questions.add(quest6)

         val quest7 = Questions(
             7,
             "What country does this flag belong to?",
             R.drawable.china,
             "China",
             "Angola",
             "Mali",
             "Switzerland",
             1
         )
         questions.add(quest7)

         val quest8 = Questions(
             8,
             "What country does this flag belong to?",
             R.drawable.egypt,
             "China",
             "Egypt",
             "Morocco",
             "Nigeria",
             2
         )
         questions.add(quest8)

         val quest9 = Questions(
             9,
             "What country does this flag belong to?",
             R.drawable.uk,
             "UK",
             "Angola",
             "Mali",
             "Switzerland",
             1
         )
         questions.add(quest9)

         val quest10 = Questions(
             10,
             "What country does this flag belong to?",
             R.drawable.france,
             "China",
             "Angola",
             "France",
             "UK",
             3
         )

          questions.add(quest10)
         return questions

     }
}