package com.example.permissions

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    private lateinit var permissionBtn: MaterialButton
    private lateinit var goToBtn: MaterialButton
    private var theRequestCode = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        permissionBtn = findViewById(R.id.permissionBtn)
        goToBtn = findViewById(R.id.goTo)



        permissionBtn.setOnClickListener {
            getPermission()
        }

        goToBtn.setOnClickListener {
            val intent = Intent(this, MultiplePerms::class.java)
            startActivity(intent)
        }
    }

    private fun getPermission(){
           if(ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
                  requestPermission()
           }else {
                Toast.makeText(this, "Permission Granted Already", Toast.LENGTH_SHORT).show()
           }
    }

    private fun requestPermission(){
          ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), theRequestCode)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode != theRequestCode){
            return
        }
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }
}

