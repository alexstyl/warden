package com.alexstyl.warden

import android.app.Activity
import kotlinx.coroutines.flow.Flow

internal interface PermissionResultListener {
    fun processPermissionResult(activity: Activity, results: Map<String, Boolean>)
    fun observePermissionResults(permissions: Array<String>): Flow<PermissionResults>
}
