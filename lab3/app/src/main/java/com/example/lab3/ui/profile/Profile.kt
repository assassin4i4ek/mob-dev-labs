package com.example.lab3.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.lab3.R
import com.example.lab3.models.user.User
import com.example.lab3.ui.theme.Lab3Theme
import com.example.lab3.ui.utils.TranslatePreview
import java.text.NumberFormat


@Composable
fun UserPhoto(image: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = image),
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


@Composable
fun UserDetailsItem(label: String, modifier: Modifier = Modifier, icon: @Composable () -> Unit) {
    val maxWidth = dimensionResource(id = R.dimen.profile_user_details_item_max_width)
    val iconSize = dimensionResource(id = R.dimen.profile_user_details_icon_size)
    val iconPaddingBottom = dimensionResource(id = R.dimen.profile_user_details_icon_padding_bottom)

    Column(
        modifier = Modifier
            .width(width = maxWidth)
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier
            .padding(bottom = iconPaddingBottom)
            .size(size = iconSize)) {
            icon()
        }
        Text(
            text = label,
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
        )
    }
}


@Composable
fun UserDetails(
    numPosts: Int, numFollowers: Int, numFollowing: Int, modifier: Modifier = Modifier,
    formatNum: (Int) -> String = NumberFormat.getIntegerInstance()::format
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // num posts
        val numPostsFmt = formatNum(numPosts)
        val numPostsLabel = stringResource(id = R.string.profile_user_num_posts_label, numPostsFmt)
        UserDetailsItem(label = numPostsLabel) {
            Icon(
                painter = painterResource(id = R.drawable.num_posts),
                contentDescription = stringResource(R.string.number_of_posts_cont_desc),
                tint = MaterialTheme.colorScheme.secondary
            )
        }
        // num followers
        val numFollowersFmt = formatNum(numFollowers)
        val numFollowersLabel = stringResource(
            id = R.string.profile_user_num_followers_label, numFollowersFmt
        )
        UserDetailsItem(label = numFollowersLabel) {
            Icon(
                painter = painterResource(id = R.drawable.num_followers),
                contentDescription = stringResource(R.string.number_of_followers_cont_desc),
                tint = MaterialTheme.colorScheme.secondary
            )
        }
        // num followers
        val numFollowingFmt = formatNum(numFollowing)
        val numFollowingLabel = stringResource(
            id = R.string.profile_user_num_following_label, numFollowingFmt
        )
        UserDetailsItem(label = numFollowingLabel) {
            Icon(
                painter = painterResource(id = R.drawable.num_following),
                contentDescription = stringResource(R.string.number_of_followings_cont_desc),
                tint = MaterialTheme.colorScheme.secondary
            )
        }
    }
}


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
fun Profile(user: User, modifier: Modifier = Modifier) {
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
            UserPhoto(image = user.image)
        }
        UserInfo(user = user)
    }
}


@Composable
@TranslatePreview
fun ProfilePreview() {
    print(R.drawable.photo1)
    val testUser = User(
        1L, "Emma", "Brony",
        "My name is Emma. And I am collector of flowers.", R.drawable.photo1,
        "France", "Paris",1111, 222, 33
    )

    Lab3Theme {
        Surface {
            Profile(user = testUser)
        }
    }
}