package com.alexstyl.warden.sample

import android.content.Intent
import android.net.Uri

fun callNumber(phoneNumber: String): Intent {
    return Intent(
        Intent.ACTION_CALL,
        phoneUri(phoneNumber)
    )
}

fun dialNumber(phoneNumber: String): Intent {
    return Intent(
        Intent.ACTION_DIAL,
        phoneUri(phoneNumber)
    )
}

fun phoneUri(phoneNumber: String): Uri {
    return Uri.fromParts("tel", phoneNumber, null)
}
