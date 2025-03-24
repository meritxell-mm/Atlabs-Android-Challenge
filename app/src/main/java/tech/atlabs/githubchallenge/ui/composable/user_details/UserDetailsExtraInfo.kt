package tech.atlabs.githubchallenge.ui.composable.user_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import tech.atlabs.githubchallenge.R
import tech.atlabs.githubchallenge.domain.model.User
import tech.atlabs.githubchallenge.ui.composable.commons.IconText

@Composable
fun UserDetailsExtraInfo(user: User) {

    val extraInfo = listOf(
        Triple(Icons.Outlined.Apartment, R.string.icon_company, user.company),
        Triple(Icons.Default.LocationOn, R.string.icon_location, user.location),
        Triple(Icons.Default.Email, R.string.icon_email, user.email),
        Triple(Icons.Default.Link, R.string.icon_blog, user.blog),
        Triple(
            Icons.Default.Close,
            R.string.icon_twitter,
            user.twitterUsername
        ) //TODO canviar icona?
    )

    val items = extraInfo.filter { it.third?.isNotBlank() == true }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        items.forEach { (icon, iconDescription, value) ->
            IconText(
                icon = icon,
                iconDescription = stringResource(iconDescription),
                value = value.orEmpty()
            )
        }
    }
}