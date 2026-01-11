package com.example.permissions

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
import java.util.jar.Manifest

class MultiplePerms : AppCompatActivity() {
    private lateinit var multiplePermsBtn: MaterialButton
    private lateinit var nextBtn: MaterialButton

    private val permissions = mutableListOf(android.Manifest.permission.RECORD_AUDIO, android.Manifest.permission.READ_CONTACTS, android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION)
    private val permissionsToGrant = mutableListOf<String>()
    private val theRequestCode = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiple_perms)

        multiplePermsBtn = findViewById(R.id.multiplePermBtn)
        nextBtn = findViewById(R.id.goTo)

        multiplePermsBtn.setOnClickListener {
            requestMultiplePermissions()
        }

        nextBtn.setOnClickListener{
            val intent = Intent(this, ResultLauncherPerm::class.java)
            startActivity(intent)
        }
    }

    private fun requestMultiplePermissions(){
            for(permission in permissions){
                if(ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED ){
                    permissionsToGrant.add(permission)
                }
            }

          if(permissionsToGrant.isEmpty()){
              Toast.makeText(this, "Permission is already Granted", Toast.LENGTH_SHORT).show()
          }else{
              requestPermissions()
              permissionsToGrant.clear()
          }
    }

    private fun requestPermissions(){
          ActivityCompat.requestPermissions(this, permissionsToGrant.toTypedArray(), theRequestCode)
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

    }

}