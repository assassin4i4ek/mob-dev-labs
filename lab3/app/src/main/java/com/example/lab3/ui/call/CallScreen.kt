package com.example.lab3.ui.call

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.lab3.R
import com.example.lab3.models.call.CallInfo
import com.example.lab3.models.call.CallState
import com.example.lab3.models.call.CalleeInfo
import com.example.lab3.ui.theme.Lab3Theme
import com.example.lab3.ui.utils.TranslatePreview


@Composable
fun CallScreen(callInfo: CallInfo, modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize().then(modifier)) {
        CallBackgroundPane(photo = callInfo.calleeInfo.photo)
        CallControlPanel(callInfo = callInfo)
    }
}


@Composable
@TranslatePreview
fun CallScreenPreview() {
    val callInfo = CallInfo(
        callState = CallState.CALLING,
        calleeInfo = CalleeInfo(
            photo = R.drawable.photo1,
            name = "Jane",
            surname = "Doe"
        ),
        isMicrophoneOn = true,
        isSpeakerOn = false
    )

    Lab3Theme {
        Surface {
            CallScreen(callInfo = callInfo)
        }
    }
}