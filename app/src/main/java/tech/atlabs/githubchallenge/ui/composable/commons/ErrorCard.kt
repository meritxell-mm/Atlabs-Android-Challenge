package tech.atlabs.githubchallenge.ui.composable.commons

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import tech.atlabs.githubchallenge.R
import tech.atlabs.githubchallenge.ui.theme.AppErrorColor

@Composable
fun ErrorCard(
    errorMessage: String,
    onRetry: (() -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .padding(dimensionResource(R.dimen.xxlarge_padding))
            .border(
                width = dimensionResource(R.dimen.border_thickness),
                color = AppErrorColor,
                shape = MaterialTheme.shapes.medium
            ),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.xlarge_padding)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = errorMessage,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
            )

            onRetry?.let {
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.large_spacer)))
                Button(
                    onClick = onRetry,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = AppErrorColor)
                ) {
                    Text(
                        text = stringResource(R.string.error_btn_retry),
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.White
                    )
                }
            }
        }
    }
}