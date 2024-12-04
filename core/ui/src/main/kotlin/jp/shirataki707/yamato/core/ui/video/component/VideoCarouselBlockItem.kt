package jp.shirataki707.yamato.core.ui.video.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.shirataki707.yamato.core.designsystem.theme.YamatoTheme

@Composable
internal fun VideoCarouselBlockItem(
    imageUrl: String,
    videoTitle: String,
    videoId: String,
    channelName: String,
    onVideoClick: (videoId: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val configuration = LocalConfiguration.current
    val screenWidthDp = remember(configuration) {
        configuration.screenWidthDp.dp
    }
    // TODO: ブロックが2.2個分表示されるように調整する
    val thumbnailWidthDp = remember(screenWidthDp) {
        screenWidthDp / 2.4f
    }

    Column(
        modifier = modifier
            .width(thumbnailWidthDp)
            .clickable { onVideoClick(videoId) },
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
private fun VideoCarouselBlockItemPreview() {
    YamatoTheme {
        VideoCarouselBlockItem(
            imageUrl = "https://example",
            videoTitle = "八ヶ岳完全ガイド！登山ルートからアクセス方法まで徹底解説",
            videoId = "123456",
            channelName = "山登りチャンネル",
            onVideoClick = { },
        )
    }
}
