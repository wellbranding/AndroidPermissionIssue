package com.idenfy.permissionissue

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener


class MainActivity : AppCompatActivity() {
    var permissionsDelegateUtil = PermissionsDelegateUtil()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(PermissionsDelegateUtil.RESULT_TAG, "onCreate")
        if(!permissionsDelegateUtil.hasCameraPermission(this) && savedInstanceState==null) {
            Log.d(PermissionsDelegateUtil.RESULT_TAG, "checking")
            permissionsDelegateUtil.requestCameraPermission(this)

        }


    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        val result = permissionsDelegateUtil.resultGranted(this, requestCode, permissions, grantResults)
        Log.d(PermissionsDelegateUtil.RESULT_TAG, "onRequestPermissionsResult:$result")
        handleCameraPermissionResult(permissionResult = result)
    }

    private fun handleCameraPermissionResult(permissionResult:PermissionResult){
        when(permissionResult){
            PermissionResult.PermissionGranted -> {

            }
            PermissionResult.PermissionNotGrantedRetryAuto -> {
                permissionsDelegateUtil.requestCameraPermission(this)

            }
            PermissionResult.PermissionNotGrantedCantRetry -> {
                permissionsDelegateUtil.requestCameraPermission(this)
            }
            PermissionResult.PermissionNotGrantedDontAsk -> {
            }
        }
    }
}