package com.example.mydrawingapp

import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.graphics.createBitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import yuku.ambilwarna.AmbilWarnaDialog
import yuku.ambilwarna.AmbilWarnaDialog.OnAmbilWarnaListener
import java.io.File
import java.io.FileOutputStream
import kotlin.random.Random

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var blackBtn: ImageButton
    private lateinit var blueBtn: ImageButton
    private lateinit var goldBtn: ImageButton
    private lateinit var brownBtn: ImageButton
    private lateinit var redBtn: ImageButton
    private lateinit var myDrawingView: DrawingView
    private lateinit var undoBtn: ImageButton
    private lateinit var paintBrush: ImageButton
    private lateinit var colorPicker: ImageButton
    private lateinit var galleryBtn: ImageButton
    private lateinit var saveBtn: ImageButton
    private var seekProgress = 20f

    private val openGalleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()){
          uri ->
                    findViewById<ImageView>(R.id.gallery_image).setImageURI(uri)
    }



    private val requestPermission: ActivityResultLauncher<Array<String>> = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){
          permissions -> permissions.entries.forEach {
              val permissionName = it.key
              val isGranted = it.value

              if(isGranted &&  permissionName == android.Manifest.permission.READ_EXTERNAL_STORAGE){
                  Toast.makeText(this, "Read Permission granted", Toast.LENGTH_SHORT).show()
                  openGalleryLauncher.launch("image/*")
              }
              else if (isGranted && (permissionName == android.Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                  Toast.makeText(this, "Write Permission granted", Toast.LENGTH_SHORT).show()
                 CoroutineScope(IO).launch {
                     val theBitmap = getBitmapFromView(findViewById(R.id.constraint_l3))
                     saveImage(theBitmap)
                 }

              }
              else{
                  if(permissionName == android.Manifest.permission.READ_EXTERNAL_STORAGE){
                      Toast.makeText(this, "Read Permission denied", Toast.LENGTH_SHORT).show()
                  }
                  else{
                      Toast.makeText(this, "Write Permission denied", Toast.LENGTH_SHORT).show()
                  }
              }
          }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myDrawingView = findViewById(R.id.myDrawingView)
        blackBtn = findViewById(R.id.blackBtn)
        blueBtn = findViewById(R.id.blueBtn)
        goldBtn = findViewById(R.id.goldBtn)
        brownBtn = findViewById(R.id.brownBtn)
        redBtn = findViewById(R.id.redBtn)
        undoBtn = findViewById(R.id.undo_ic)
        paintBrush = findViewById(R.id.paint_ic)
        colorPicker = findViewById(R.id.colorPicker)
        galleryBtn = findViewById(R.id.gallery_ic)
        saveBtn = findViewById(R.id.save_ic)


        blackBtn.setOnClickListener(this)
        blueBtn.setOnClickListener(this)
        goldBtn.setOnClickListener(this)
        brownBtn.setOnClickListener(this)
        redBtn.setOnClickListener(this)
        undoBtn.setOnClickListener(this)
        paintBrush.setOnClickListener(this)
        colorPicker.setOnClickListener(this)
        galleryBtn.setOnClickListener(this)
        saveBtn.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.blackBtn -> {
                myDrawingView.setColor("#FF000000")
            }

            R.id.blueBtn -> {
                myDrawingView.setColor("#0000FF")
            }

            R.id.goldBtn -> {
                myDrawingView.setColor("#FFD700")
            }

            R.id.brownBtn -> {
                myDrawingView.setColor("#A52A2A")
            }

            R.id.redBtn -> {
                myDrawingView.setColor("#FF0000")
            }

            R.id.undo_ic -> {
                myDrawingView.undoPath()
            }

            R.id.paint_ic -> {
                showDialog(this, seekProgress)
            }

            R.id.colorPicker -> {
                showColorPickerDialog()
            }

            R.id.gallery_ic -> {
                if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    requestStoragePermission()
                }
                else{
                    // open gallery and get image
                    openGalleryLauncher.launch("image/*")
                }
            }
            R.id.save_ic -> {
                // Save the image
                if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    requestStoragePermission()
                }else{
                    val layout = findViewById<ConstraintLayout>(R.id.constraint_l3)
                    val bitmap = getBitmapFromView(layout)
                    CoroutineScope(IO).launch{
                        saveImage(bitmap)
                    }

                }
            }
        }
    }

    private fun showColorPickerDialog(){
        val dialog = AmbilWarnaDialog(this, Color.RED, object: OnAmbilWarnaListener {
            override fun onCancel(dialog: AmbilWarnaDialog?) {

            }

            override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
                myDrawingView.setColor(color)
            }

        } )

        dialog.show()
    }

    private fun showDialog(context: Context, seekProgress: Float) {
        val builder = AlertDialog.Builder(context)
        val inflater = LayoutInflater.from(context)
        val dialogLayout = inflater.inflate(R.layout.dialog_brush, null)

        val seekBar = dialogLayout.findViewById<SeekBar>(R.id.seekBar)
        val seekBarText = dialogLayout.findViewById<TextView>(R.id.seekText)

        seekBar.progress = seekProgress.toInt()
        seekBarText.text = seekProgress.toInt().toString()
        builder.setView(dialogLayout)

        val dialog = builder.create()
        dialog.show()  // show only once

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    myDrawingView.changeBrushSize(progress.toFloat())
                    seekBarText.text = progress.toString()
                    this@MainActivity.seekProgress = progress.toFloat()
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                dialog.dismiss()
            }
        })
    }

    private fun requestStoragePermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)){
            showRationaleDialog()
        }
        else{
            requestPermission.launch(
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            )
        }
    }

    private fun showRationaleDialog(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Storage permission")
            .setMessage("We need this permission in order to access the external storage")
            .setPositiveButton("Yes"){
                dialog, _ ->
                    requestPermission.launch(
                        arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE,
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    )
                    dialog.dismiss()
            }
        builder.create().show()
    }

    private fun getBitmapFromView(view: View): Bitmap {
        val bitmap = createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

//    private suspend fun saveImage(bitmap: Bitmap){
//        val root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString()
//        val myDir = File("$root/saved_images")
//        myDir.mkdir()
//        val generator = Random
//        var n = 10000
//        n = generator.nextInt(n)
//        val outputFile = File(myDir, "Images-$n.jpg")
//        if(outputFile.exists()){
//            outputFile.delete()
//        }else{
//            try {
//                val out = withContext(IO) {
//                    FileOutputStream(outputFile)
//                }
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
//                withContext(IO) {
//                    out.flush()
//                    out.close()
//                }
//
//            }catch (e: Exception){
//                e.stackTrace
//            }
//
//            withContext(Main){
//                Toast.makeText(this@MainActivity, "${outputFile.absolutePath} saved", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

    private suspend fun saveImage(bitmap: Bitmap) {
        withContext(IO) {
            try {
                val contentValues = ContentValues().apply {
                    put(MediaStore.Images.Media.DISPLAY_NAME, "Image-${System.currentTimeMillis()}.jpg")
                    put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                    put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/saved_images")
                    put(MediaStore.Images.Media.IS_PENDING, 1)
                }

                val resolver = applicationContext.contentResolver
                val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

                uri?.let {
                    resolver.openOutputStream(it).use { out ->
                        if (out != null) {
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
                        }
                    }

                    contentValues.clear()
                    contentValues.put(MediaStore.Images.Media.IS_PENDING, 0)
                    resolver.update(uri, contentValues, null, null)

                    withContext(Main) {
                        Toast.makeText(
                            this@MainActivity,
                            "Saved to gallery",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}