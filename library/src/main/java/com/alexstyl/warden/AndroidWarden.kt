package com.alexstyl.warden

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

public class AndroidWarden internal constructor(
    private val context: Context,
    private val observer: PermissionResultListener
) : Warden {

    override suspend fun requestPermission(forPermission: String): PermissionState {
        return requestPermissions(arrayOf(forPermission)).value.values.first()
    }

    override suspend fun requestPermissions(forPermission: Array<String>): PermissionResults {
        val allGranted = forPermission.fold(true) { acc, permission ->
            acc && isPermissionGranted(permission)
        }
        return if (allGranted) {
            PermissionResults(forPermission.associateWith { PermissionState.Granted })
        } else {
            context.startActivity(WardenActivity.requestPermission(context, forPermission))
            return observeResponse(forPermission).first()
        }
    }

    private fun isPermissionGranted(forPermission: String): Boolean {
        return ContextCompat
            .checkSelfPermission(context, forPermission) == PackageManager.PERMISSION_GRANTED
    }

    private fun observeResponse(forPermission: Array<String>): Flow<PermissionResults> {
        return observer.observePermissionResults(forPermission)
    }
}
