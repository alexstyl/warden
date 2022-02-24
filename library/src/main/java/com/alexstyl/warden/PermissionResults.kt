package com.alexstyl.warden

/**
 * Class that represents a collection of permission responses
 */
public data class PermissionResults(
    /**
     * A map with keys representing permissions and values representing whether
     * the permission is granted or not.
     */
    val value: Map<String, PermissionState>
)