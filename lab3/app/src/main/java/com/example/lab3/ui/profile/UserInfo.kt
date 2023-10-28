package com.example.lab3.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.lab3.R
import com.example.lab3.models.user.User


@Composable
fun UserInfo(user: User, modifier: Modifier = Modifier) {
    val usernamePaddingTop = dimensionResource(id = R.dimen.profile_user_name_padding_top)
    val userNamePaddingBottom = dimensionResource(id = R.dimen.profile_user_name_padding_bottom)
    val userDescPaddingTop = dimensionResource(id = R.dimen.profile_user_description_padding_top)
    val userDescPaddingBottom = dimensionResource(
        id = R.dimen.profile_user_description_padding_bottom
    )

    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(usernamePaddingTop))

        val iconSize = dimensionResource(id = R.dimen.profile_follow_user_icon_size)
        val iconSpacerWidth = dimensionResource(id = R.dimen.profile_follow_user_icon_padding_left)
        val wrapperOffset = (iconSize + iconSpacerWidth) / 2

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .offset(x = wrapperOffset),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {

            UserName(name = user.name, surname = user.surname)
            Spacer(modifier = Modifier.width(iconSpacerWidth))
            Icon(
                painter = painterResource(id = R.drawable.user_follow),
                contentDescription = stringResource(R.string.follow_user_cont_desc),
                modifier = Modifier.size(iconSize),
                tint = MaterialTheme.colorScheme.primary
            )
        }
        Spacer(modifier = Modifier.height(userNamePaddingBottom))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            UserLocation(country = user.locationCountry, city = user.locationCity)
        }
        Spacer(modifier = Modifier.height(userDescPaddingTop))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            UserDescription(description = user.description)
        }
        Spacer(modifier = Modifier.height(userDescPaddingBottom))
    }
}


@Composable
fun UserName(
    name: String,
    surname: String,
    modifier: Modifier = Modifier,
    overflow: TextOverflow = TextOverflow.Clip
) {
    val text = stringResource(id = R.string.profile_user_name, name, surname)
    Text(
        text = text,
        modifier = modifier,
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        overflow = overflow
    )
}


@Composable
fun UserLocation(country: String, city: String, modifier: Modifier = Modifier) {
    val text = stringResource(id = R.string.profile_user_location, country, city)
    Text(
        text = text,
        modifier = modifier,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        style = MaterialTheme.typography.bodyMedium,
//        fontWeight = FontWeight.Bold
    )
}


@Composable
fun UserDescription(
    description: String,
    modifier: Modifier = Modifier,
    overflow: TextOverflow = TextOverflow.Clip
) {
    Text(
        text = description,
        modifier = modifier,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Center,
        overflow = overflow
    )
}
