package tech.atlabs.githubchallenge.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val GitHubChallengeTypography = Typography(
        bodyLarge = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                color = GitHubTextColor
        ),
        bodyMedium = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = GitHubTextColor
        ),
        bodySmall = TextStyle(
                fontWeight = FontWeight.Light,
                fontSize = 14.sp,
                color = GitHubTextColor
        ),
        titleLarge = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = GitHubTitleColor
        ),
        titleMedium = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = GitHubTitleColor
        ),
        titleSmall = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = GitHubTitleColor
        )
)
