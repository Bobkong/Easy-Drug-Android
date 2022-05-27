package com.example.easydrug.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import com.example.easydrug.R
import com.example.easydrug.Utils.UIUtils
import com.githang.statusbar.StatusBarCompat


class MainActivity : Activity() {

    private val TAG = "MainActivity"
    private var userAvatar: ImageView? = null
    private var scanCl: ConstraintLayout? = null
    private var foodCl: ConstraintLayout? = null
    private var exploreCl: ConstraintLayout? = null
    private var drugListCl: ConstraintLayout? = null
    private var learnCl:ConstraintLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        StatusBarCompat.setStatusBarColor(this, this.resources.getColor(R.color.bg_color))
        userAvatar = findViewById(R.id.user_avatar)
        scanCl = findViewById(R.id.scan_drug)
        setFeatureWidth(scanCl)
        foodCl = findViewById(R.id.check_food)
        setFeatureWidth(foodCl)
        exploreCl = findViewById(R.id.explore)
        setFeatureWidth(exploreCl)
        drugListCl = findViewById(R.id.drug_list)
        setFeatureWidth(drugListCl)
        learnCl = findViewById(R.id.learn_cl)

        learnCl?.setOnClickListener {
            startActivity(Intent(this, OnboardingActivity::class.java))
        }

        scanCl?.setOnClickListener {
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),1);
            } else {
                startActivity(Intent(this, ScanDrugActivity::class.java))
            }

        }

        foodCl?.setOnClickListener {
            startActivity(Intent(this, SpeechFoodActivity::class.java))
        }

        drugListCl?.setOnClickListener {
            startActivity(Intent(this, DrugDetailActivity::class.java))
        }
//
//        inputFood?.setOnClickListener {
//            startActivity(Intent(this, SpeechFoodActivity::class.java))
//        }

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
                    Toast.makeText(this, "Permission Request is Rejected!", Toast.LENGTH_LONG).show()

                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri: Uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Permission Request is Rejected!", Toast.LENGTH_LONG).show()
                }
            } else {
                startActivity(Intent(this, ScanDrugActivity::class.java))
            }
        }
    }

    private fun setFeatureWidth(view: View?) {
        val width = (UIUtils.getWidth(this) - UIUtils.dp2px(this, 64F)) / 2
        val params = view?.layoutParams
        params?.width = width
        view?.layoutParams = params
    }
}