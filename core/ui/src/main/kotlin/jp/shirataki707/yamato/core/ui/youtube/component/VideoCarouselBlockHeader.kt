package jp.shirataki707.yamato.core.ui.youtube.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.shirataki707.yamato.core.designsystem.component.YamatoButton
import jp.shirataki707.yamato.core.designsystem.theme.YamatoTheme

@Composable
internal fun VideoCarouselBlockHeader(
    headerTitle: String,
    buttonText: String,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Text(
            text = headerTitle,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
        )
        YamatoButton(
            onClick = onButtonClick,
            content = {
                Text(
                    text = buttonText,
                    style = MaterialTheme.typography.titleSmall,
                )
            },
            modifier = Modifier.padding(8.dp),
        )
    }
}

@Preview
@Composable
private fun VideoCarouselBlockHeaderPreview() {
    YamatoTheme {
        VideoCarouselBlockHeader(
            headerTitle = "富士山登山に興味があるあなたへおすすめの動画",
            buttonText = "もっと見る",
            onButtonClick = { },
        )
    }
}
