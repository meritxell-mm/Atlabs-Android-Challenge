package tech.atlabs.githubchallenge.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

fun appType(isDark: Boolean, isLandscape: Boolean): Typography {
    val titleColor = if (isDark) AppTitleColorDark else AppTitleColor
    val textColor = if (isDark) AppTextColorDark else AppTextColor

    return Typography(
        bodyLarge = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            color = textColor
        ),
        bodyMedium = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = textColor
        ),
        bodySmall = TextStyle(
            fontWeight = FontWeight.Light,
            fontSize = 14.sp,
            color = textColor
        ),
        titleLarge = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = if (isLandscape) 20.sp else 30.sp,
            color = titleColor
        ),
        titleMedium = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = titleColor
        ),
        titleSmall = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = titleColor
        )
    )
}
