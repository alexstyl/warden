package com.alexstyl.warden

public sealed class PermissionState {
    public object Granted : PermissionState()
    public data class Denied(val shouldShowRationale: Boolean) : PermissionState()
}
