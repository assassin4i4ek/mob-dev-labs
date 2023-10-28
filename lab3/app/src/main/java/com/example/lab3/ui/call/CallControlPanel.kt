package com.example.lab3.ui.call

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab3.R
import com.example.lab3.models.call.CallInfo
import com.example.lab3.models.call.CallState
import com.example.lab3.models.call.CalleeInfo
import com.example.lab3.ui.theme.Lab3Theme


enum class CallButtonType {
    ABORT, SWITCH, NAVIGATION // TODO add other button types
}


enum class CallButtonSize {
    MEDIUM, SMALL
}


@Composable
fun CallButton(
    type: CallButtonType,
    painter: Painter,
    modifier: Modifier = Modifier,
    size: CallButtonSize = CallButtonSize.MEDIUM,
    onClick: () -> Unit = {}
) {
    val buttonSize = when(size) {
        CallButtonSize.MEDIUM -> dimensionResource(
            id = R.dimen.call_control_panel_button_size_medium
        )
        CallButtonSize.SMALL -> dimensionResource(
            id = R.dimen.call_control_panel_button_size_small
        )
    }
    val buttonInnerPadding = dimensionResource(
        id = R.dimen.call_control_panel_button_inner_padding
    )
    val buttonOuterPadding = dimensionResource(
        id = R.dimen.call_control_panel_button_outer_padding
    )
    val buttonCornerSize = dimensionResource(
        id = R.dimen.call_control_panel_button_corner_size
    )
    val buttonBgColor = when(type) {
        CallButtonType.ABORT -> colorResource(id = R.color.cancel)
        CallButtonType.SWITCH -> colorResource(id = R.color.neutral)
        CallButtonType.NAVIGATION -> colorResource(id = R.color.neutral).copy(alpha = 0.4f)
    }
    val buttonIconColor = when(type) {
        CallButtonType.ABORT -> colorResource(id = R.color.on_cancel)
        CallButtonType.SWITCH -> colorResource(id = R.color.on_neutral)
        CallButtonType.NAVIGATION -> colorResource(id = R.color.neutral).copy(alpha = 0.8f)
    }

    Box(
        modifier = Modifier
            .padding(buttonOuterPadding)
            .then(modifier)
    ) {
        Icon(
            painter = painter,
            contentDescription = "turn off microphone",
            modifier = Modifier
                .size(buttonSize)
                .clip(RoundedCornerShape(buttonCornerSize))
                .background(buttonBgColor)
                .clickable(onClick = onClick)
                .padding(buttonInnerPadding)
                .then(modifier),
            tint = buttonIconColor
        )
    }
}


@Composable
fun PrimaryCallButtonsContainer(callInfo: CallInfo, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        horizontalArrangement = Arrangement.Center
    ) {
        if (callInfo.isMicrophoneOn) {
            CallButton(
                type = CallButtonType.SWITCH,
                painter = painterResource(id = R.drawable.mic_off)
            )
        }
        else {
            // TODO implement button
        }

        when(callInfo.callState) {
            CallState.CALLING -> {
                CallButton(
                    type = CallButtonType.ABORT,
                    painter = painterResource(id = R.drawable.call_end)
                )
            }
        }

        if (callInfo.isSpeakerOn) {
            // TODO implement button
        }
        else {
            CallButton(
                type = CallButtonType.SWITCH,
                painter = painterResource(id = R.drawable.speaker_on)
            )
        }
    }
}


@Composable
fun SecondaryCallButtonContainer(callInfo: CallInfo, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
    ) {
        when(callInfo.callState) {
            CallState.CALLING -> {
                CallButton(
                    type = CallButtonType.NAVIGATION,
                    painter = painterResource(id = R.drawable.chevron_left),
                    size = CallButtonSize.SMALL,
                )
            }
        }
    }
}


@Composable
fun CallStatus(state: CallState, modifier: Modifier = Modifier) {
    val stateLabel = when(state) {
        CallState.CALLING -> {
            stringResource(id = R.string.call_state_calling)
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stateLabel,
            color = colorResource(id = R.color.neutral)
        )
    }
}


@Composable
fun CalleeInfoPane(calleeInfo: CalleeInfo, modifier: Modifier = Modifier) {
    val calleeName = stringResource(
        id = R.string.call_callee_name, calleeInfo.name, calleeInfo.surname
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = calleeName,
            color = colorResource(id = R.color.neutral),
            style = MaterialTheme.typography.headlineSmall,
            overflow = TextOverflow.Ellipsis
        )
    }
}


@Composable
fun CallControlPanel(callInfo: CallInfo, modifier: Modifier = Modifier) {
    val paddingTop = dimensionResource(id = R.dimen.call_control_panel_padding_top)
    val paddingBottom = dimensionResource(id = R.dimen.call_control_panel_padding_bottom)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingTop, bottom = paddingBottom)
            .then(modifier)
    ) {
        SecondaryCallButtonContainer(callInfo = callInfo)
        Spacer(modifier = Modifier.weight(1f))
        CallStatus(state = callInfo.callState)
        Spacer(modifier = Modifier.height(24.dp))
        CalleeInfoPane(calleeInfo = callInfo.calleeInfo)
        Spacer(modifier = Modifier.height(32.dp))
        PrimaryCallButtonsContainer(callInfo = callInfo)
    }
}


@Composable
@Preview
fun CallControlPanelPreview() {
    Lab3Theme {
        Surface {
            CallControlPanel(callInfo = CallInfo(
                CallState.CALLING, CalleeInfo("John", "Doe", R.drawable.photo1),
                true, false
            ))
        }
    }
}