package com.example.mequizapp.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.mequizapp.Constants
import com.example.mequizapp.R

class QuestionsActivity : AppCompatActivity(), View.OnClickListener{
    private lateinit var imageView: ImageView
    private lateinit var progressView: ProgressBar
    private lateinit var progressText: TextView
    private lateinit var optionOne: TextView
    private lateinit var optionTwo: TextView
    private lateinit var optionThree: TextView
    private lateinit var optionFour: TextView
    private lateinit var nextButton: Button

    private var questions = Constants.getQuizQuestions()
    private var questionIndex = 0
    private var selectedAnswer = 0
    private var score = 0
    private var answered = false
    private var hasSelected = false
    private lateinit var name: String





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        imageView = findViewById(R.id.flagImage)
        progressView = findViewById(R.id.progressBar)
        progressText = findViewById(R.id.progressText)
        optionOne = findViewById(R.id.optionOne)
        optionTwo = findViewById(R.id.optionTwo)
        optionThree = findViewById(R.id.optionThree)
        optionFour = findViewById(R.id.optionFour)
        nextButton = findViewById(R.id.nextButton)


        optionOne.setOnClickListener(this)
        optionTwo.setOnClickListener(this)
        optionThree.setOnClickListener(this)
        optionFour.setOnClickListener(this)
        nextButton.setOnClickListener(this)


        name = intent.getStringExtra(Constants.PLAYER_NAME).toString()



        setQuestion()

    }

    private fun setQuestion(){
        if(questionIndex < questions.size){
            resetOptions()
            val question = questions[questionIndex]
            imageView.setImageResource(question.flag)
            progressView.progress = questionIndex
            progressText.text = "${questionIndex+1}/${questions.size}"
            optionOne.text = question.optionOne
            optionTwo.text = question.optionTwo
            optionThree.text = question.optionThree
            optionFour.text = question.optionFour

            questionIndex++
            answered = false
            hasSelected = false
        }else{
            val playerResult = "You got $score out of ${questions.size} correctly"
            Intent(this@QuestionsActivity, ResultActivity::class.java).also {
                        it.putExtra(Constants.PLAYER_NAME, name)
                        it.putExtra(Constants.SCORE, score)
                        it.putExtra(Constants.PLAYER_RESULT, playerResult)
                       startActivity(it)
                       finish()
                }
        }

    }

    private fun resetOptions(){
        val allOptions = mutableListOf(optionOne, optionTwo, optionThree, optionFour)
        for(option in allOptions){

                option.setTextColor(Color.parseColor("#4D4D4D"))
                option.textSize = 18f
                option.setTypeface(null, Typeface.NORMAL)
                option.background = ContextCompat.getDrawable(this, R.drawable.option_bg)

        }

    }

    private fun selectedOption(selectedOption: TextView, selectedOptionPosition: Int){
        if(answered){
            return
        }
        resetOptions()
        selectedAnswer = selectedOptionPosition
        hasSelected = true
        selectedOption.setTextColor(Color.BLACK)
        selectedOption.textSize = 20f
        selectedOption.setTypeface(null, Typeface.BOLD)
        selectedOption.background = ContextCompat.getDrawable(this, R.drawable.selected_option_bg)

    }

    private fun showAnswer(){
        answered = true
        if(selectedAnswer == questions[questionIndex - 1].correctAnswer){
            score++
             correctAnswer(selectedAnswer)
        }
        else{
            when(selectedAnswer){
                1 -> {
                    optionOne.background = ContextCompat.getDrawable(this, R.drawable.wrong_option_bg)
                    optionOne.setTextColor(Color.WHITE)
                    optionOne.textSize = 18f
                }
                2 -> {
                    optionTwo.background = ContextCompat.getDrawable(this, R.drawable.wrong_option_bg)
                    optionTwo.setTextColor(Color.WHITE)
                    optionTwo.textSize = 18f
                }
                3 -> {
                    optionThree.background = ContextCompat.getDrawable(this, R.drawable.wrong_option_bg)
                    optionThree.setTextColor(Color.WHITE)
                    optionThree.textSize = 18f
                }
                4 -> {
                    optionFour.background = ContextCompat.getDrawable(this, R.drawable.wrong_option_bg)
                    optionFour.setTextColor(Color.WHITE)
                    optionFour.textSize = 18f
                }

            }
            showSolution()
        }
        if(questionIndex < questions.size){
            nextButton.text = getString(R.string.next)
        }else{
            nextButton.text = getString(R.string.see_result)
        }
    }

    private fun showSolution(){
        val correctOption = questions[questionIndex - 1].correctAnswer
        correctAnswer(correctOption)

    }

    private fun correctAnswer(selectedAnswer: Int){
        when(selectedAnswer){
            1 -> {
                optionOne.background = ContextCompat.getDrawable(this, R.drawable.correct_option_bg)
            }
            2 -> {
                optionTwo.background = ContextCompat.getDrawable(this, R.drawable.correct_option_bg)
            }
            3 -> {
                optionThree.background = ContextCompat.getDrawable(this, R.drawable.correct_option_bg)
            }
            4 -> {
                optionFour.background = ContextCompat.getDrawable(this, R.drawable.correct_option_bg)
            }

        }
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.optionOne -> {

                    selectedOption( optionOne,1)


            }
            R.id.optionTwo -> {

                selectedOption( optionTwo,2)
            }
            R.id.optionThree -> {
                selectedOption(optionThree, 3)
            }
            R.id.optionFour -> {
                selectedOption(optionFour, 4)
           }
            R.id.nextButton -> {
                if(!hasSelected){
                    Toast.makeText(this@QuestionsActivity, "Select an option", Toast.LENGTH_LONG).show()
                }else{
                    if(!answered){
                        showAnswer()
                    }else{

                        setQuestion()
                    }
                }

           }

        }
    }
}