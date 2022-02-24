package com.alexstyl.warden.sample

import android.Manifest
import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.alexstyl.warden.PermissionState
import com.alexstyl.warden.Warden
import com.alexstyl.warden.sample.theme.SampleAppTheme
import kotlinx.coroutines.launch

class DialerActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)

        setContent {
            SampleAppTheme {
                DialerScreen(
                    onCallButtonClick = { phoneNumber ->
                        startPhoneCallOrDial(phoneNumber)
                    }
                )
            }
        }
    }

    private fun startPhoneCallOrDial(phoneNumber: String) {
        lifecycleScope.launch {
            val result =
                Warden.with(this@DialerActivity).requestPermission(Manifest.permission.CALL_PHONE)
            when (result) {
                is PermissionState.Denied -> startActivity(dialNumber(phoneNumber))
                PermissionState.Granted -> startActivity(callNumber(phoneNumber))
            }
        }
    }
}
