package com.example.lab3.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.lab3.R
import com.example.lab3.models.user.User
import com.example.lab3.ui.theme.Lab3Theme
import com.example.lab3.ui.utils.TranslatePreview


@Composable
fun ProfileScreen(user: User, modifier: Modifier = Modifier) {
    val globalPaddingTop = dimensionResource(id = R.dimen.profile_padding_top)
    val globalPaddingHor = dimensionResource(id = R.dimen.profile_padding_horizontal)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = globalPaddingTop)
            .padding(horizontal = globalPaddingHor)
            .then(modifier)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            UserPhoto(photo = user.photo)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            UserInfo(user = user)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            UserDetails(
                numPosts = user.numPosts,
                numFollowers = user.numFollowers,
                numFollowing = user.numFollowing
            )
        }
    }
}


@Composable
@TranslatePreview
fun ProfileScreenPreview() {
    val testUser = User(
        1L, "Emma", "Brony",
        "My name is Emma. And I am collector of flowers.", R.drawable.photo1,
        "France", "Paris",1111, 222, 33
    )

    Lab3Theme {
        Surface {
            ProfileScreen(user = testUser)
        }
    }
}