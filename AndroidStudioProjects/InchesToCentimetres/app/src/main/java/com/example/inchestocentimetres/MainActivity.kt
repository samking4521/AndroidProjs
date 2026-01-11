package com.example.inchestocentimetres

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
//    Inch to centimetres
//    private var constant = 2.54
//    private lateinit var button: Button
//    private lateinit var editText: EditText
//    private lateinit var textView: TextView

//  Change Color
//    private lateinit var parentView: View
//    private lateinit var floatButton: FloatingActionButton
//    val colorList = arrayOf(Color.RED, Color.CYAN, Color.GREEN, Color.GRAY, Color.MAGENTA, Color.BLUE, Color.YELLOW)

//   Radio Button
//    private lateinit var radioGroup: RadioGroup
//    private lateinit var radioButton: RadioButton

//    Seek Bar
//    private lateinit var seekBar: SeekBar
//    private lateinit var textView: TextView

//    Toggle Button
//    private lateinit var toggleButton: ToggleButton
//    private lateinit var textView: TextView

    private lateinit var checkBoxKotlin: CheckBox
    private lateinit var checkBoxJava: CheckBox
    private lateinit var checkBoxSwift: CheckBox
    private lateinit var button: Button
    private lateinit var statusResultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        Toast.makeText(this@MainActivity, "onCreate executed", Toast.LENGTH_SHORT).show()
    super.onCreate(savedInstanceState)
    setContentView(R.layout.checkbox)

//  Inch To Centimetres
//        button = findViewById(R.id.button)
//        editText = findViewById(R.id.editTextText)
//        textView = findViewById(R.id.textView2)
//
//        button.setOnClickListener {
//
//            if(editText.text.isNotEmpty()){
//                val inputVal = editText.text.toString().toDouble() * constant
//                textView.text = inputVal.toString()
//                textView.setTextColor(Color.BLACK)
//                editText.text = null
//            }else{
//                textView.text = getString(R.string.msg)
//                textView.setTextColor(Color.RED)
//            }
//        }

//       Change Color
//        parentView = findViewById(R.id.view)
//        floatButton = findViewById(R.id.button)
//
//        floatButton.setOnClickListener {
//
//                parentView.setBackgroundColor(colorList[Random.nextInt(colorList.size)])
//
//
//        }

//        Radio Button
//        radioGroup = findViewById(R.id.radioGroup)
//        radioGroup.setOnCheckedChangeListener { _, id ->
//              radioButton = findViewById(id)
//            when(radioButton.id){
//                R.id.yesButton -> {
//                    Toast.makeText(this@MainActivity, "Yes button is clicked", Toast.LENGTH_SHORT).show()
//                }
//                R.id.noButton -> {
//                    Toast.makeText(this@MainActivity, "No button is clicked", Toast.LENGTH_SHORT).show()
//                }
//                R.id.maybeButton -> {
//                    Toast.makeText(this@MainActivity, "Maybe button is clicked", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }

//        Seek Bar
//            seekBar = findViewById(R.id.seekBar)
//            textView = findViewById(R.id.finalView)
//
//        seekBar.setOnSeekBarChangeListener(object: OnSeekBarChangeListener {
//            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//               Toast.makeText(this@MainActivity, "onProgressChanged", Toast.LENGTH_SHORT).show()
//                textView.text = getString(R.string.rateText) + progress.toString()
//            }
//
//            override fun onStartTrackingTouch(seekBar: SeekBar?) {
//                Toast.makeText(this@MainActivity, "onStartTrackingTouch", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onStopTrackingTouch(seekBar: SeekBar?) {
//                Toast.makeText(this@MainActivity, "onStopTrackingTouch", Toast.LENGTH_SHORT).show()
//                textView.text = getString(R.string.finalRate) + seekBar?.progress
//            }
//
//        })

//        Toggle Button
//        toggleButton = findViewById(R.id.toggleButton)
//        textView = findViewById(R.id.toggleText)
//
//        toggleButton.setOnCheckedChangeListener { _, isChecked ->
//            if(isChecked){
//                textView.visibility = View.VISIBLE
//                textView.text = getString(R.string.visibleText)
//            }else{
//                textView.visibility = View.INVISIBLE
//            }
//        }
//

        statusResultTextView = findViewById(R.id.textViewOutput)
        checkBoxKotlin = findViewById(R.id.CheckBoxKotlin)
        checkBoxJava = findViewById(R.id.checkBoxJava)
        checkBoxSwift = findViewById(R.id.CheckBoxSwift)
        button = findViewById(R.id.checkButton)

        button.setOnClickListener {
            val sb = StringBuilder()
            sb.append("Language : " + checkBoxKotlin.text.toString() + ", Status is : ", checkBoxKotlin.isChecked.toString() + "\n")
            sb.append("Language : " + checkBoxJava.text.toString() + ", Status is : ", checkBoxJava.isChecked.toString() + "\n")
            sb.append("Language : " + checkBoxSwift.text.toString() + ", Status is : ", checkBoxSwift.isChecked.toString())

            statusResultTextView.text = sb.toString()
        }


  }


    override fun onStart() {
        super.onStart()
        Toast.makeText(this@MainActivity, "onStart executed", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this@MainActivity, "onResume executed", Toast.LENGTH_SHORT).show()
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(this@MainActivity, "onRestart executed", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this@MainActivity, "onPause executed", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this@MainActivity, "onStop executed", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this@MainActivity, "onDestroy executed", Toast.LENGTH_SHORT).show()
    }
}