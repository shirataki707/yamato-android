package jp.shirataki707.yamato.core.ui.video

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
import jp.shirataki707.yamato.core.model.data.DetailPageConfig
import jp.shirataki707.yamato.core.model.data.VideoResources
import jp.shirataki707.yamato.core.ui.video.component.VideoCarouselBlockHeader
import jp.shirataki707.yamato.core.ui.video.component.VideoCarouselBlockItem

@Composable
fun VideoCarouselBlock(
    videoCarouselBlock: VideoResources.VideoCarouselBlock,
    onVideoClick: (videoId: String) -> Unit,
    onMoreButtonClick: (DetailPageConfig) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        VideoCarouselBlockHeader(
            headerTitle = videoCarouselBlock.blockTitle,
            buttonText = stringResource(R.string.core_ui_more_show),
            onButtonClick = {
                onMoreButtonClick(videoCarouselBlock.detailPageConfig)
            },
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(videoCarouselBlock.videoSummaries) { videoSummary ->
                VideoCarouselBlockItem(
                    imageUrl = videoSummary.thumbnailUrl,
                    videoTitle = videoSummary.videoTitle,
                    channelName = videoSummary.channelName,
                    onVideoClick = { onVideoClick(videoSummary.videoId) },
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
            videoCarouselBlock = VideoResources.VideoCarouselBlock(
                blockTitle = "おすすめ",
                blockType = VideoResources.VideoCarouselBlockType.Recommended,
                videoSummaries = listOf(
                    VideoResources.VideoCarouselBlock.VideoSummary(
                        videoTitle = "富士山登山やってみた",
                        channelName = "しらたきチャンネル",
                        description = "",
                        thumbnailUrl = "",
                        videoId = "",
                        publishedAt = "",
                    ),
                    VideoResources.VideoCarouselBlock.VideoSummary(
                        videoTitle = "八ヶ岳に行ってみた",
                        channelName = "やっほーチャンネル",
                        description = "",
                        thumbnailUrl = "",
                        videoId = "",
                        publishedAt = "",
                    ),
                ),
                detailPageConfig = DetailPageConfig(
                    detailPageTitle = "おすすめ",
                    carouselBlockType = VideoResources.VideoCarouselBlockType.Recommended,
                    keyword = "登山",
                ),
            ),
            onVideoClick = { },
            onMoreButtonClick = { },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
