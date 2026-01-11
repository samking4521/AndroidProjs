package com.example.storage

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    private lateinit var saveBtn: Button
    private lateinit var textInput: EditText
    private lateinit var btnLoad: Button
    private lateinit var text: TextView
    private lateinit var saveFileLocation: Button

    private var fileName = "myCustomFile.txt"

    private val requestPermission: ActivityResultLauncher<Array<String>> = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){
          permissions ->
              permissions.entries.forEach {
                   val isGranted = it.value
                   val name = it.key

                   if( (name == android.Manifest.permission.WRITE_EXTERNAL_STORAGE) && isGranted ){
                         Toast.makeText(this, "Write Permission Granted", Toast.LENGTH_SHORT).show()
                   }else if((name == android.Manifest.permission.READ_EXTERNAL_STORAGE) && isGranted){
                       Toast.makeText(this, "Read Permission Granted", Toast.LENGTH_SHORT).show()
                        }
                   else {
                       if(name == android.Manifest.permission.WRITE_EXTERNAL_STORAGE){
                           Toast.makeText(this, "Write Permission Denied", Toast.LENGTH_SHORT).show()
                       }else{
                           Toast.makeText(this, "Read Permission Denied", Toast.LENGTH_SHORT).show()
                       }

                   }
              }

    }

    private val fileContracts = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result -> result.data?.data.let {
            uri ->
               saveFile(uri!!)
             }
       }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        saveBtn = findViewById(R.id.btn_save_file)
        textInput = findViewById(R.id.et_file_contents)
        btnLoad = findViewById(R.id.btn_load_file)
        text = findViewById(R.id.tv_file_contents)
        saveFileLocation = findViewById(R.id.btn_save_ext_file_in_downloads)

        val isReadPermissionGranted = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
        val isWritePermissionGranted = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED

        if(isReadPermissionGranted || isWritePermissionGranted){
            requestPermission.launch(
                arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE)
            )
        }

        saveBtn.setOnClickListener {
            try{
                val file = File(Environment.getExternalStorageDirectory(), fileName)
                file.writeText(textInput.text.toString())
                Toast.makeText(this, "File Saved", Toast.LENGTH_SHORT).show()
            }catch(e: Exception){
                Toast.makeText(this, "Error saving file", Toast.LENGTH_SHORT).show()
                Log.d("fileError", "Error: ${e.message}")
            }
        }

        btnLoad.setOnClickListener{
            try {
                val file = File(Environment.getExternalStorageDirectory(), fileName)
                text.text = file.readText()
            }catch (e:Exception){
                Toast.makeText(this, "Error reading file", Toast.LENGTH_SHORT).show()
                Log.d("fileError", "Error: ${e.message}")
            }
        }

        saveFileLocation.setOnClickListener{
            val intent = Intent(Intent.ACTION_CREATE_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TITLE, fileName)
            fileContracts.launch(intent)
        }
    }

    private fun saveFile(uri: Uri){
        val parcelFileDescriptor = this.contentResolver.openFileDescriptor(uri, "w")
        val fileOutputStream = FileOutputStream(parcelFileDescriptor?.fileDescriptor)
        fileOutputStream.write(textInput.text.toString().toByteArray())
        fileOutputStream.close()
        parcelFileDescriptor?.close()
    }
}