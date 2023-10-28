package com.example.lab3.models.call


data class CallInfo(
    val callState: CallState,
    val calleeInfo: CalleeInfo,
    val isMicrophoneOn: Boolean,
    val isSpeakerOn: Boolean,
)
