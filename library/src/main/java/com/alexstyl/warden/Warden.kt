package com.alexstyl.warden

import android.content.Context

public interface Warden {

    /**
     * Blocks the suspend function until there is a user interaction for the given permission.
     */
    public suspend fun requestPermission(forPermission: String): PermissionState

    /**
     * Blocks the suspend function until there is a user interaction for all given permissions.
     */
    public suspend fun requestPermissions(forPermission: Array<String>): PermissionResults

    public companion object {
        public fun with(context: Context): Warden {
            return AndroidWarden(
                context.applicationContext,
                PermissionResultsListener
            )
        }
    }
}
