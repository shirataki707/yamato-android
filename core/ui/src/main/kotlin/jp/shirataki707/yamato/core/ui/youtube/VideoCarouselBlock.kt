package jp.shirataki707.yamato.core.ui.youtube

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.shirataki707.core.ui.R
import jp.shirataki707.yamato.core.designsystem.theme.YamatoTheme
import jp.shirataki707.yamato.core.model.data.YoutubeVideoResource
import jp.shirataki707.yamato.core.ui.youtube.component.VideoCarouselBlockHeader
import jp.shirataki707.yamato.core.ui.youtube.component.VideoCarouselBlockItem

@Composable
fun VideoCarouselBlock(
    headerTitle: String,
    youtubeVideoResources: List<YoutubeVideoResource>,
    onVideoClick: (videoId: String) -> Unit,
    onMoreButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        VideoCarouselBlockHeader(
            headerTitle = headerTitle,
            buttonText = stringResource(R.string.core_ui_more_show),
            onButtonClick = onMoreButtonClick,
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(youtubeVideoResources) { youtubeVideoResource ->
                VideoCarouselBlockItem(
                    imageUrl = youtubeVideoResource.thumbnailUrl,
                    videoTitle = youtubeVideoResource.videoTitle,
                    channelName = youtubeVideoResource.channelName,
                    onVideoClick = { onVideoClick(youtubeVideoResource.videoId) },
                )
            }
        }
    }
}

@Preview
@Composable
private fun VideoCarouselBlockPreview() {
    YamatoTheme {
        VideoCarouselBlock(
            headerTitle = "富士山登山に興味があるあなたへオススメの動画",
            youtubeVideoResources = listOf(
                YoutubeVideoResource(
                    videoTitle = "富士山登山の魅力",
                    channelName = "山登りチャンネル",
                    description = "富士山登山の魅力を紹介します。",
                    thumbnailUrl = "https://example.com/thumbnail.jpg",
                    videoId = "1234567890",
                    publishedAt = "2022-01-01",
                ),
                YoutubeVideoResource(
                    videoTitle = "富士山登山の準備",
                    channelName = "山登りチャンネル",
                    description = "富士山登山の準備を紹介します。",
                    thumbnailUrl = "https://example.com/thumbnail.jpg",
                    videoId = "1234567890",
                    publishedAt = "2022-01-01",
                ),
                YoutubeVideoResource(
                    videoTitle = "富士山登山の注意点",
                    channelName = "山登りチャンネル",
                    description = "富士山登山の注意点を紹介します。",
                    thumbnailUrl = "https://example.com/thumbnail.jpg",
                    videoId = "1234567890",
                    publishedAt = "2022-01-01",
                ),
            ),
            onVideoClick = { },
            onMoreButtonClick = { },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
