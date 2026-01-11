package com.example.quizapp.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import com.example.quizapp.R
import com.example.quizapp.model.Questions
import com.example.quizapp.utils.Constants

class QuestionActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var progressBar: ProgressBar
    private lateinit var textViewProgressBar: TextView
    private lateinit var textViewQuestion: TextView
    private lateinit var imageView: ImageView

    private lateinit var textViewQuestionOne: TextView
    private lateinit var textViewQuestionTwo: TextView
    private lateinit var textViewQuestionThree: TextView
    private lateinit var textViewQuestionFour: TextView

    private lateinit var startButton: Button
    private var questionCounter = 0
    private lateinit var questionsList: MutableList<Questions>
    private var selectedAnswer = 0
    private lateinit var currentQuestion: Questions
    private var answered = false
    private lateinit var name: String
    private var score = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        progressBar = findViewById(R.id.progressBar)
        textViewProgressBar = findViewById(R.id.progressText)
        textViewQuestion = findViewById(R.id.question)
        imageView = findViewById(R.id.image_flag)

        textViewQuestionOne = findViewById(R.id.option1)
        textViewQuestionTwo = findViewById(R.id.option2)
        textViewQuestionThree = findViewById(R.id.option3)
        textViewQuestionFour = findViewById(R.id.option4)

        startButton = findViewById(R.id.startButton)

        textViewQuestionOne.setOnClickListener(this)
        textViewQuestionTwo.setOnClickListener(this)
        textViewQuestionThree.setOnClickListener(this)
        textViewQuestionFour.setOnClickListener(this)
        startButton.setOnClickListener(this)

        questionsList = Constants.getQuestions()
        Log.d("QuestionSize", "${questionsList.size}")
        showNextQuestion()

        if(intent.hasExtra(Constants.USER_NAME)){
            name = intent.getStringExtra(Constants.USER_NAME)!!
        }

    }

    private fun resetOptions(){
        val options = mutableListOf<TextView>()
        options.add(textViewQuestionOne)
        options.add(textViewQuestionTwo)
        options.add(textViewQuestionThree)
        options.add(textViewQuestionFour)

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.option_bg
            )
        }
    }




    private fun showNextQuestion(){


        if(questionCounter < questionsList.size){
            startButton.text = getString(R.string.checkP)
            currentQuestion = questionsList[questionCounter]

            resetOptions()
            val question = questionsList[questionCounter]
            imageView.setImageResource(question.image)
            progressBar.progress = questionCounter
            textViewProgressBar.text = "${questionCounter + 1}/${progressBar.max}"
            textViewQuestionOne.text = question.optionOne
            textViewQuestionTwo.text = question.optionTwo
            textViewQuestionThree.text = question.optionThree
            textViewQuestionFour.text = question.optionFour

            questionCounter++
            answered = false

        }else{
//            startButton.text = getString(R.string.finish)

            Intent(this@QuestionActivity, ResultActivity::class.java).also{
                it.putExtra(Constants.USER_NAME, name)
                it.putExtra(Constants.SCORE, score)
                it.putExtra(Constants.TOTAL_QUESTIONS, questionsList.size)
                startActivity(it)
            }

        }


    }

     private fun selectedOption(textView: TextView, selectedOptionNumber: Int){
           resetOptions()
           selectedAnswer = selectedOptionNumber

         textView.setTextColor(Color.parseColor("#363A43"))
         textView.setTypeface(textView.typeface, Typeface.BOLD)
         textView.background = ContextCompat.getDrawable(
             this,
             R.drawable.selected_option_bg
         )
     }

    override fun onClick(view: View?) {

       when(view?.id){
            R.id.option1 -> {
                if(!answered){
                    selectedOption(textViewQuestionOne, 1)
                }

            }
           R.id.option2 -> {
               if(!answered){
                   selectedOption(textViewQuestionTwo, 2)
               }

           }
           R.id.option3 -> {
               if(!answered){
                   selectedOption(textViewQuestionThree, 3)
               }

           }
           R.id.option4 -> {
               if(!answered){
                   selectedOption(textViewQuestionFour, 4)
               }

       }
           R.id.startButton -> {
                if(!answered){
                    checkAnswer()
                }else{
                    showNextQuestion()
                }
               selectedAnswer = 0

           }

       }
    }

    private fun checkAnswer(){
        answered = true
        if(selectedAnswer == currentQuestion.correctAnswer) {
            score++
            highlightedResult(selectedAnswer)
        }
        else {

            when(selectedAnswer){
                1 -> {
                    textViewQuestionOne.background = ContextCompat.getDrawable(
                        this,
                        R.drawable.wrong_option_bg
                    )
                }
                2 -> {
                    textViewQuestionTwo.background = ContextCompat.getDrawable(
                        this,
                        R.drawable.wrong_option_bg
                    )
                }
                3 -> {
                    textViewQuestionThree.background = ContextCompat.getDrawable(
                        this,
                        R.drawable.wrong_option_bg
                    )
                }
                4 -> {
                    textViewQuestionFour.background = ContextCompat.getDrawable(
                        this,
                        R.drawable.wrong_option_bg
                    )
                }
            }
            showSolution()
        }
        if(questionCounter < questionsList.size) {
            startButton.text = getString(R.string.next)
        }else{
            startButton.text = getString(R.string.see_result)
        }


    }

    private fun showSolution(){
        selectedAnswer = currentQuestion.correctAnswer
        highlightedResult(selectedAnswer)
    }

    private fun highlightedResult(theAnswers: Int){
        when(theAnswers){
            1 -> {
                textViewQuestionOne.background = ContextCompat.getDrawable(
                    this,
                    R.drawable.correct_option_bg
                )
            }
            2 -> {
                textViewQuestionTwo.background = ContextCompat.getDrawable(
                    this,
                    R.drawable.correct_option_bg
                )
            }
            3 -> {
                textViewQuestionThree.background = ContextCompat.getDrawable(
                    this,
                    R.drawable.correct_option_bg
                )
            }
            4 -> {
                textViewQuestionFour.background = ContextCompat.getDrawable(
                    this,
                    R.drawable.correct_option_bg
                )
            }
        }
    }

}