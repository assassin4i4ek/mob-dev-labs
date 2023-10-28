package com.example.lab3.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextAlign
import com.example.lab3.R
import java.text.NumberFormat


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
