package com.idenfy.permissionissue

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var permissionsDelegateUtil:PermissionsDelegateUtil
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionsDelegateUtil = PermissionsDelegateUtil(this)
        setContentView(R.layout.activity_main)
        if (!permissionsDelegateUtil.hasCameraPermission() && savedInstanceState == null) {
            Log.d(PermissionsDelegateUtil.RESULT_TAG, "checking permissions")
            permissionsDelegateUtil.requestCameraPermission()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        val result = permissionsDelegateUtil.resultGranted(requestCode, permissions, grantResults)
        Log.d(PermissionsDelegateUtil.RESULT_TAG, "onRequestPermissionsResult:$result")
        handleCameraPermissionResult(permissionResult = result)
    }

    private fun handleCameraPermissionResult(permissionResult: PermissionResult) {
        when (permissionResult) {
            PermissionResult.PermissionGranted -> {
                //Permission was granted
            }
            PermissionResult.PermissionNotGrantedRetryAuto -> {
                //Retrying permission request
                permissionsDelegateUtil.requestCameraPermission()

            }
            PermissionResult.PermissionNotGrantedCantRetry -> {
                //Display a new window, explaining user to enable permission manually in the app system settings
            }
            PermissionResult.PermissionNotGrantedBySystem -> {
                //Permission request was canceled by a system, don't ask automatically again
            }
        }
    }
}