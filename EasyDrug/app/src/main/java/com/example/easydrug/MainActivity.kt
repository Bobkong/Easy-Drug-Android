package com.example.easydrug

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.microsoft.cognitiveservices.speech.*


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private var startScan: TextView? = null
    private var inputFood: TextView? = null
    private var textToSpeech: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startScan = findViewById<TextView>(R.id.start_scan)
        inputFood = findViewById<TextView>(R.id.input_food)
        textToSpeech = findViewById<TextView>(R.id.text_to_speech)

        startScan?.setOnClickListener {
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),1);
            } else {
                startActivity(Intent(this, ScanDrugActivity::class.java))
            }

        }

        inputFood?.setOnClickListener {
            startActivity(Intent(this, SpeechFoodActivity::class.java))
        }

        textToSpeech?.setOnClickListener {
            startActivity(Intent(this, TextToSpeechActivity::class.java))
        }

//        SignService.getInstance().signUp("123", "123").observeOn(AndroidSchedulers.mainThread())
//            .subscribe {
//                Toast.makeText(this, it.string(), Toast.LENGTH_SHORT).show()
//            }
//
//
//        DrugService.getInstance().getDrugList("1234", "123").observeOn(AndroidSchedulers.mainThread())
//            .subscribe {
//                if (it != null && it.drugList != null && it.drugList.size != 0) {
//                    Log.i(TAG, it.drugList.size.toString())
//                } else {
//                    Log.i(TAG, "drug list is null")
//                }
//            }
//
//        DrugService.getInstance().addDrug("1234", "xxxx", "abc.com", "001").observeOn(AndroidSchedulers.mainThread())
//            .subscribe {
//                Log.i(TAG, it.string())
//            }
//
//        DrugService.getInstance().removeDrug("1234", "001").observeOn(AndroidSchedulers.mainThread())
//            .subscribe {
//                Log.i(TAG, it.string())
//            }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] !== PackageManager.PERMISSION_GRANTED) {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(
                        this@MainActivity,
                        Manifest.permission.CAMERA
                    )
                ) {
                    Toast.makeText(this, "被永遠拒絕，只能使用者手動給予權限", Toast.LENGTH_LONG).show()

                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri: Uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "按下拒絕", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "允許權限", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, ScanDrugActivity::class.java))
            }
        }
    }
}