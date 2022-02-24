@file:OptIn(ExperimentalMaterialApi::class)

package com.alexstyl.warden.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DialerScreen(
    onCallButtonClick: (String) -> Unit
) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
        ) {
            Spacer(Modifier.height(20.dp))

            val numberState = remember { mutableStateOf("555") }

            CenteredRow(Modifier.weight(1f)) {
                Text(
                    text = numberState.value,
                    letterSpacing = 2.sp,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.body1
                )
            }

            Row(Modifier.weight(1f)) {
                DialButton(Modifier.weight(1f), "1", "abc")
                DialButton(Modifier.weight(1f), "2", "def")
                DialButton(Modifier.weight(1f), "3", "def")
            }

            Row(Modifier.weight(1f)) {
                DialButton(Modifier.weight(1f), "4", "ghi")
                DialButton(Modifier.weight(1f), "5", "jkl")
                DialButton(Modifier.weight(1f), "6", "mno")
            }

            Row(Modifier.weight(1f)) {
                DialButton(Modifier.weight(1f), "7", "pqrs")
                DialButton(Modifier.weight(1f), "8", "tuv")
                DialButton(Modifier.weight(1f), "9", "wxyz")
            }

            Row(Modifier.weight(1f)) {
                DialButton(Modifier.weight(1f), "*")
                DialButton(Modifier.weight(1f), "0", "+")
                DialButton(Modifier.weight(1f), "#")
            }

            CenteredRow(Modifier.weight(1f)) {
                CallButton(onClick = { onCallButtonClick(numberState.value) })
            }
        }
    }
}

@Composable
private fun CenteredRow(
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    Row(modifier) {
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}

@Composable
private fun CallButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier,
        contentAlignment = Alignment.Center
    ) {
        Surface(
            color = MaterialTheme.colors.primary,
            onClick = onClick,
            modifier = Modifier.size(96.dp),
            shape = CircleShape, elevation = 4.dp
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    painter = painterResource(R.drawable.ic_call),
                    contentDescription = "Call",
                    modifier = Modifier.size(48.dp),
                    tint = Color.White,
                )
            }
        }
    }

}

@Composable
private fun DialButton(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String = "",
) {
    Box(
        modifier,
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier.size(96.dp),
            shape = CircleShape, elevation = 4.dp
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(title, style = MaterialTheme.typography.body1)
                if (subtitle.isNotEmpty()) {
                    Spacer(Modifier.height(2.dp))
                    Text(
                        subtitle.uppercase(),
                        style = MaterialTheme.typography.body2,
                        letterSpacing = 2.sp
                    )
                }
            }
        }
    }
}

