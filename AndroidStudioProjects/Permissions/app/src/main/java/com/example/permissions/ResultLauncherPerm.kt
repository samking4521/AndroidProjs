package com.example.permissions
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.material.button.MaterialButton

class ResultLauncherPerm : AppCompatActivity() {
    private lateinit var permissionsBtn: MaterialButton
    private lateinit var nextBtn: MaterialButton


    private val requestPermissions: ActivityResultLauncher<String> = registerForActivityResult(ActivityResultContracts.RequestPermission()){
        isGranted ->
                if(isGranted){
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_result_launcher_perm)

        permissionsBtn = findViewById(R.id.launcherPermBtn)
        nextBtn = findViewById(R.id.goTo)




        permissionsBtn.setOnClickListener {
            getPermissions()
        }

        nextBtn.setOnClickListener {
            val intent = Intent(this, ResultLauncherMultiplePerms::class.java)
            startActivity(intent)
        }

    }

    private fun getPermissions(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED){
            requestThePermission()
        }else{
            Toast.makeText(this, "Permission Granted Already", Toast.LENGTH_LONG).show()
        }
    }

    private fun requestThePermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_SMS)){
            showAlertDialog()
        }else{
            requestPermissions.launch(
                Manifest.permission.READ_SMS
            )
        }

    }

    private fun showAlertDialog(){
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Essential Permission")
                .setMessage("Sms is an essential, press allow to request permission")

                .setPositiveButton("Allow") {
                    dialog, _ ->
                    requestPermissions.launch(
                        Manifest.permission.READ_SMS
                    )
                }
                .setNegativeButton("Dismiss"){
                        dialog, _ ->
                            dialog.dismiss()
                }

        val dialog = builder.create()
        dialog.show()
    }
}