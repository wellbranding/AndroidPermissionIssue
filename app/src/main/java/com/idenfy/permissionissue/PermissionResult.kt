package com.idenfy.permissionissue

sealed class PermissionResult {
    object PermissionGranted : PermissionResult()
    object PermissionNotGrantedRetryAuto : PermissionResult()
    object PermissionNotGrantedBySystem : PermissionResult()
    object PermissionNotGrantedCantRetry : PermissionResult()
}