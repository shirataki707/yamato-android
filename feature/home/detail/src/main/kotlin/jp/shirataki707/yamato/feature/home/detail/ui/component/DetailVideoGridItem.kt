package jp.shirataki707.yamato.feature.home.detail.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.shirataki707.yamato.core.ui.video.component.AsyncVideoThumbnail

@Composable
internal fun DetailVideoGridItem(
    imageUrl: String,
    videoTitle: String,
    videoId: String,
    channelName: String,
    publishedAt: String,
    onVideoClick: (videoId: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.clickable { onVideoClick(videoId) },
    ) {
        AsyncVideoThumbnail(
            imageUrl = imageUrl,
            contentDescription = videoTitle,
        )

        Text(
            text = videoTitle,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(8.dp),
        )

        Text(
            text = publishedAt,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(start = 8.dp),
        )

        Text(
            text = channelName,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(8.dp),
        )
    }
}

@Preview
@Composable
private fun DetailVideoGridItemPreview() {
    DetailVideoGridItem(
        imageUrl = "https://example",
        videoTitle = "富士山登山やってみた",
        videoId = "123456",
        channelName = "しらたきチャンネル",
        publishedAt = "2022/01/01",
        onVideoClick = {},
    )
}
