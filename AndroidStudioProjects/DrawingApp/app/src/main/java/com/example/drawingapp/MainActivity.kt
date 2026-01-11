package com.example.drawingapp


import android.Manifest
import android.app.AlertDialog
import android.app.Dialog
import android.content.pm.PackageManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import yuku.ambilwarna.AmbilWarnaDialog
import yuku.ambilwarna.AmbilWarnaDialog.OnAmbilWarnaListener


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var myDrawingView: MyDrawingView
    private lateinit var imageButton: ImageButton
    private lateinit var purpleBtn: ImageButton
    private lateinit var greenBtn: ImageButton
    private lateinit var redBtn: ImageButton
    private lateinit var orangeBtn: ImageButton
    private lateinit var blueBtn: ImageButton
    private lateinit var undoBtn: ImageButton
    private lateinit var colorPickerBtn: ImageButton
    private lateinit var galleryBtn: ImageButton

    private val requestPermission: ActivityResultLauncher<Array<String>> = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){
        permissions ->
        permissions.entries.forEach{
            val permissionName = it.key
            val isGranted = it.value

            if(isGranted && permissionName == Manifest.permission.READ_EXTERNAL_STORAGE) {
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
            }else{
                if(permissionName == Manifest.permission.READ_EXTERNAL_STORAGE) {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         myDrawingView = findViewById(R.id.myDrawingView)
         imageButton = findViewById(R.id.brush_button)
         myDrawingView.changeBrushSize(10.toFloat())

         purpleBtn = findViewById(R.id.purple_button)
        blueBtn = findViewById(R.id.blue_button)
        greenBtn = findViewById(R.id.green_button)
        orangeBtn = findViewById(R.id.orange_button)
        redBtn = findViewById(R.id.red_button)
        undoBtn = findViewById(R.id.undo_button)
        colorPickerBtn = findViewById(R.id.color_picker_button)
        galleryBtn = findViewById(R.id.gallery_button)

        imageButton.setOnClickListener{
             showChangeBrushSizeDialog()
        }

        purpleBtn.setOnClickListener(this)
        blueBtn.setOnClickListener(this)
        greenBtn.setOnClickListener(this)
        orangeBtn.setOnClickListener(this)
        redBtn.setOnClickListener(this)
        undoBtn.setOnClickListener(this)
        colorPickerBtn.setOnClickListener(this)
        galleryBtn.setOnClickListener(this)
    }

    fun showChangeBrushSizeDialog(){
         val brushDialog = Dialog(this@MainActivity)
        brushDialog.setContentView(R.layout.dialog_brush)
        val seekProgressView = brushDialog.findViewById<SeekBar>(R.id.dialog_seek_bar)
        val seekProgressText = brushDialog.findViewById<TextView>(R.id.dialog_textview_progress)

        seekProgressView.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                myDrawingView.changeBrushSize(seekBar.progress.toFloat())
                seekProgressText.text = seekBar.progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
        brushDialog.show()
    }

    override fun onClick(view: View?) {
       when(view?.id){
           R.id.purple_button -> {
               myDrawingView.setColor("#A808FA")
           }
           R.id.green_button -> {
               myDrawingView.setColor("#789D0A")
           }
           R.id.red_button -> {
               myDrawingView.setColor("#B90707")
           }
           R.id.orange_button -> {
               myDrawingView.setColor("#BE820B")
           }
           R.id.blue_button -> {
               myDrawingView.setColor("#0C98CC")
            }
           R.id.undo_button -> {
               myDrawingView.undoPath()
           }
           R.id.color_picker_button -> {
               showColorPickerDialog()
           }
           R.id.gallery_button -> {
                if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                        requestStoragePermission()
               }else{
                   // get the image
               }
           }
       }


    }

    private fun showColorPickerDialog(){
        val dialog = AmbilWarnaDialog(this, Color.GREEN, object: OnAmbilWarnaListener {
            override fun onCancel(dialog: AmbilWarnaDialog?) {

            }

            override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
                myDrawingView.setColor(color)
            }

        })
        dialog.show()
    }

    private fun requestStoragePermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )){
            showRationaleDialog()
        }else {
            requestPermission.launch(
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            )
        }
    }

    private fun showRationaleDialog(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Storage Permission")
            .setMessage("We need this permission in order to access the internal storage")
            .setPositiveButton(R.string.dialog_yes) {
                dialog, _ ->
                requestPermission.launch(
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                )
                     dialog.dismiss()
            }
         builder.create().show()
    }

}

