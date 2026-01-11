package com.example.permissions

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class ResultLauncherMultiplePerms : AppCompatActivity() {
    private lateinit var permBtn: MaterialButton

    private val permissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.BODY_SENSORS, Manifest.permission.RECEIVE_MMS )
    private val permissionsToGrant = mutableListOf<String>()

    private val requestPermissions: ActivityResultLauncher<Array<String>> = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){
        thePermissions -> thePermissions.entries.forEach {
            val isGranted = it.value

        if(isGranted){
            Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
        }

    }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_result_launcher_multiple_perms)

        permBtn = findViewById(R.id.getPerms)


        permBtn.setOnClickListener {
            getPermissions()
        }

    }

    private fun getPermissions(){
        for(permission in permissions){
            if(ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
                  permissionsToGrant.add(permission)
            }
        }

        if(permissionsToGrant.isEmpty()){
            Toast.makeText(this, "Permission already Granted", Toast.LENGTH_SHORT).show()
        }else{
            requestThePermissions()
            permissionsToGrant.clear()
        }


    }

    private fun requestThePermissions(){
            requestPermissions.launch(
                permissionsToGrant.toTypedArray()
            )
    }
}