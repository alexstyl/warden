package com.alexstyl.warden

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions

internal class WardenActivity : ComponentActivity() {

    private val requestPermission = registerForActivityResult(RequestMultiplePermissions()) {
        PermissionResultsListener.processPermissionResult(this, it)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val permissions: Array<String> = requireNotNull(
            intent.getStringArrayExtra(EXTRA_PERMISSIONS)
        )
        requestPermission.launch(permissions)
    }

    companion object {
        private const val EXTRA_PERMISSIONS = "extra:permissions"

        fun requestPermission(context: Context, forPermission: Array<String>): Intent {
            return Intent(context, WardenActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                putExtra(EXTRA_PERMISSIONS, forPermission)
            }
        }
    }
}
