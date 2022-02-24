package com.alexstyl.warden

import android.app.Activity
import androidx.core.app.ActivityCompat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter

internal object PermissionResultsListener : PermissionResultListener {

    private val onPermissionResults = MutableSharedFlow<PermissionResults>(
        extraBufferCapacity = 1
    )

    override fun processPermissionResult(activity: Activity, results: Map<String, Boolean>) {
        val value = results.map { (permission, isGranted) ->
            val state = if (isGranted) {
                PermissionState.Granted
            } else {
                val shouldShowRationale = ActivityCompat.shouldShowRequestPermissionRationale(
                    activity,
                    permission
                )
                PermissionState.Denied(shouldShowRationale)
            }
            permission to state
        }.toMap()

        onPermissionResults.tryEmit(PermissionResults(value))
    }

    override fun observePermissionResults(permissions: Array<String>): Flow<PermissionResults> {
        val keys = permissions.toSet()
        return onPermissionResults.filter {
            it.value.keys == keys
        }
    }
}
