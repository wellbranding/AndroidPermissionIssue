package com.idenfy.permissionissue

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    var permissionsDelegateUtil = PermissionsDelegateUtil()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (!permissionsDelegateUtil.hasCameraPermission(this) && savedInstanceState == null) {
            Log.d(PermissionsDelegateUtil.RESULT_TAG, "checking permissions")
            permissionsDelegateUtil.requestCameraPermission(this)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        val result = permissionsDelegateUtil.resultGranted(this, requestCode, permissions, grantResults)
        Log.d(PermissionsDelegateUtil.RESULT_TAG, "onRequestPermissionsResult:$result")
        handleCameraPermissionResult(permissionResult = result)
    }

    private fun handleCameraPermissionResult(permissionResult: PermissionResult) {
        when (permissionResult) {
            PermissionResult.PermissionGranted -> {
                //Permission was granted
            }
            PermissionResult.PermissionNotGrantedRetryAuto -> {
                permissionsDelegateUtil.requestCameraPermission(this)

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