package tech.atlabs.githubchallenge.ui.composable.commons

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun IconText(icon: ImageVector, iconDescription:String, value: String, isAvailable: Boolean = true) {
    Row(modifier = Modifier.padding(4.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = iconDescription,
            tint = if (isAvailable) Color(0xFF4A5D78) else Color.Gray,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = value,
            style =  MaterialTheme.typography.bodyMedium,
            color = if (isAvailable) Color(0xFF4A5D78) else Color.Gray
        )
    }
}