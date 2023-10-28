package com.example.lab3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.lab3.models.call.CallInfo
import com.example.lab3.models.call.CallState
import com.example.lab3.models.call.CalleeInfo
import com.example.lab3.models.user.User
import com.example.lab3.ui.call.CallScreen
import com.example.lab3.ui.profile.ProfileScreen
import com.example.lab3.ui.theme.Lab3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val user by remember {
                        mutableStateOf(
                            User(1L, "Emma", "Brony",
                                "My name is Emma. And I am collector of flowers.",
                                R.drawable.photo1, "France", "Paris",
                                172, 324, 119
                            )
                        )
                    }
//                    ProfileScreen(user = user)

                    val call by remember {
                        derivedStateOf {
                            CallInfo(
                                callState = CallState.CALLING,
                                calleeInfo = CalleeInfo(user.name, user.surname, user.photo),
                                isMicrophoneOn = true,
                                isSpeakerOn = false
                            )
                        }
                    }
                    CallScreen(callInfo = call)
                }
            }
        }
    }
}