package com.example.dialogviews

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    private lateinit var alertBtn: MaterialButton
    private var listItems = arrayOf("One", "Two", "Three", "Four")
    private var toggleColor = false
    private var toggleDescColor = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        alertBtn = findViewById(R.id.alertBtn)
        alertBtn.setOnClickListener {
           showCustomAlertDialog()
        }
    }

    private fun showAlertDialog(){
         val builder = AlertDialog.Builder(this)
         builder.
              setTitle("Welcome!")
//             .setMessage("Congratulations, you are now the best engineer in the world")
             .setPositiveButtonIcon(ContextCompat.getDrawable(this, R.drawable.ic_ok))

             .setPositiveButton("Thanks") {
                 dialog, _->
                    Toast.makeText(this, "You pressed the Thanks button", Toast.LENGTH_SHORT).show()
                }

             .setNegativeButton("Cancel") {
                     dialog, _->
                    Toast.makeText(this, "You pressed the Cancel button", Toast.LENGTH_SHORT).show()
             }

             .setNeutralButton("Remind me later"){
                     dialog, _->
                    Toast.makeText(this, "You pressed the Neutral button", Toast.LENGTH_SHORT).show()
             }

             .setMultiChoiceItems(listItems, null) {
                 dialog, which, isChecked ->
                    val selectedItem = listItems[which]
//                 when (selectedItem){
//                     "One" ->
//                         Toast.makeText(this, "You selected $selectedItem button", Toast.LENGTH_SHORT).show()
//                     "Two" ->
//                         Toast.makeText(this, "You selected $selectedItem button", Toast.LENGTH_SHORT).show()
//                     "Three" ->
//                         Toast.makeText(this, "You selected $selectedItem button", Toast.LENGTH_SHORT).show()
//                     else ->
//                         Toast.makeText(this, "You selected $selectedItem button", Toast.LENGTH_SHORT).show()
//                 }
                 if(isChecked){
                     Toast.makeText(this, "You checked $selectedItem button", Toast.LENGTH_SHORT).show()
                 }else{
                     Toast.makeText(this, "You unchecked $selectedItem button", Toast.LENGTH_SHORT).show()
                 }

             }

        val dialog = builder.create()
        dialog.show()
    }

    private fun showCustomAlertDialog(){
        val builder = AlertDialog.Builder(this)
        val inflater = LayoutInflater.from(this)
        val dialogView = inflater.inflate(R.layout.alert_dialog, null)

        val titleText = dialogView.findViewById<TextView>(R.id.title)
        val descText = dialogView.findViewById<TextView>(R.id.desc)

        toggleColor = false
        toggleDescColor = false

        titleText.setOnClickListener {
            if(toggleColor){
                titleText.setTextColor(Color.BLACK)
                toggleColor = false
            }else{
                titleText.setTextColor(Color.RED)
                toggleColor = true
            }

        }

        descText.setOnClickListener {
            if(toggleDescColor){
                descText.setTextColor(Color.BLACK)
                toggleDescColor = false
            }else{
                descText.setTextColor(Color.BLUE)
                toggleDescColor = true
            }
        }

        builder.setView(dialogView)
            .setPositiveButton(
                "OK"
            ){
                dialog, _ ->
                   Toast.makeText(this, "You clicked the OK button", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("CANCEL"){
                dialog, _ ->
                     Toast.makeText(this, "You clicked the CANCEL button", Toast.LENGTH_SHORT).show()
            }

        val dialog = builder.create()
        dialog.show()

    }
}