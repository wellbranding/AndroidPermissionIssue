package com.idenfy.permissionissue

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.annotation.Keep
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

class PermissionsDelegateUtil(val context: FragmentActivity) {
    fun hasCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
                context,
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_PERMISSIONS_CAMERA
        )
    }

    fun resultGranted(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ): PermissionResult {
        if (requestCode != REQUEST_PERMISSIONS_CAMERA) {
            Log.d(RESULT_TAG, "requestCode does not match")
            return PermissionResult.PermissionNotGrantedBySystem
        }
        if (grantResults.isEmpty()) {
            Log.d(RESULT_TAG, "grantResults.isEmpty()")
            return PermissionResult.PermissionNotGrantedBySystem
        }
        if (permissions[0] != Manifest.permission.CAMERA) {
            return PermissionResult.PermissionNotGrantedBySystem
        }
        return if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            PermissionResult.PermissionGranted

        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (context.shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                    PermissionResult.PermissionNotGrantedRetryAuto
                } else {
                    ///User selected don't show again checkbox.
                    PermissionResult.PermissionNotGrantedCantRetry
                }
            } else {
                PermissionResult.PermissionNotGrantedRetryAuto
            }
        }
    }

    @Keep
    companion object {
        const val RESULT_TAG = "CAMERA_PERMISSION"
        const val REQUEST_PERMISSIONS_CAMERA = 1001
    }

}

