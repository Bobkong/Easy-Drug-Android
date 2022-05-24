package com.example.easydrug.activity

import android.app.Activity
import android.os.Bundle
import android.os.Vibrator
import android.widget.Toast
import cn.bingoogolapple.qrcode.core.QRCodeView
import cn.bingoogolapple.qrcode.zbar.ZBarView
import com.example.easydrug.R
import com.example.easydrug.netservice.Api.DrugLookUpService
import io.reactivex.android.schedulers.AndroidSchedulers

class ScanDrugActivity: Activity(), QRCodeView.Delegate {

    private val TAG = "ScanDrugActivity"
    private var mZBarView: ZBarView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_drug)

        mZBarView = findViewById<ZBarView>(R.id.zbarview)
        mZBarView?.setDelegate(this)

    }

    override fun onStart() {
        super.onStart()
        mZBarView?.startCamera()
        mZBarView?.showScanRect()
        mZBarView?.startSpot()
    }

    override fun onStop() {
        mZBarView?.stopCamera()
        super.onStop()
    }

    override fun onDestroy() {
        mZBarView?.onDestroy()
        super.onDestroy()
    }

    override fun onScanQRCodeSuccess(result: String?) {
        vibrate()
        mZBarView?.stopSpot()

        result?.let {
            DrugLookUpService.getInstance().drugLookUp(it).observeOn(AndroidSchedulers.mainThread())
                .subscribe { drug ->
                    if (drug.items != null && drug.items.size != 0) {
                        // ensure scanning a drug
                        if (drug.items[0].category.startsWith("Health")) {
                            Toast.makeText(this, drug.items[0].title, Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Please scan a drug", Toast.LENGTH_SHORT).show()
                        }

                    }
                }
        }
    }

    override fun onCameraAmbientBrightnessChanged(isDark: Boolean) {

    }

    override fun onScanQRCodeOpenCameraError() {
        Toast.makeText(this, "scan barcode failed", Toast.LENGTH_SHORT).show()
    }


    private fun vibrate() {
        val vibrator: Vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(200)
    }


}