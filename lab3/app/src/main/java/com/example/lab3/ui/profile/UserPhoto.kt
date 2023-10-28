package com.example.lab3.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.lab3.R


@Composable
fun UserPhoto(photo: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = photo),
        contentDescription = stringResource(R.string.user_photo_cont_desc),
        modifier = Modifier
            .size(
                width = dimensionResource(id = R.dimen.profile_user_photo_width),
                height = dimensionResource(id = R.dimen.profile_user_photo_height)
            )
            .clip(shape = CircleShape)
            .then(modifier),
        contentScale = ContentScale.Crop,
    )
}